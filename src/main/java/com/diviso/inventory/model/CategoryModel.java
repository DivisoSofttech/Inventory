package com.diviso.inventory.model;



public class CategoryModel {
	private Long id;
	private String name;
	private byte[] image;

	public CategoryModel(Long id2, String description2, byte[] image2, String imageContentType2, String name2) {
		// TODO Auto-generated constructor stub
		id=id2;
		description=description2;
		image=image2;
		imageContentType=imageContentType2;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String imageContentType;
	private Boolean visible;
	private String description;
}
