package com.diviso.inventory.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class StockLineModel {
	@JsonProperty("buyPrice")
	private Double buyPrice = null;

	@JsonProperty("grossProfit")
	private Double grossProfit = null;

	@JsonProperty("id")
	private Long id = null;

	@JsonProperty("infrastructureId")
	private Long infrastructureId = null;

	@JsonProperty("locationId")
	private String locationId = null;

	@JsonProperty("margin")
	private Double margin = null;

	private ProductModel product;

	public StockLineModel(Long id2, String reference2, Double buyPrice2, Double grossProfit2,
			Double sellPriceExclusive2, Double sellPriceInclusive2, Double margin2, Long infrastructureId2,
			String locationId2, ProductModel productModel, Double units2, UomModel uomModel) {
		// TODO Auto-generated constructor stub
		id=id2;
		buyPrice=buyPrice2;
		reference=reference2;
		grossProfit=grossProfit2;
		sellPriceExclusive=sellPriceExclusive2;
		sellPriceInclusive=sellPriceInclusive2;
		margin=margin2;
		infrastructureId=infrastructureId2;
		locationId=locationId2;
		this.product=productModel;
		units=units2;
		uom=uomModel;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Double getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(Double grossProfit) {
		this.grossProfit = grossProfit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInfrastructureId() {
		return infrastructureId;
	}

	public void setInfrastructureId(Long infrastructureId) {
		this.infrastructureId = infrastructureId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public Double getMargin() {
		return margin;
	}

	public void setMargin(Double margin) {
		this.margin = margin;
	}

	

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Double getSellPriceExclusive() {
		return sellPriceExclusive;
	}

	public void setSellPriceExclusive(Double sellPriceExclusive) {
		this.sellPriceExclusive = sellPriceExclusive;
	}

	public Double getSellPriceInclusive() {
		return sellPriceInclusive;
	}

	public void setSellPriceInclusive(Double sellPriceInclusive) {
		this.sellPriceInclusive = sellPriceInclusive;
	}

	public Long getSupplierRef() {
		return supplierRef;
	}

	public void setSupplierRef(Long supplierRef) {
		this.supplierRef = supplierRef;
	}

	public Double getUnits() {
		return units;
	}

	public void setUnits(Double units) {
		this.units = units;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	@JsonProperty("reference")
	private String reference = null;

	@JsonProperty("sellPriceExclusive")
	private Double sellPriceExclusive = null;

	@JsonProperty("sellPriceInclusive")
	private Double sellPriceInclusive = null;

	@JsonProperty("supplierRef")
	private Long supplierRef = null;

	@JsonProperty("units")
	private Double units = null;
	
	private UomModel uom;

	public UomModel getUom() {
		return uom;
	}

	public void setUom(UomModel uom) {
		this.uom = uom;
	}

}
