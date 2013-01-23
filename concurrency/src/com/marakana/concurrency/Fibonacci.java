package com.marakana.concurrency;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
			int n = RANDOM.nextInt(40);
			logger.log(String.format("fib(%d) = %s", n, fib(n)));
		}
	}

	public static class Logger implements Runnable {
		@Override
		public void run() {
			// TODO
		}

		public void log(String message) {
			System.out.println(message); // FIXME: this is synchronous!!
		}
	}

	public static void main(String[] args) throws Exception {
		
		ExecutorService pool = Executors.newFixedThreadPool(10);
		Logger logger = new Logger();
		pool.execute(logger);

		for (int i = 0; i < 20; i++) {
			pool.execute(new FibonacciTask(logger));
		}

		pool.shutdown();
		pool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
		System.out.println("done");
	}

}
