package com.myspringmvc.handle;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.myspringmvc.annotation.MyRequestMappring;

public class HanleFactory {
	
	private HanleFactory(){}
	
	public static class HanleFactoryInstance{
		public static final HanleFactory hf = new HanleFactory();
		public static final Map<String, Handle> handleMap = new HashMap<String, Handle>();
	}
	
	public static HanleFactory getHanleFactory(){
		return HanleFactoryInstance.hf;
	}
	
	public static Handle getMethod(String key){
		return HanleFactoryInstance.handleMap.get(key);
	}
	
	public void init(Map<String, Object> beanMap){
		String classuri = "";
		String methoduri = "";
		for(String key : beanMap.keySet()){
			Object obj = beanMap.get(key);
			Annotation[] as = obj.getClass().getAnnotations();
			for(int i=0;i<as.length;i++){
				Annotation a = as[i];
				if(a instanceof MyRequestMappring){
					classuri = ((MyRequestMappring) a).value();
					Method[] ms = obj.getClass().getMethods();
					for(int j = 0;j<ms.length;j++){
						Method m = ms[j];
						Annotation[] as2 = m.getAnnotations();
						for(int k=0;k<as2.length;k++){
							Annotation a2 = as2[k];
							if(a2 instanceof MyRequestMappring){
								methoduri = ((MyRequestMappring) a2).value();
								System.out.println(classuri+methoduri);
								Handle hd = new Handle();
								hd.setM(m);
								hd.setObj(obj);
//								hb.setParamNames(AsmUtil.getMethodParamNames(m));
								HanleFactoryInstance.handleMap.put(classuri+methoduri, hd);
								break;
							}
						}
					}
					break;
				}
			}
		}
	}
	
}
