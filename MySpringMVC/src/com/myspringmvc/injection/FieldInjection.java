package com.myspringmvc.injection;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myspringmvc.handle.HandleBean;
import com.myspringmvc.util.AsmUtil;

/**
 * 根据参数列表和方法参数，完成参数的注入
 * @author hanxu
 */
public class FieldInjection {
	
	public static Object[] injection(HttpServletRequest request, HttpServletResponse response, HandleBean hb){
		Method method = hb.getM();
		Class[] clases = method.getParameterTypes();
		Object[] objs = new Object[clases.length];
		String[] paramnames = new String[clases.length];
		try {
			paramnames = AsmUtil.getMethodParamNames(method);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map<Integer, Object> paramMap = new HashMap<Integer, Object>();
		
		//是否需要request和response
		for (int i=0;i<clases.length;i++) {
			Class class1 = clases[i];
			if(class1.isInstance(request)){
				objs[i] = request;
				continue;
			}
			if(class1.isInstance(response)){
				objs[i] = response;
				continue;
			}
			String param = paramnames[i];
			String value = request.getParameter(param);
			if(value == null || "".equals(value))
				continue;
			objs[i] = value;
		}
		return objs;
	}
	
}
