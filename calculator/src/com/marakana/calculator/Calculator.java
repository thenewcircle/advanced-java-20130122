package com.marakana.calculator;

import java.util.Stack;

public class Calculator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length != 1) {
			System.err.println("Usage: Calculator <expression>");
			return;
		}

		Stack<Integer> stack = new Stack<Integer>();
		for (String token : args[0].split(" ")) {
			try {
				int number = Integer.parseInt(token);
				stack.push(number);
			} catch (NumberFormatException e) {
				if ("+".equals(token)) {
					int rhs = stack.pop(), lhs = stack.pop();
					stack.push(lhs + rhs);
				} else if ("-".equals(token)) {
					int rhs = stack.pop(), lhs = stack.pop();
					stack.push(lhs - rhs);
				} else if ("*".equals(token)) {
					int rhs = stack.pop(), lhs = stack.pop();
					stack.push(lhs * rhs);
				} else if ("/".equals(token)) {
					int rhs = stack.pop(), lhs = stack.pop();
					stack.push(lhs / rhs);
				} else {
					throw new IllegalArgumentException("garbage in expression");
				}
			}
		}

		System.out.println(stack.pop());
	}

}
