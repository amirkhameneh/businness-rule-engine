package com.hm.rules.service;

import java.util.List;

import com.hm.rules.model.dom.Operation;
import com.hm.rules.model.dom.Token;

public interface ExpressionEvaluatorService {
	
	List<Operation> compileExpression(String expresstion);
	
	int complieSubExpression(List<Operation> executionSequence ,List<Token> expression);
	
	List<Token> parseExpression(String expresstion);
	
	boolean validateExpression(String expresstion);
	
	boolean validateExpressionParentheses(String expresstion);
	
	
	
	
}
