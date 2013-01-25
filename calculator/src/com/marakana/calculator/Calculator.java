package com.marakana.calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.marakana.calculator.expressions.Expression;
import com.marakana.calculator.expressions.NumberExpression;
import com.marakana.calculator.expressions.OperationExpression;

public class Calculator {

	private static final Map<String, Operator> operators;
	static {
		operators = new HashMap<String, Operator>();
		for (Operator op : Operator.values()) {
			operators.put(op.toString(), op);
		}
	}

	private static boolean handleNumber(String token, Stack<Expression> stack) {
		try {
			stack.push(new NumberExpression(Integer.parseInt(token)));
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private static boolean handleOperator(String token, Stack<Expression> stack) {
		Operator op = operators.get(token);
		if (op == null) {
			return false;
		}

		Expression rhs = stack.pop(), lhs = stack.pop();
		stack.push(new OperationExpression(lhs, rhs, op));
		return true;
	}

	public static int calculate(String expression) {
		Stack<Expression> stack = new Stack<Expression>();
		for (String token : expression.split(" ")) {
			if (!handleNumber(token, stack) && !handleOperator(token, stack)) {
				throw new IllegalArgumentException("unrecognized token: "
						+ token);
			}
		}
		return stack.pop().getValue();
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: Calculator <expression>");
			return;
		}
		System.out.println(calculate(args[0]));
	}
}
