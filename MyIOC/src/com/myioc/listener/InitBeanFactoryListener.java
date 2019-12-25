package com.myioc.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.myioc.ioc.BeanFacotory;

/**
 * 配置这个监听器，即可使用ioc功能
 *  <listener>
 *		<listener-class>com.listener.InitBeanFactoryListener</listener-class>
 *	</listener>
 * @author hanxu
 */
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
