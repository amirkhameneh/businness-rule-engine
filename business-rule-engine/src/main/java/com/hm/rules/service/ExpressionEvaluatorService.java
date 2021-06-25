package com.hm.rules.service;

import java.util.List;

import com.hm.rules.model.dom.Operation;
import com.hm.rules.model.dom.Token;

public interface ExpressionEvaluatorService {
	
	List<Operation> compileExpression(String expression);
	
	int complieSubExpression(List<Operation> executionSequence ,List<Token> expression);
	
	List<Token> parseExpression(String expression);
	
	boolean validateExpression(String expression);
	
	boolean validateExpressionParentheses(List<Token> expression);
	
	public void truncateParentheses(List<Token> expression);
	
	
}
