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

	public static void main(String[] args) throws Exception {
		
		ExecutorService pool = Executors.newFixedThreadPool(10);

		for (int i = 0; i < 20; i++) {
			pool.execute(new Runnable() {

				@Override
				public void run() {
					int n = RANDOM.nextInt(40);
					System.out.format("fib(%d) = %s\n", n, fib(n));
				}
			});
		}

		pool.shutdown();
		pool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
		System.out.println("done");
	}

}
