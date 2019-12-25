package com.myioc.Exception;

/**
 * 查询bean失败时抛出的异常
 * @author hanxu
 */
public class NoSuchBeanException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoSuchBeanException(String message) {
		super(message);
	}
	
	
	
}
