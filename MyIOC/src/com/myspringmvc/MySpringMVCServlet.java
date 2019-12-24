package com.myspringmvc;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author hanxu
 *
 */
@WebServlet("/MySpringMVCServlet")
public class MySpringMVCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MySpringMVCServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		while(es.hasMoreElements()){
			String key = es.nextElement();
			String value = request.getParameter(key);
			System.out.println(key);
			System.out.println(value);
		}
		
	}

}
