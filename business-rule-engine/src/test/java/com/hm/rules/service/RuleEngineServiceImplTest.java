package com.hm.rules.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hm.rules.model.dom.Operation;
import com.hm.rules.model.dto.RuleInMemoryRepository;
import com.hm.rules.model.entity.Rule;
import com.hm.rules.model.entity.RuleRow;

@SpringBootTest
public class RuleEngineServiceImplTest {
	@Autowired
	RuleInMemoryRepository ruleInMemoryRepository;
	
	@Test
	void it_should_set_expression_sequence_when_it_is_null() {
		//Given 
		Rule rule= new Rule();
		List<RuleRow> rulerows = new ArrayList();
		rule.setId("123");
		
		RuleRow ruleRow = new RuleRow();
		ruleRow.setConditionalExpressionString("(p1==1000&&p3==p4&&p7~~[1,3,5,7,899])");
		ruleRow.setActionExpressionString("Do ...");
		rulerows.add(ruleRow);
		rule.setRuleRows(rulerows);
		/*ruleRow = new RuleRow();
		ruleRow.setConditionalExpressionString("(p1==1000&&p3==p4");
		ruleRow.setActionExpressionString("Do ...");
		rulerows.add(ruleRow);*/
		
		List<Operation> expectedExecutionSequence = new ArrayList();
		expectedExecutionSequence.add(new Operation(0, "p1", 0, "1000","=="));
		expectedExecutionSequence.add(new Operation(0, "p3", 0, "p4","=="));
		expectedExecutionSequence.add(new Operation(0, null, 1, null,"&&"));
		expectedExecutionSequence.add(new Operation(0, "p7", 0, "[1,3,5,7,899]","~~"));
		expectedExecutionSequence.add(new Operation(2, null, 3, null,"&&"));		
		
		
		//When
		ruleInMemoryRepository.saveRule(rule);
		//Then
		assertArrayEquals(ruleInMemoryRepository.getRule("123").getRuleRows().get(0).getConditionExpression().toArray(),expectedExecutionSequence.toArray());
		
		
	}
	
}
