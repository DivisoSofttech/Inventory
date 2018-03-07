package com.diviso.inventory.model;


import com.fasterxml.jackson.annotation.JsonProperty;



public class LabelModel   {
  @JsonProperty("description")
  private String description = null;

  @JsonProperty("id")
  private Long id = null;
  public LabelModel() {}
  public LabelModel(Long id2, String description2, String name2) {
	// TODO Auto-generated constructor stub
	  id=id2;
	  description=description2;
	  name=name2;
	  
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

@JsonProperty("name")
  private String name = null;

  
}

