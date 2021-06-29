package com.hm.rules.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hm.rules.dao.RuleInMemoryRepository;
import com.hm.rules.model.dom.Operation;
import com.hm.rules.model.dom.Parameter;
import com.hm.rules.model.entity.Rule;
import com.hm.rules.model.entity.RuleRow;
import com.hm.rules.model.entity.Zone;

@SpringBootTest
public class RuleEngineServiceImplTest {
	@Autowired
	RuleInMemoryRepository ruleInMemoryRepository;
	
	@Autowired
	RuleEngineService ruleEngineService;
	
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
	
	@Test
    void it_should_return_1_in_matching_rules() {
		
		//given
		Rule rule = new Rule();
		List<RuleRow> matchedRuleRows= new ArrayList();
		List<RuleRow> ruleRows= new ArrayList();
		
		RuleRow ruleRow1 = new RuleRow();
		ruleRow1.setConditionalExpressionString("((p1>>1000&&(p2!=50&&p3<=70))||p5~~[300,501,700])");
		ruleRow1.setPriority(1);
		ruleRow1.setActionExpressionString("Action 1");
		ruleRow1.setRule(rule);
		ruleRow1.setId("1");
		
		RuleRow ruleRow2 = new RuleRow();
		ruleRow2.setConditionalExpressionString("((p1>>1000&&(p2!=50&&p3<=1))||p51~~[300,501,700])");
		ruleRow2.setPriority(1);
		ruleRow2.setActionExpressionString("Action 2");
		ruleRow2.setRule(rule);
		ruleRow2.setId("2");
		
		ruleRows.add(ruleRow1);
		ruleRows.add(ruleRow2);
		rule.setId("1");
		rule.setZone(new Zone());
		rule.setRuleRows(ruleRows);
		
		ruleInMemoryRepository.saveRule(rule);
		
		List<Parameter> parameters = new ArrayList();
		parameters.add(new Parameter("p1", 1001L));
		parameters.add(new Parameter("p2", 51L));
		parameters.add(new Parameter("p3", 70L));
		parameters.add(new Parameter("p5", 500L));


		//when 
		matchedRuleRows = ruleEngineService.getMatchedRuleRows("1", parameters);
		
		//then
		assertEquals(matchedRuleRows.get(0).getId(), "1");
	}	
	
}
