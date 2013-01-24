package com.marakana.clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.marakana.concurrency.Actor;

public class Server {

	public static class Logger extends Actor {
		protected void receive(Object message) {
			System.out.println(message);
		}
	}

	public static class Worker extends Actor {

		private final Logger logger;

		public Worker(Logger logger) {
			this.logger = logger;
		}

		@Override
		protected void receive(Object message) {
			if (message instanceof Socket) {
				work((Socket) message);
			}
		}

		private void work(Socket client) {
			try {
				try {
					BufferedReader in = new BufferedReader(
							new InputStreamReader(client.getInputStream()));
					try {
						logger.tell(in.readLine());
					} finally {
						in.close();
					}
				} finally {
					client.close();
				}
			} catch (IOException e) {
				logger.tell(e);
			}
		}
	}

	public static void main(String[] args) {
		ExecutorService pool = Executors.newCachedThreadPool();

		Logger logger = new Logger();
		pool.execute(logger);

		List<Worker> workers = new ArrayList<Worker>(10);
		for (int i = 0; i < 10; i++) {
			Worker worker = new Worker(logger);
			pool.execute(worker);
			workers.add(worker);
		}
		int worker = 0;

		try {
			ServerSocket server = new ServerSocket(31337);
			try {
				while (true) {
					Socket client = server.accept();
					workers.get(worker).tell(client);
					worker = (worker + 1) % 10;
				}
			} finally {
				server.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
