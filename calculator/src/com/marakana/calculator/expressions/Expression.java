package com.marakana.calculator.expressions;

public interface Expression {
	<A> A accept(ExpressionVisitor<A> visitor);
}
