package com.hm.rules.service;


import java.util.List;

import com.hm.rules.model.dom.Parameter;
import com.hm.rules.model.entity.Rule;
import com.hm.rules.model.entity.RuleRow;
import com.hm.rules.model.entity.Zone;

public interface RuleEngineService {
	
	List<RuleRow> getMatchedRuleRows(String ruleId,List<Parameter> parametersList);
	
	
	void SaveRule(Rule rule);
	
	void removeRule(String ruleId);
	
	List<Rule> getRules(String zoneId);
	
	
	
	void addRuleRow(RuleRow ruleRow);
	
	void removeRuleRow(RuleRow ruleRow);
	
	List<RuleRow> getRuleRows(String ruleId);
	
	
	
	void saveZone(Zone zone);
	
	void removeZone(String zoneId);
	
	
}
