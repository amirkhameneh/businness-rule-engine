package com.hm.rules.model.dom;

public class Parameter {
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Parameter(String key, long value) {
		super();
		this.key = key;
		this.value = value;
	}
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
	private String key;
	private long value; 
}
