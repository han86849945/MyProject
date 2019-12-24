package com.di;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

import com.Exception.NoSuchBeanException;
import com.annotation.AutoWare;
import com.ioc.BeanFacotory;

public class DependencyInjection {
	
	@SuppressWarnings("rawtypes")
	public void dependencyInjection(Map<String, Object> beanMap) throws IllegalArgumentException, IllegalAccessException, NoSuchBeanException{
		for(String key : beanMap.keySet()){
			Object obj = beanMap.get(key);
			Class clazz = obj.getClass();
			di(obj, clazz);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void di(Object obj, Class clazz) throws IllegalArgumentException, IllegalAccessException, NoSuchBeanException{
		Field[] fields = clazz.getDeclaredFields();
		for(int i=0;i<fields.length;i++){
			Field field = fields[i];
			Annotation[] as = field.getAnnotations();
			for(int j=0;j<as.length;j++){
				Annotation a = as[j];
				if(a instanceof AutoWare){
					field.set(obj, BeanFacotory.getObj(field.getType()));
				}
			}
		}
	}
	
}
