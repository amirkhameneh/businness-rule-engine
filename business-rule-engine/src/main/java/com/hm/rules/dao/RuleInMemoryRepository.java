package com.hm.rules.dao;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hm.rules.model.entity.Rule;
import com.hm.rules.model.entity.RuleRow;
import com.hm.rules.service.ExpressionEvaluatorService;

@Repository
public class RuleInMemoryRepository {
	
	@Autowired
	ExpressionEvaluatorService expressionEvaluatorService;
	
	private static Map<String,Rule> rules = new HashMap(); 
	
	public void saveRule(Rule rule) {
		
		List<RuleRow> sortedRows = rule.getRuleRows().stream()
											.map(ruleRow-> 
													{if (ruleRow.getConditionExpression() == null){
														ruleRow.setConditionExpression(
																expressionEvaluatorService.compileExpression(
																		ruleRow.getConditionalExpressionString()));}
													 if(ruleRow.getPriority()==null) {
														ruleRow.setPriority(0);
													 }
													 return ruleRow;
													}
												).sorted(Comparator.comparingInt(RuleRow::getPriority))
												 .collect(Collectors.toList()); 

		rule.setRuleRows(sortedRows);
		
		rules.put(rule.getId(), rule);
	}

	public void removeRule(String ruleId) {
		rules.remove(ruleId);
	}
	
	public Rule getRule(String ruleId) {
		return rules.get(ruleId);
	}
	
}
