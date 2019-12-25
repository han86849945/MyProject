package com.myspringmvc.handle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 对应每个请求的处理器，在HandleFactory初始化时被创建,每个请求创建一个
 * @author hanxu
 */
public class Handle {
	
	public Object invoke(Object[] objs) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return m.invoke(obj, objs);
	}
	
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
