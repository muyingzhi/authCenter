package com.tianjian.util;

/**
 * 接口响应页面调用
 *
 */
public class ResponseBean {

	// 响应code
	private String code;
	// 响应是否成功内容
	private String codeDesc;
	// 返回data
	private Object data;

	public ResponseBean() {
	}

	public ResponseBean(String code, String codeDesc, Object data) {
		super();
		this.code = code;
		this.codeDesc = codeDesc;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
