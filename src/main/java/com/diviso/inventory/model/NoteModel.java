package com.diviso.inventory.model;

import java.time.LocalDate;


public class NoteModel {
	private Long id;
	private Long productId;

	public NoteModel(Long id2, LocalDate dateOfCreation2, String matter2) {
		// TODO Auto-generated constructor stub
		id=id2;
		dateOfCreation=dateOfCreation2;
		matter=matter2;
		
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatter() {
		return matter;
	}

	public void setMatter(String matter) {
		this.matter = matter;
	}

	public LocalDate getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(LocalDate dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	private String matter;
	private LocalDate dateOfCreation;
}
