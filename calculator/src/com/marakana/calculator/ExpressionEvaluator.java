package com.marakana.calculator;

import com.marakana.calculator.expressions.ExpressionVisitor;
import com.marakana.calculator.expressions.NumberExpression;
import com.marakana.calculator.expressions.OperationExpression;

public class ExpressionEvaluator implements ExpressionVisitor<Integer> {

	@Override
	public Integer visitNumber(NumberExpression expression) {
		return expression.getValue();
	}

	@Override
	public Integer visitOperation(OperationExpression expression) {
		return expression.getOp().operate(expression.getLhs().accept(this), expression.getRhs().accept(this));
	}

}
