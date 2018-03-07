package com.diviso.inventory.model;



public class StatusModel {
	
	  private String description ;
	  public StatusModel() {}
	  
	  public StatusModel(Long id2, String description2, String name2, String reference2) {
		// TODO Auto-generated constructor stub
		  id=id2;
		  description=description2;
		  name=name2;
		  reference=reference2;
		  
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	private Long id ;

	 
	  private String name ;

	  private String reference ;
}
