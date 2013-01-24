package com.marakana.calculator;

import java.util.Stack;

public class Calculator {

	private static boolean handleNumber(String token, Stack<Integer> stack) {
		try {
			stack.push(Integer.parseInt(token));
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private static boolean handleOperator(String token, Stack<Integer> stack) {
		if ("+".equals(token)) {
			int rhs = stack.pop(), lhs = stack.pop();
			stack.push(lhs + rhs);
			return true;
		} else if ("-".equals(token)) {
			int rhs = stack.pop(), lhs = stack.pop();
			stack.push(lhs - rhs);
			return true;
		} else if ("*".equals(token)) {
			int rhs = stack.pop(), lhs = stack.pop();
			stack.push(lhs * rhs);
			return true;
		} else if ("/".equals(token)) {
			int rhs = stack.pop(), lhs = stack.pop();
			stack.push(lhs / rhs);
			return true;
		} else {
			return false;
		}
	}

	public static int calculate(String expression) {
		Stack<Integer> stack = new Stack<Integer>();
		for (String token : expression.split(" ")) {
			if (!handleNumber(token, stack) && !handleOperator(token, stack)) {
				throw new IllegalArgumentException("unrecognized token: " + token);
			}
		}
		return stack.pop();
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: Calculator <expression>");
			return;
		}
		System.out.println(calculate(args[0]));
	}
}
