package com.marakana.calculator;

public enum Operator {
	ADD("+") {
		public int operate(int lhs, int rhs) {
			return lhs + rhs;
		}
	},
	SUBTRACT("-") {
		public int operate(int lhs, int rhs) {
			return lhs - rhs;
		}
	},
	MULTIPLY("*") {
		public int operate(int lhs, int rhs) {
			return lhs * rhs;
		}
	},
	DIVIDE("/") {
		public int operate(int lhs, int rhs) {
			return lhs / rhs;
		}
	};

	private final String token;

	private Operator(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return token;
	}

	abstract public int operate(int lhs, int rhs);
}