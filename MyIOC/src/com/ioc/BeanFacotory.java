package com.ioc;


import java.util.HashMap;
import java.util.Map;

import com.Exception.NoSuchBeanException;
import com.di.DependencyInjection;

public class BeanFacotory {
	
	private static boolean flag = true;
	
	public static class BeanFacotoryInstance{
		private static final BeanFacotory beanFacotory = new BeanFacotory();
		private static final Map<String, Object> beanMap = new HashMap<String, Object>();
	}
	
	private BeanFacotory(){}
	
	public static BeanFacotory getBeanFacotory(){
		if(flag)
			initBeanFactory();
		return BeanFacotoryInstance.beanFacotory;
	}
	
	private static void initBeanFactory(){
		flag = false;
		FileLoader fl = new FileLoader(BeanFacotoryInstance.beanMap);
		fl.init();
		DependencyInjection di = new DependencyInjection();
		try {
			di.dependencyInjection(BeanFacotoryInstance.beanMap);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static Object getObj(String key) throws NoSuchBeanException{
		Object obj = BeanFacotoryInstance.beanMap.get(key);
		if(obj == null)
			throw new NoSuchBeanException("没有找到...");
		else
			return obj;
	}
	
	@SuppressWarnings("rawtypes")
	public static Object getObj(Class clazz) throws NoSuchBeanException{
		for(String key : BeanFacotoryInstance.beanMap.keySet()){
			Object obj = BeanFacotoryInstance.beanMap.get(key);
			if(clazz.isInstance(obj))
				return obj;
		}
		throw new NoSuchBeanException("没有找到...");
	}
	
	
	
}
