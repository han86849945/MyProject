package com.myioc.di.query;

import com.myioc.ioc.BeanFacotory;

/**
 * 自定义查询接口，如果有需要，实现并重写find即可
 * @author hanxu
 */
public interface Query {
	
	public static final BeanFacotory beanFacotory = null;
	  
	public abstract Object find(Object paramObject, BeanFacotory paramBeanFacotory);
	
}
