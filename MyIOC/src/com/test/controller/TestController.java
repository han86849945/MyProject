package com.test.controller;

import com.annotation.AutoWare;
import com.annotation.MyComponent;

@MyComponent
public class TestController {
	
	public String aaa = "";
	
	@AutoWare
	public Person per;
	
	public static void main(String[] args) throws ClassNotFoundException {
		Object p = new Person();
		Class clazz = Class.forName("com.test.controller.Person");
		System.out.println(clazz.getCanonicalName());
	}
	
}
