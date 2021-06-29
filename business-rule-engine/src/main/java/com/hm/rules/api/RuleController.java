package com.hm.rules.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hm.rules.model.entity.Rule;
import com.hm.rules.service.RuleEngineService;



@RestController
public class RuleController extends ApiController {

	@Autowired
	RuleEngineService ruleEngineService;
	
    @PostMapping(value = "/saverule")
    public ResponseEntity<String> saveRule(@RequestBody  Rule rule) {
    	ruleEngineService.SaveRule(rule);
        return new ResponseEntity<>(rule.getId(), HttpStatus.OK);
    }
    
    @PostMapping(value = "/getmatchedrows")
    public ResponseEntity<String> getMatchedRows(@RequestBody  Rule rule) {
    	ruleEngineService.SaveRule(rule);
        return new ResponseEntity<>(rule.getId(), HttpStatus.OK);
    }
    
    



    @GetMapping(value = "/product/count")
    public ResponseEntity<Long> getAllCount(@RequestParam(value = "rule", required = false) String rule) {
       // Long productCount = productService.getAllCount(category, minPrice, maxPrice, color);
        return new ResponseEntity<>(10L, HttpStatus.OK);
    }

}
