package com.marakana.concurrency;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Fibonacci {

	public static enum Message { FIB, SHUTDOWN }

	public static class FibonacciTask extends Actor {

		private static final Random RANDOM = new Random();
		private final Logger logger;

		private static BigInteger fib(int n) {
			return n < 2 ? BigInteger.ONE : fib(n - 1).add(fib(n - 2));
		}

		public FibonacciTask(Logger logger) {
			this.logger = logger;
			this.tell(Message.FIB);
		}

		protected void receive(Object message) {
			if (message == Message.FIB) {
				int n = RANDOM.nextInt(30);
				logger.tell(String.format("fib(%d) = %s", n, fib(n)));
				this.tell(Message.FIB);
			} else if (message == Message.SHUTDOWN) {
				this.shutdown();
			}
		}
	}

	public static class Logger extends Actor {
		protected void receive(Object message) {
			if (message == Message.SHUTDOWN) {
				this.shutdown();
			} else {
				System.out.println(message);
			}
		}
	}

	public static void main(String[] args) throws Exception {

		// create a thread pool and an empty list of actors
		ExecutorService pool = Executors.newFixedThreadPool(10);
		List<Actor> actors = new ArrayList<Actor>();

		// create a logger, execute it and add it to the list
		Logger logger = new Logger();
		pool.execute(logger);
		actors.add(logger);

		// create fibonacci tasks, execute them and add them to the list
		for (int i = 1; i < 10; i++) {
			FibonacciTask task = new FibonacciTask(logger);
			pool.execute(task);
			actors.add(task);
		}

		// wait for user to hit enter, and tell all actors to shut down
		System.in.read();
		for (Actor actor : actors) {
			actor.tell(Message.SHUTDOWN);
		}

		// shut down the thread pool
		pool.shutdown();
	}

}
