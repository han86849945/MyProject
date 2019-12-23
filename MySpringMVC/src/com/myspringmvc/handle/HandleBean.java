package com.myspringmvc.handle;

import java.lang.reflect.Method;

public class HandleBean {
	
	public Object obj;
	public Method m;
	public String[] paramNames;
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public Method getM() {
		return m;
	}
	public void setM(Method m) {
		this.m = m;
	}
	public String[] getParamNames() {
		return paramNames;
	}
	public void setParamNames(String[] paramNames) {
		this.paramNames = paramNames;
	}
	
	
}
