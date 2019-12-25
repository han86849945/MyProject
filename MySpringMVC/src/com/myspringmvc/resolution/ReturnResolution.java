package com.myspringmvc.resolution;

/**
 * 返回结果，目前不支持视图返回
 * @author hanxu
 */
public class ReturnResolution {
	
	private String type;
	
	private String data;
	
	public ReturnResolution(Object obj){
		if(obj == null){
			type = ReturnType.NULL.getValue();
			data = ReturnType.NULL.getValue();
		}else{
			if(obj instanceof Integer || obj instanceof Long || obj instanceof Double){
				type = ReturnType.DATA.getValue();
			}else if(obj instanceof String){
				if(obj.toString().indexOf("/") != -1){
					type = ReturnType.VIEW.getValue();
				}else{
					type = ReturnType.DATA.getValue();
				}
			}else{
				type = ReturnType.OBJECT.getValue();
			}
			data = obj.toString();
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
	
}
