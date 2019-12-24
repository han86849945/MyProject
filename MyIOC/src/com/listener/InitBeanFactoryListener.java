package com.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ioc.BeanFacotory;

public class InitBeanFactoryListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//初始化beanfactory
		BeanFacotory bf = BeanFacotory.getBeanFacotory();
		arg0.getServletContext().setAttribute("beanFactory", bf);
	}

}
