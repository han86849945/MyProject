package com.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 /**
 * 扫描的标识
 * @author hanxu
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(value = {ElementType.TYPE})
public @interface MyDao {
	
}









