package com.diviso.inventory.model;



import com.diviso.inventory.domain.enumeration.TaxType;



public class TaxModel   {
  
  private String description = null;


  private Long id = null;


  private String name = null;


  
  private Double rate = null;

 
  private TaxType type;
 

  public TaxModel(Long id2, String name2, Double rate2, TaxType taxType2) {
	// TODO Auto-generated constructor stub
	  id=id2;
	  name=name2;
	  rate=rate2;
	  type=taxType2;
	  
}


public TaxType getType() {
	return type;
}


public void setType(TaxType type) {
	this.type = type;
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


public Double getRate() {
	return rate;
}


public void setRate(Double rate) {
	this.rate = rate;
}




public TaxTypeEnum getTaxType() {
	return taxType;
}


public void setTaxType(TaxTypeEnum taxType) {
	this.taxType = taxType;
}


/**
   * Gets or Sets taxType
   */
  public enum TaxTypeEnum {
    CGST("CGST"),
    
    SGST("SGST"),
    
    IGST("IGST"),
    
    UGST("UGST"),
    
    CESS("CESS");

    private String value;

    TaxTypeEnum(String value) {
      this.setValue(value);
    }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
  }

    
  private TaxTypeEnum taxType = null;

 
}

