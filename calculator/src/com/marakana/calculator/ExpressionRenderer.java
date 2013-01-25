package com.marakana.calculator;

import com.marakana.calculator.expressions.ExpressionVisitor;
import com.marakana.calculator.expressions.NumberExpression;
import com.marakana.calculator.expressions.OperationExpression;

public class ExpressionRenderer implements ExpressionVisitor<String> {

	@Override
	public String visitNumber(NumberExpression expression) {
		return String.valueOf(expression.getValue());
	}

	@Override
	public String visitOperation(OperationExpression expression) {
		return String.format("(%s %s %s)", expression.getLhs().accept(this), expression.getOp(), expression.getRhs().accept(this));
	}

}
