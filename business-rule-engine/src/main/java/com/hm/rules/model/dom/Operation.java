package com.hm.rules.model.dom;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;



public class Operation {

	enum Operator{ EQUAL , NOTEQUAL,GREATER,GREATEROREQUAL,LESSER,LESSEROREQUAL,CONTAINS,AND,OR}
	
	enum TermType{ REFERENCE , PARAMETER,LONG,STRING,SET}
	




	public TermType getLeftTermType() {
		return leftTermType;
	}

	public void setLeftTermType(TermType leftTermType) {
		this.leftTermType = leftTermType;
	}

	public int getLeftTermIndex() {
		return leftTermIndex;
	}

	public void setLeftTermIndex(int leftTermIndex) {
		this.leftTermIndex = leftTermIndex;
	}

	public String getLeftTermString() {
		return leftTermString;
	}

	public void setLeftTermString(String leftTermString) {
		this.leftTermString = leftTermString;
	}

	public long getLeftTermLong() {
		return leftTermLong;
	}

	public void setLeftTermLong(long leftTermLong) {
		this.leftTermLong = leftTermLong;
	}

	public TermType getRightTermType() {
		return rightTermType;
	}

	public void setRightTermType(TermType rightTermType) {
		this.rightTermType = rightTermType;
	}

	public int getRightTermIndex() {
		return rightTermIndex;
	}

	public void setRightTermIndex(int rightTermIndex) {
		this.rightTermIndex = rightTermIndex;
	}

	public String getRightTermString() {
		return rightTermString;
	}

	public void setRightTermString(String rightTermString) {
		this.rightTermString = rightTermString;
	}

	public long getRightTermLong() {
		return rightTermLong;
	}

	public void setRightTermLong(long rightTermLong) {
		this.rightTermLong = rightTermLong;
	}

	public HashSet<Long> getRightTermSet() {
		return rightTermSet;
	}

	public void setRightTermSet(HashSet<Long> rightTermSet) {
		this.rightTermSet = rightTermSet;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	TermType  leftTermType;
	int 	leftTermIndex;
	String  leftTermString;
	long  leftTermLong;
	
	TermType  rightTermType;
	int 	rightTermIndex;
	String  rightTermString;
	long  rightTermLong;
	HashSet<Long>   rightTermSet;
	
	String operatorString;
	 
	
	
	public Operation(int leftTermIndex, String leftTermString, int rightTermIndex, String rightTermString,
			String operatorString) {
		super();
		this.leftTermIndex = leftTermIndex;
		this.leftTermString = leftTermString;
		this.rightTermIndex = rightTermIndex;
		this.rightTermString = rightTermString;
		this.operatorString = operatorString;
		
		if (leftTermString == null) {
			this.leftTermType = TermType.REFERENCE;
		}else {
			if (leftTermString.charAt(0)=='0'||leftTermString.charAt(0)=='1'||leftTermString.charAt(0)=='2'
				||leftTermString.charAt(0)=='3'	||leftTermString.charAt(0)=='4'||leftTermString.charAt(0)=='5'
				||leftTermString.charAt(0)=='6'||leftTermString.charAt(0)=='7'||leftTermString.charAt(0)=='8'
				||leftTermString.charAt(0)=='9') {
				this.leftTermType = TermType.LONG;
				this.leftTermLong = Long.valueOf(leftTermString);
			}else {
				this.leftTermType = TermType.PARAMETER;
			}
		}
		
		if (rightTermString == null) {
			this.rightTermType = TermType.REFERENCE;
		}else {
			if (rightTermString.charAt(0)=='0'||rightTermString.charAt(0)=='1'||rightTermString.charAt(0)=='2'
				||rightTermString.charAt(0)=='3'	||rightTermString.charAt(0)=='4'||rightTermString.charAt(0)=='5'
				||rightTermString.charAt(0)=='6'||rightTermString.charAt(0)=='7'||rightTermString.charAt(0)=='8'
				||rightTermString.charAt(0)=='9') {
				this.rightTermType = TermType.LONG;
				this.rightTermLong = Long.valueOf(rightTermString);
			}else if(rightTermString.charAt(0)=='['){
				this.rightTermType = TermType.SET;
				this.rightTermSet =(HashSet<Long>) Arrays.asList(rightTermString.substring(1,rightTermString.length()-2 ).split(","))
										.stream().map(Long::valueOf).collect(Collectors.toSet());
			}else {
				this.rightTermType = TermType.PARAMETER;
			}
		}
		
		switch(operatorString) {
		  case "==":
		    this.operator = Operator.EQUAL;
		    break;
		  case "!=":
			    this.operator = Operator.NOTEQUAL;
		    break;
		  case ">=":
			    this.operator = Operator.GREATEROREQUAL;
		  case "<=":
			    this.operator = Operator.LESSEROREQUAL;
		  case ">>":
			    this.operator = Operator.GREATER;
		  case "<<":
			    this.operator = Operator.LESSER;
		  case "~~":
			    this.operator = Operator.CONTAINS;
		  case "&&":
			    this.operator = Operator.AND;
		  case "||":
			    this.operator = Operator.OR;			    
		}
		
	}

	public String getOperatorString() {
		return operatorString;
	}

	public void setOperatorString(String operatorString) {
		this.operatorString = operatorString;
	}

	Operator operator;

	
}