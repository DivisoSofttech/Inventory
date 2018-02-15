package com.diviso.inventory.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.diviso.inventory.service.dto.LabelDTO;






public class ProductModel {
	
	private BarcodeModel barcode;
	private Long id;
	public ProductModel(Long id2,String name2,boolean display, LocalDate dateOfExpiry2, LocalDate dateOfMfd2, byte[] image2,
			String imageContentType2, String description2, Double maximumStockLevel2, String searchkey2, String sku2,
			String mpn2, Double reOrderLevel2, String reference2) {
		// TODO Auto-generated constructor stub
		id=id2;
		name=name2;
		dateOfExpiry=dateOfExpiry2;
		dateOfMfd=dateOfMfd2;
		image=image2;
		imageContentType=imageContentType2;
		description=description2;
		maximumStockLevel=maximumStockLevel2;
		searchkey=searchkey2;
		sku=sku2;
		mpn=mpn2;
		reOrderLevel=reOrderLevel2;
		reference=reference2;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private String reference;
	private String name;
	private String description;
	private StatusModel status;
	private List<NoteModel> notes;
	public List<NoteModel> getNotes() {
		return notes;
	}
	public void setNotes(List<NoteModel> notes) {
		this.notes = notes;
	}
	public BarcodeModel getBarcode() {
		return barcode;
	}
	public void setBarcode(BarcodeModel barcode) {
		this.barcode = barcode;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
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
	public Double getMaximumStockLevel() {
		return maximumStockLevel;
	}
	public StatusModel getStatus() {
		return status;
	}
	public void setStatus(StatusModel status) {
		this.status = status;
	}
	public void setMaximumStockLevel(Double maximumStockLevel) {
		this.maximumStockLevel = maximumStockLevel;
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
	public String getSearchkey() {
		return searchkey;
	}
	public void setSearchkey(String searchkey) {
		this.searchkey = searchkey;
	}
	public Boolean getDisplay() {
		return display;
	}
	public void setDisplay(Boolean display) {
		this.display = display;
	}
	public LocalDate getDateOfMfd() {
		return dateOfMfd;
	}
	public void setDateOfMfd(LocalDate dateOfMfd) {
		this.dateOfMfd = dateOfMfd;
	}
	public LocalDate getDateOfExpiry() {
		return dateOfExpiry;
	}
	public void setDateOfExpiry(LocalDate dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getMpn() {
		return mpn;
	}
	public void setMpn(String mpn) {
		this.mpn = mpn;
	}
	public Double getReOrderLevel() {
		return reOrderLevel;
	}
	public void setReOrderLevel(Double reOrderLevel) {
		this.reOrderLevel = reOrderLevel;
	}
	public CategoryModel getCategoryModel() {
		return categoryModel;
	}
	public void setCategoryModel(CategoryModel categoryModel) {
		this.categoryModel = categoryModel;
	}
	public TaxCategoryModel getTaxCategoryModel() {
		return taxCategoryModel;
	}
	public void setTaxCategoryModel(TaxCategoryModel taxCategoryModel) {
		this.taxCategoryModel = taxCategoryModel;
	}
	public List<LabelModel> getLabels() {
		return labels;
	}
	public void setLabels(List<LabelModel> labels) {
		this.labels = labels;
	}
	private Double maximumStockLevel;
	private byte[] image;
	private String imageContentType; 
	private String searchkey;
	private Boolean display;
	private LocalDate dateOfMfd;
	private LocalDate dateOfExpiry;
	private String sku;
	private String mpn;
	private Double reOrderLevel;
	private CategoryModel categoryModel;
	private TaxCategoryModel taxCategoryModel;
	
	 private List<LabelModel> labels = new ArrayList<LabelModel>();

}
