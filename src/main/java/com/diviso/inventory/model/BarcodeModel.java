package com.diviso.inventory.model;


public class BarcodeModel {
	private Long id;
	private String code;
	public BarcodeModel(Long id2, String code2, String description2) {
		id=id2;
		code=code2;
		description=description2;
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private String type;
	private String description;

}
