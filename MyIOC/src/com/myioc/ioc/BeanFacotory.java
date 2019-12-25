package com.myioc.ioc;

import java.util.HashMap;
import java.util.Map;

import com.myioc.Exception.NoSuchBeanException;
import com.myioc.di.core.DependencyInjection;
import com.myioc.di.query.Query;

/**
 * 单例的工厂类，存放所有初始化后的bean类
 * @author hanxu
 */
public class BeanFacotory {
	private static boolean flag = true;

	public static class BeanFacotoryInstance {
		//工厂实例
		private static final BeanFacotory beanFacotory = new BeanFacotory();
		private static final Map<String, Object> beanMap = new HashMap<String, Object>();
	}

	/**
	 * 返回工厂实例
	 * @author hanxu
	 */
	public static BeanFacotory getBeanFacotory() {
		if (flag) {
			initBeanFactory();
		}
		return BeanFacotoryInstance.beanFacotory;
	}

	/**
	 * 工厂在这里初始化
	 * @author hanxu
	 */
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

	/**
	 * 工厂类实际是一个map对象，按照key查找
	 * @author hanxu
	 */
	public Object getObj(String key) throws NoSuchBeanException {
		Object obj = BeanFacotoryInstance.beanMap.get(key);
		if (obj == null) {
			throw new NoSuchBeanException("没有找到这个bean...");
		}
		return obj;
	}

	/**
	 * 按对象类型查找
	 * @author hanxu
	 */
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

	/**
	 * 自定义查找bean的方法，实现Query，重写Query里的find方法即可
	 * @author hanxu
	 */
	public Object find(Object key, Query query) {
		return query.find(key, BeanFacotoryInstance.beanFacotory);
	}

	/**
	 * 获取存放bean的map对象
	 * @author hanxu
	 */
	public Map<String, Object> getBeanMap() {
		return BeanFacotoryInstance.beanMap;
	}
}
