package com.di.query;

import com.ioc.BeanFacotory;

public interface Query {
	
	public static final BeanFacotory beanFacotory = null;
	  
	public abstract Object find(Object paramObject, BeanFacotory paramBeanFacotory);
	
}
