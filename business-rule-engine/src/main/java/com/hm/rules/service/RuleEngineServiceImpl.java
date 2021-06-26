package com.hm.rules.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.hm.rules.model.dom.Parameter;
import com.hm.rules.model.dto.RuleInMemoryRepository;
import com.hm.rules.model.entity.Rule;
import com.hm.rules.model.entity.RuleRow;
import com.hm.rules.model.entity.Zone;

public class RuleEngineServiceImpl implements RuleEngineService {
	
	@Autowired
	RuleInMemoryRepository ruleInMemoryRepository;
	
	@Override
	public List<RuleRow> getMatchedRuleRows(String ruleId, List<Parameter> parametersList) {
		Map<String, Long> parameters = 
				parametersList.stream().collect(Collectors.toMap(Parameter::getKey, Parameter::getValue));
		
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void SaveRule(Rule rule) {
		ruleInMemoryRepository.saveRule(rule);
		
	}

	@Override
	public void removeRule(String ruleId) {
		ruleInMemoryRepository.removeRule(ruleId);
		
	}

	@Override
	public List<Rule> getRules(String zoneId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addRuleRow(RuleRow ruleRow) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeRuleRow(RuleRow ruleRow) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RuleRow> getRuleRows(String ruleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveZone(Zone zone) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeZone(String zoneId) {
		// TODO Auto-generated method stub
		
	}

}
