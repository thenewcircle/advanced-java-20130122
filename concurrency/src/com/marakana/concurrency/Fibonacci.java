package com.marakana.concurrency;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Fibonacci {

	private static final Random RANDOM = new Random();
	private static final Object FIB = new Object();

	public static BigInteger fib(int n) {
		return n < 2 ? BigInteger.ONE : fib(n - 1).add(fib(n - 2));
	}

	public static class FibonacciTask extends Actor {

		private final Logger logger;

		public FibonacciTask(Logger logger) {
			this.logger = logger;
		}

		protected void receive(Object message) {
			if (message == FIB) {
				int n = RANDOM.nextInt(30);
				logger.tell(String.format("fib(%d) = %s", n, fib(n)));
				this.tell(FIB);
			}
		}
	}

	public static class Logger extends Actor {
		protected void receive(Object message) {
			System.out.println(message);
		}
	}

	public static void main(String[] args) throws Exception {

		ExecutorService pool = Executors.newFixedThreadPool(10);
		Logger logger = new Logger();
		pool.execute(logger);

		for (int i = 0; i < 9; i++) {
			FibonacciTask task = new FibonacciTask(logger);
			pool.execute(task);
			task.tell(FIB);
		}
	}

}
