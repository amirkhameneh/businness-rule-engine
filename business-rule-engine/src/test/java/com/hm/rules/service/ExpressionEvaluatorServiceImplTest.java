package com.hm.rules.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDAssertions.assertThat;

import com.hm.rules.model.dom.Operation;
import com.hm.rules.model.dom.Token;

@SpringBootTest
public class ExpressionEvaluatorServiceImplTest {
	
	@Autowired
	ExpressionEvaluatorService expressionEvaluatorService;
	
	@Test
    void it_should_return_true_when_validates_expression_parentheses() {
		//given
		List<Token> expression1 = new ArrayList();
		List<Token> expression2 = new ArrayList();
		
		//case 1 ((p1==p2)||(p3>=4000))
		expression1.add(new Token("(","LP"));
		expression1.add(new Token("(","LP"));
		expression1.add(new Token("p1","EX"));
		expression1.add(new Token("==","CO"));
		expression1.add(new Token("p2","EX"));
		expression1.add(new Token(")","RP"));
		expression1.add(new Token("||","LO"));
		expression1.add(new Token("(","LP"));
		expression1.add(new Token("p3","EX"));
		expression1.add(new Token(">=","CO"));
		expression1.add(new Token("4000","EX"));
		expression1.add(new Token(")","RP"));
		expression1.add(new Token(")","RP"));
		
		//case 1 ((p1==p2)||(p3>=4000))&&p1~~[1,2,3,4]
		expression2.add(new Token("(","LP"));
		expression2.add(new Token("(","LP"));
		expression2.add(new Token("p1","EX"));
		expression2.add(new Token("==","CO"));
		expression2.add(new Token("p2","EX"));
		expression2.add(new Token(")","RP"));
		expression2.add(new Token("||","LO"));
		expression2.add(new Token("(","LP"));
		expression2.add(new Token("p3","EX"));
		expression2.add(new Token(">=","CO"));
		expression2.add(new Token("4000","EX"));
		expression2.add(new Token(")","RP"));
		expression2.add(new Token(")","RP"));	
		expression2.add(new Token("&&","LO"));		
		expression2.add(new Token("p1","EX"));		
		expression2.add(new Token("~~","CO"));		
		expression2.add(new Token("[1,2,3,4]","EX"));		
		
		
		boolean expectedResult = true;
		
		//when,then
		assertAll(
			      "heading",
			      () -> assertEquals(expectedResult , expressionEvaluatorService.validateExpressionParentheses(expression1)),
			      () -> assertEquals(expectedResult , expressionEvaluatorService.validateExpressionParentheses(expression2))
			    );
		
	}
	
	@Test
    void it_should_return_false_when_validates_expression_parentheses() {
		//given
		List<Token> expression1 = new ArrayList();
		List<Token> expression2 = new ArrayList();
		
		//case 1 p1==p2)||(p3>=4000
		expression1.add(new Token("p1","EX"));
		expression1.add(new Token("==","CO"));
		expression1.add(new Token("p2","EX"));
		expression1.add(new Token(")","RP"));
		expression1.add(new Token("||","LO"));
		expression1.add(new Token("(","LP"));
		expression1.add(new Token("p3","EX"));
		expression1.add(new Token(">=","CO"));
		expression1.add(new Token("4000","EX"));
		
		//case 1 ((p1==p2)||((p3>=4000)))&&p1~~[1,2,3,4])
		expression2.add(new Token("(","LP"));
		expression2.add(new Token("(","LP"));
		expression2.add(new Token("p1","EX"));
		expression2.add(new Token("==","CO"));
		expression2.add(new Token("p2","EX"));
		expression2.add(new Token(")","RP"));
		expression2.add(new Token("||","LO"));
		expression2.add(new Token("(","LP"));
		expression2.add(new Token("(","LP"));
		expression2.add(new Token("p3","EX"));
		expression2.add(new Token(">=","CO"));
		expression2.add(new Token("4000","EX"));
		expression2.add(new Token(")","RP"));
		expression2.add(new Token(")","RP"));
		expression2.add(new Token(")","RP"));	
		expression2.add(new Token("&&","LO"));		
		expression2.add(new Token("p1","EX"));		
		expression2.add(new Token("~~","CO"));		
		expression2.add(new Token("[1,2,3,4]","EX"));
		expression2.add(new Token(")","RP"));	
		
		
		boolean expectedResult = false;
		
		//when,then
		assertAll(
			      "heading",
			      () -> assertEquals(expectedResult , expressionEvaluatorService.validateExpressionParentheses(expression1)),
			      () -> assertEquals(expectedResult , expressionEvaluatorService.validateExpressionParentheses(expression2))
			    );
		
	}
	
