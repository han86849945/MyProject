package com.myspringmvc.core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Exception.NoSuchBeanException;
import com.ioc.BeanFacotory;
import com.myspringmvc.handle.HandleBean;
import com.myspringmvc.handle.HanleFactory;
import com.myspringmvc.injection.FieldInjection;

/**
 * 
 * @author hanxu
 *
 */
public class MySpringMVCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MySpringMVCServlet() {
        super();
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException{
    	System.out.println("MySpringMVCServlet init ...");
    	HanleFactory hf = HanleFactory.getHanleFactory();
    	hf.init(((BeanFacotory)config.getServletContext().getAttribute("beanFacotory")).getBeanMap());
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
	
	public void doService(HttpServletRequest request, HttpServletResponse response){
		String uri = request.getRequestURI();
		String method = request.getMethod();
		String contentType = request.getContentType();
		Enumeration<String> es = request.getParameterNames();
		System.out.println(uri);
		System.out.println(method);
		System.out.println(contentType);
		System.out.println(request.getContextPath());
		while(es.hasMoreElements()){
			String key = es.nextElement();
			String value = request.getParameter(key);
			System.out.println(key);
			System.out.println(value);
		}
		
		HanleFactory hf = HanleFactory.getHanleFactory();
		try {
			uri = uri.replace(request.getContextPath(), "");
			System.out.println(uri);
			HandleBean hb = hf.getMethod(uri);
			hb.getM().invoke(hb.getObj(), FieldInjection.injection(request, response, hb));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
