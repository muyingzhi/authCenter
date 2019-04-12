package com.tianjian.hsp.bean;

public class CommConfig {
	private String itemCode = "";
	private String itemName = "";
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public CommConfig(String itemCode, String itemName) {
		super();
		this.itemCode = itemCode;
		this.itemName = itemName;
	}
	public CommConfig() {
		
	}
}
