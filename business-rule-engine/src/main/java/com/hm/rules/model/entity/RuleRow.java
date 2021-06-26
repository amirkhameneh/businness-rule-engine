package com.hm.rules.model.entity;

import java.util.Date;
import java.util.List;

import com.hm.rules.model.dom.Operation;

public class RuleRow {
	
	private String id;

	private Rule rule;
	
	private Date fromDate;
	
	private Date toDate;
	
	private Integer priority;
	
	private String conditionalExpressionString;
	
	private List<Operation> conditionExpression;
	
	private String ActionExpressionString;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getConditionalExpressionString() {
		return conditionalExpressionString;
	}

	public void setConditionalExpressionString(String conditionalExpressionString) {
		this.conditionalExpressionString = conditionalExpressionString;
	}

	public List<Operation> getConditionExpression() {
		return conditionExpression;
	}

	public void setConditionExpression(List<Operation> conditionExpression) {
		this.conditionExpression = conditionExpression;
	}

	public String getActionExpressionString() {
		return ActionExpressionString;
	}

	public void setActionExpressionString(String actionExpressionString) {
		ActionExpressionString = actionExpressionString;
	}
}
