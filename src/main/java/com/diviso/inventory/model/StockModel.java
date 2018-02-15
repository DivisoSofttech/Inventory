package com.diviso.inventory.model;


import com.diviso.inventory.service.dto.StockLineDTO;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;



public class StockModel   {
  @JsonProperty("dateOfStockUpdated")
  private LocalDate dateOfStockUpdated = null;

  @JsonProperty("deliveryNoteRef")
  private Long deliveryNoteRef = null;

  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("reference")
  private String reference = null;

 
  private StatusModel status ;

  public LocalDate getDateOfStockUpdated() {
	return dateOfStockUpdated;
}

public void setDateOfStockUpdated(LocalDate dateOfStockUpdated) {
	this.dateOfStockUpdated = dateOfStockUpdated;
}

public Long getDeliveryNoteRef() {
	return deliveryNoteRef;
}

public void setDeliveryNoteRef(Long deliveryNoteRef) {
	this.deliveryNoteRef = deliveryNoteRef;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getReference() {
	return reference;
}

public void setReference(String reference) {
	this.reference = reference;
}

public StatusModel getStatus() {
	return status;
}

public void setStatus(StatusModel status) {
	this.status = status;
}

public List<StockLineDTO> getStockLines() {
	return stockLines;
}

public void setStockLines(List<StockLineDTO> stockLines) {
	this.stockLines = stockLines;
}

public Double getStorageCost() {
	return storageCost;
}

public void setStorageCost(Double storageCost) {
	this.storageCost = storageCost;
}

@JsonProperty("stockLines")
  private List<StockLineDTO> stockLines = new ArrayList<StockLineDTO>();

  @JsonProperty("storageCost")
  private Double storageCost = null;

  
}

