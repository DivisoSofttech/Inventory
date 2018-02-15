package com.diviso.inventory.model;


import com.fasterxml.jackson.annotation.JsonProperty;




public class StockLineModel   {
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

public ProductModel getProduct() {
	return product;
}

public void setProduct(ProductModel product) {
	this.product = product;
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

@JsonProperty("margin")
  private Double margin = null;

  
  private ProductModel product = null;

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

}

