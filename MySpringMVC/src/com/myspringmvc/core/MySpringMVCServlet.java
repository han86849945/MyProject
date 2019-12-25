package com.myspringmvc.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myioc.ioc.BeanFacotory;
import com.myspringmvc.handle.Handle;
import com.myspringmvc.handle.HanleFactory;
import com.myspringmvc.injection.FieldInjection;
import com.myspringmvc.resolution.Resolution;
import com.myspringmvc.resolution.ReturnResolution;

/**
 * 核心的servlet，拦截所有请求并处理，配置这个servlet即可使用
 *  <servlet>
 *		<servlet-name>hello</servlet-name>
 *		<servlet-class>com.myspringmvc.core.MySpringMVCServlet</servlet-class>
 *		<load-on-startup>1</load-on-startup>
 *	</servlet>
 *	<servlet-mapping>
 *		<servlet-name>hello</servlet-name>
 *		<url-pattern>/</url-pattern>
 *	</servlet-mapping>
 * @author hanxu
 */
public class MySpringMVCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MySpringMVCServlet() {
        super();
    }
    
    /**
     * 初始化处理器工厂HanleFactory
     */
    @Override
    public void init(ServletConfig config) throws ServletException{
    	System.out.println("MySpringMVCServlet init ...");
    	HanleFactory hf = HanleFactory.getHanleFactory();
    	hf.init(((BeanFacotory)config.getServletContext().getAttribute("beanFactory")).getBeanMap());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
	}
	
	/**
	 * 完成拦截请求的相关处理及返回
	 * @author hanxu
	 */
	@SuppressWarnings("static-access")
	public void doService(HttpServletRequest request, HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String uri = request.getRequestURI();
		HanleFactory hf = HanleFactory.getHanleFactory();
		try {
			uri = uri.replace(request.getContextPath(), "");
			System.out.println(uri);
			//查找合适的处理器
			Handle hb = hf.getMethod(uri);
			//使用FieldInjection完成参数的映射
			Object[] objs = FieldInjection.injection(request, response, hb);
			//执行处理器
			Object obj = hb.invoke(objs);
			//处理返回后的结果
			ReturnResolution rr = new ReturnResolution(obj);
			//根据处理结果，返回到页面
			Resolution.resolution(request, response, rr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
