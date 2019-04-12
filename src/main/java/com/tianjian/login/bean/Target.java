package com.tianjian.login.bean;

/**
 * @author muyz
 *         Created on 2018/5/3
 */
public class Target {
    private String code;
    private String name;
    private String title;
    private String type;

    
    private String pageUrl;
    
    
    public Target() {
    }

    public Target(String code, String name){
        this.code = code;
        this.name = name;
    }
    public Target(String code, String name,String title){
        this.code = code;
        this.name = name;
        this.title = title;
    }
    
    
    
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
    
    
}
