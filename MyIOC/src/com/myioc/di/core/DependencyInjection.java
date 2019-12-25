package com.myioc.di.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

import com.myioc.Exception.NoSuchBeanException;
import com.myioc.annotation.AutoWare;
import com.myioc.ioc.BeanFacotory;

/**
 * 在beanFactory初始化完成后，完成实例相关成员变量的注入，成员变量的注入对象 ，依旧是在beanFactory中查找
 * 只注入public的成员变量
 * @author hanxu
 */
public class DependencyInjection {
	
	/**
	 * 对所有的bean完成成员变量的注入
	 * @author hanxu
	 */
	@SuppressWarnings("rawtypes")
	public void dependencyInjection(Map<String, Object> beanMap) throws IllegalArgumentException, IllegalAccessException, NoSuchBeanException {
		for (String key : beanMap.keySet()) {
			Object obj = beanMap.get(key);
			Class clazz = obj.getClass();
			di(obj, clazz);
		}
	}

	/**
	 * 对有AutoWare的成员变量，注入相应的值.如果更新其他注入方式或注解，修改这个方法
	 * @author hanxu
	 */
	@SuppressWarnings("rawtypes")
	public void di(Object obj, Class clazz) throws IllegalArgumentException, IllegalAccessException, NoSuchBeanException {
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			Annotation[] as = field.getAnnotations();
			for (int j = 0; j < as.length; j++) {
				Annotation a = as[j];
				if ((a instanceof AutoWare)) {
					field.set(obj, BeanFacotory.getBeanFacotory().getObj(field.getType()));
				}
			}
		}
	}

}
