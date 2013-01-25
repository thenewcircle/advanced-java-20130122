package com.marakana.calculator;

import java.util.Stack;

public class Calculator {

	private static interface Operator {
		int operate(int lhs, int rhs);
	}

	private static class Add implements Operator {
		public int operate(int lhs, int rhs) {
			return lhs + rhs;
		}
	}

	private static class Subtract implements Operator {
		public int operate(int lhs, int rhs) {
			return lhs - rhs;
		}
	}

	private static class Multiply implements Operator {
		public int operate(int lhs, int rhs) {
			return lhs * rhs;
		}
	}

	private static class Divide implements Operator {
		public int operate(int lhs, int rhs) {
			return lhs / rhs;
		}
	}

	private static boolean handleNumber(String token, Stack<Integer> stack) {
		try {
			stack.push(Integer.parseInt(token));
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private static boolean handleOperator(String token, Stack<Integer> stack) {
		Operator op;
		if ("+".equals(token)) {
			op = new Add();
		} else if ("-".equals(token)) {
			op = new Subtract();
		} else if ("*".equals(token)) {
			op = new Multiply();
		} else if ("/".equals(token)) {
			op = new Divide();
		} else {
			return false;
		}
		
		int rhs = stack.pop(), lhs = stack.pop();
		stack.push(op.operate(lhs, rhs));
		return true;
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
