package com.myspringmvc.resolution;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用来判断Handle处理器返回的结果是什么类型
 * @author hanxu
 */
public class Resolution {
	
	public static void resolution(HttpServletRequest request, HttpServletResponse response, ReturnResolution rr){
		String type = rr.getType();
		String data = rr.getData();
		PrintWriter out = null;
		try {
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			if( ReturnType.DATA.getValue().equals(type) || 
				ReturnType.NULL.getValue().equals(type) || 
				ReturnType.OBJECT.getValue().equals(type)){
				out.print(data);
				out.flush();
			}else{
				out.print("这是一个视图..." + data);
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	
}
