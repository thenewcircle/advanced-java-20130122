package com.marakana.concurrency;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Fibonacci {

	private static final Random RANDOM = new Random();

	public static BigInteger fib(int n) {
		return n < 2 ? BigInteger.ONE : fib(n - 1).add(fib(n - 2));
	}

	public static class FibonacciTask implements Runnable {

		private final Logger logger;

		public FibonacciTask(Logger logger) {
			this.logger = logger;
		}

		@Override
		public void run() {
			int n = RANDOM.nextInt(30);
			logger.log(String.format("fib(%d) = %s", n, fib(n)));
		}
	}

	public static class Logger implements Runnable {

		private final Queue<String> messages = new LinkedList<String>();

		@Override
		public void run() {
			while (true) {
				synchronized (messages) {
					if (messages.isEmpty()) {
						Thread.yield();
					} else {
						System.out.println(messages.remove());
					}
				}
			}
		}

		public void log(String message) {
			synchronized (messages) {
				messages.add(message);
			}
		}

	}

	public static void main(String[] args) throws Exception {

		ExecutorService pool = Executors.newFixedThreadPool(10);
		Logger logger = new Logger();
		pool.execute(logger);

		while (true) {
			pool.execute(new FibonacciTask(logger));
		}
	}

}
