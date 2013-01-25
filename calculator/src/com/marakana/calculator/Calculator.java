package com.marakana.calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Calculator {

	private static final Map<String, Operator> operators;
	static {
		operators = new HashMap<String, Operator>();
		for (Operator op : Operator.values()) {
			operators.put(op.toString(), op);
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
		Operator op = operators.get(token);
		if (op == null) {
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
				throw new IllegalArgumentException("unrecognized token: "
						+ token);
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
