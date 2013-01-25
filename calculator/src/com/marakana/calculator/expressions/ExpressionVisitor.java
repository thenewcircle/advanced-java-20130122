package com.marakana.calculator.expressions;

public interface ExpressionVisitor<A> {
	A visitNumber(NumberExpression expression);
	A visitOperation(OperationExpression expression);
}
