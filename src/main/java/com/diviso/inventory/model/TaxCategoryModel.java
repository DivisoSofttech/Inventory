package com.diviso.inventory.model;

import java.util.Set;

import com.diviso.inventory.domain.Tax;

public class TaxCategoryModel {

	private Long id;
	private String name;
	public TaxCategoryModel(Long id2, String description2, String name2) {
		id=id2;
		description=description2;
		name=name2;
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private String description;
}
