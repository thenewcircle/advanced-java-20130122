package com.marakana.concurrency;

import java.math.BigInteger;
import java.util.Random;

public class Fibonacci {

	private static final Random RANDOM = new Random();

	public static BigInteger fib(int n) {
		return n < 2
			? BigInteger.ONE
			: fib(n-1).add(fib(n-2));
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					int n = RANDOM.nextInt(40);
					System.out.format("fib(%d) = %s\n", n, fib(n));
				}
			}).start();
		}
	}

}
