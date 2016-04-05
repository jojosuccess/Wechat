package com.weichat.translation;

import java.util.List;

public class TranslateResult {
	private String to;
	
	private String from;
	
	private List<ResultPair> trans_result; 
	
	public String getTo() {
		return to;
	}
	
	public void setTo(String to) {
		this.to = to;
	}
	
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public List<ResultPair> getTransResult() {
		return trans_result;
	}
	
	public void setTransResult(List<ResultPair> trans_result) {
		this.trans_result = trans_result;
	}
}

