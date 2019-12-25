package com.myspringmvc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能相当于springMVC的RequestMappring
 * @author hanxu
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented//注解是否将包含在JavaDoc中
@Target(value = {ElementType.TYPE, ElementType.METHOD})
public @interface MyRequestMappring {
	String value();
}
