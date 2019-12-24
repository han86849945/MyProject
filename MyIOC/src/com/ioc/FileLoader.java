package com.ioc;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.annotation.MyComponent;
import com.annotation.MyController;
import com.annotation.MyDao;
import com.annotation.MyService;

/**
 * 扫描全工程下的java文件，将有注解的添加到beanFactory
 * @author hanxu
 */
public class FileLoader {
	
	private List<File> fileList = new ArrayList<File>();
	private List<String> classList = new ArrayList<String>();
	private Map<String, Object> map = null;
	private String classPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	
	public FileLoader(Map<String, Object> map){
		this.map = map;
	}
	
	public void init(){
		try {
			classPath = classPath.substring(1, classPath.length());
			getAllFile(classPath, fileList);
			getAllFileName(fileList, "class");
			check();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, Object> getObjects(){
		return this.map;
	}
	
	/**
	 * 获得classpath路径下所有文件
	 * @author hanxu
	 * @throws IOException 
	 */
	private List<File> getAllFile(String url, List<File> fileList) throws IOException{
		File file = new File(url);
		File[] files = file.listFiles();
		for (File file2 : files) {
			if(file2.isDirectory())
				getAllFile(file2.getCanonicalPath(), fileList);
			fileList.add(file2);
		}
		return fileList;
	}
	
	/**
	 * 获得classpath路径下所有文件的名字
	 * @author hanxu
	 * @throws IOException 
	 */
	private void getAllFileName(List<File> list, String check) throws IOException{
		for (File file : list) {
			String fileName = file.getName();
			String[] fileNames = StringUtils.split(fileName, ".");
			if(fileNames.length < 2)
				continue;//没有扩展名
			if(check.equals(fileNames[fileNames.length-1]))
				classList.add(file.getCanonicalPath());
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void check() throws ClassNotFoundException{
		for (String string : classList) {
			String allPackageName = urlToPackage(string);
			try {
				Class clazz = Class.forName(allPackageName);
				Annotation[] as = clazz.getAnnotations();
				
				for(int i=0;i<as.length;i++){
					Annotation a = as[0];
					if(a instanceof MyComponent || a instanceof MyController || a instanceof MyService || a instanceof MyDao){
						Object obj = clazz.newInstance();
						map.put(clazz.getSimpleName(), obj);
						System.out.println(allPackageName);
						break;
					}
				}
			} catch (Exception e) {
			}
		}
	}

	private String urlToPackage(String string) {
		String url = StringUtils.replace(string, "\\", "/");
		url = StringUtils.replace(url, classPath, "");
		String pack = StringUtils.split(url, ".")[0];
		String packageV = StringUtils.replace(pack, "/", ".");
		return packageV;
	}
	
}




