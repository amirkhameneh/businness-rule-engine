package com.hm.rules.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hm.rules.model.entity.Rule;


public interface RuleRepository extends MongoRepository<Rule, String>{

}