	@Test
    void it_should_have_expected_list_when_parse_expresstion() {
		//given
		String expression = "((p1==p22 )&&(p3 ~~  [1,2,3,4,5,6])|| p1>=1000)" ;
		
		List<Token> expectedResault = new ArrayList();
		
		expectedResault.add(new Token("(","LP"));
		expectedResault.add(new Token("(","LP"));
		expectedResault.add(new Token("p1","EX"));
		expectedResault.add(new Token("==","CO"));
		expectedResault.add(new Token("p22","EX"));
		expectedResault.add(new Token(")","RP"));
		expectedResault.add(new Token("&&","LO"));
		expectedResault.add(new Token("(","LP"));
		expectedResault.add(new Token("p3","EX"));
		expectedResault.add(new Token("~~","CO"));
		expectedResault.add(new Token("[1,2,3,4,5,6]","EX"));
		expectedResault.add(new Token(")","RP"));
		expectedResault.add(new Token("||","LO"));
		expectedResault.add(new Token("p1","EX"));
		expectedResault.add(new Token(">=","CO"));
		expectedResault.add(new Token("1000","EX"));
		expectedResault.add(new Token(")","RP"));
		
		//when,then
		assertArrayEquals(expectedResault.toArray(),expressionEvaluatorService.parseExpression(expression).toArray());
	}
	@Test
    void it_should_have_expected_list_when_parse_expresstion2() {
		//given
		String expression = "(p1==1000&&p3==p4&&p7~~[1,3,5,7,899])" ;
		
		List<Token> expectedResault = new ArrayList();
		expectedResault.add(new Token("(","LP"));
		expectedResault.add(new Token("p1","EX"));
		expectedResault.add(new Token("==","CO"));
		expectedResault.add(new Token("1000","EX"));
		expectedResault.add(new Token("&&","LO"));
		expectedResault.add(new Token("p3","EX"));
		expectedResault.add(new Token("==","CO"));
		expectedResault.add(new Token("p4","EX"));
		expectedResault.add(new Token("&&","LO"));
		expectedResault.add(new Token("p7","EX"));
		expectedResault.add(new Token("~~","CO"));
		expectedResault.add(new Token("[1,3,5,7,899]","EX"));
		expectedResault.add(new Token(")","RP"));
		
		//when,then
		assertArrayEquals(expectedResault.toArray(),expressionEvaluatorService.parseExpression(expression).toArray());
	}
	
	@Test
    void it_should_have_expected_list_truncate_parentheses() {
		//given
		List<Token> expression = new ArrayList();
		expression.add(new Token("(","LP"));
		expression.add(new Token("(","LP"));
		expression.add(new Token("p1","EX"));
		expression.add(new Token("==","EX"));
		expression.add(new Token("1000","EX"));
		expression.add(new Token(")","RP"));
		expression.add(new Token(")","RP"));

		List<Token> expectedExpression = new ArrayList();
		expectedExpression.add(new Token("p1","EX"));
		expectedExpression.add(new Token("==","EX"));
		expectedExpression.add(new Token("1000","EX"));
		
		//when
		 expressionEvaluatorService.truncateParentheses(expression);
		
		//then
		assertArrayEquals(expectedExpression.toArray(),expression.toArray());
	}
	
	@Test
    void it_should_have_expected_list_compile_subexpression() {
		
		//given
		List<Operation> executionSequence = new ArrayList();
		List<Token> expression = new ArrayList();
		expression.add(new Token("p1","EX"));
		expression.add(new Token("==","EX"));
		expression.add(new Token("1000","EX"));
		expression.add(new Token("&&","LO"));
		expression.add(new Token("p3","EX"));
		expression.add(new Token("==","EX"));
		expression.add(new Token("p4","EX"));
		expression.add(new Token("&&","LO"));
		expression.add(new Token("p7","EX"));
		expression.add(new Token("~~","EX"));
		expression.add(new Token("[1,3,5,7,899]","EX"));				
		List<Operation> expectedExecutionSequence = new ArrayList();
		expectedExecutionSequence.add(new Operation(0, "p1", 0, "1000","=="));
		expectedExecutionSequence.add(new Operation(0, "p3", 0, "p4","=="));
		expectedExecutionSequence.add(new Operation(0, null, 1, null,"&&"));
		expectedExecutionSequence.add(new Operation(0, "p7", 0, "[1,3,5,7,899]","~~"));
		expectedExecutionSequence.add(new Operation(2, null, 3, null,"&&"));
		
		
		
		//when
		expressionEvaluatorService.complieSubExpression( executionSequence,  expression);
		
		//then
		assertArrayEquals(expectedExecutionSequence.toArray(),executionSequence.toArray());
	}

	@Test
    void it_should_have_expected_list_compile_expression() {
		
		//given
		List<Operation> executionSequence = new ArrayList();
		String expression = "(p1==1000&&p3==p4&&p7~~[1,3,5,7,899])";
				
		List<Operation> expectedExecutionSequence = new ArrayList();
		expectedExecutionSequence.add(new Operation(0, "p1", 0, "1000","=="));
		expectedExecutionSequence.add(new Operation(0, "p3", 0, "p4","=="));
		expectedExecutionSequence.add(new Operation(0, null, 1, null,"&&"));
		expectedExecutionSequence.add(new Operation(0, "p7", 0, "[1,3,5,7,899]","~~"));
		expectedExecutionSequence.add(new Operation(2, null, 3, null,"&&"));
		
		
		
		//when
		executionSequence = expressionEvaluatorService.compileExpression(expression);
		
		//then
		assertArrayEquals(expectedExecutionSequence.toArray(),executionSequence.toArray());
	}
}
