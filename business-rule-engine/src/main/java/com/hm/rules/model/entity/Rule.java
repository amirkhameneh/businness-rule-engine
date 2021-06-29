package com.hm.rules.model.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rule")
public class Rule {
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMatchingMethod() {
		return matchingMethod;
	}

	public void setMatchingMethod(String matchingMethod) {
		this.matchingMethod = matchingMethod;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	} 

	public List<RuleRow> getRuleRows() {
		return ruleRows;
	}

	public void setRuleRows(List<RuleRow> ruleRows) {
		this.ruleRows = ruleRows;
	}
	@Id
	private String id;
	
	private String name;
	
	private String matchingMethod;
	
	private Zone zone;
	
	private List<RuleRow> ruleRows;
	
}
