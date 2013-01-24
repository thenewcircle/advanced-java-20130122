package com.marakana.clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.marakana.concurrency.Actor;

public class Server {

	public static enum Message {
		SHUTDOWN
	}

	public static class Worker extends Actor {
		@Override
		protected void receive(Object message) {
			if (message == Message.SHUTDOWN) {
				shutdown();
			} else if (message instanceof Socket) {
				work((Socket) message);
			}
		}

		private void work(Socket client) {
			try {
				try {
					BufferedReader in = new BufferedReader(
							new InputStreamReader(client.getInputStream()));
					try {
						System.out.println(in.readLine());
					} finally {
						in.close();
					}
				} finally {
					client.close();
				}
			} catch (IOException e) {
				// TODO: do something with this exception
			}
		}
	}

	public static void main(String[] args) {
		ExecutorService pool = Executors.newCachedThreadPool();

		try {
			ServerSocket server = new ServerSocket(31337);
			try {
				Socket client = server.accept();
				// TODO: make an actor that uses the client connection
			} finally {
				server.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
