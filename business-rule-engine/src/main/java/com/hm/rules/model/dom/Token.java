package com.hm.rules.model.dom;

public class Token {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Token(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}
	String name;
	String type;
		
}