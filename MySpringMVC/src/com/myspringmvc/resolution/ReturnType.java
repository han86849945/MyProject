package com.myspringmvc.resolution;

/**
 * 返回类型view视图页面，data直接返回（字符或数字）,object对象，调用toString返回，null什么都不返回
 * @author hanxu
 */
public enum ReturnType {
	
	VIEW("view"),DATA("data"),OBJECT("object"),NULL("什么都没有...");
	
	private String value;
	public String getValue(){
		return value;
	}
	private ReturnType(String value){
		this.value=value;
	}
	
}
