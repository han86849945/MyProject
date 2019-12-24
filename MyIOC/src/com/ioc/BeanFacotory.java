package com.ioc;

import com.Exception.NoSuchBeanException;
import com.di.core.DependencyInjection;
import com.di.query.Query;
import java.util.HashMap;
import java.util.Map;

public class BeanFacotory {
	private static boolean flag = true;

	public static class BeanFacotoryInstance {
		private static final BeanFacotory beanFacotory = new BeanFacotory();
		private static final Map<String, Object> beanMap = new HashMap<String, Object>();
	}

	public static BeanFacotory getBeanFacotory() {
		if (flag) {
			initBeanFactory();
		}
		return BeanFacotoryInstance.beanFacotory;
	}

	private static void initBeanFactory() {
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

	public Object getObj(String key) throws NoSuchBeanException {
		Object obj = BeanFacotoryInstance.beanMap.get(key);
		if (obj == null) {
			throw new NoSuchBeanException("没有找到这个bean...");
		}
		return obj;
	}

	@SuppressWarnings("rawtypes")
	public Object getObj(Class clazz) throws NoSuchBeanException {
		for (String key : BeanFacotoryInstance.beanMap.keySet()) {
			Object obj = BeanFacotoryInstance.beanMap.get(key);
			if (clazz.isInstance(obj)) {
				return obj;
			}
		}
		throw new NoSuchBeanException("没有找到这个bean...");
	}

	public Object find(Object key, Query query) {
		return query.find(key, BeanFacotoryInstance.beanFacotory);
	}

	public Map<String, Object> getBeanMap() {
		return BeanFacotoryInstance.beanMap;
	}
}
