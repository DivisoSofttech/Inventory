package com.diviso.inventory.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "reference", nullable = false)
    private String reference;

    @NotNull
    @Column(name = "searchkey", nullable = false)
    private String searchkey;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @Column(name = "description")
    private String description;

    @Column(name = "sku")
    private String sku;

    @Column(name = "mpn")
    private String mpn;

    @Column(name = "visible")
    private Boolean visible;

    @Column(name = "date_of_mfd")
    private LocalDate dateOfMfd;

    @Column(name = "date_of_expiry")
    private LocalDate dateOfExpiry;

    @Column(name = "maximum_stock_level")
    private Double maximumStockLevel;

    @Column(name = "re_order_level")
    private Double reOrderLevel;

    @OneToOne
    @JoinColumn(unique = true)
    private Barcode barcode;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Note> notes = new HashSet<>();

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<StockLine> stockLines = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "product_labels",
               joinColumns = @JoinColumn(name="products_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="labels_id", referencedColumnName="id"))
    private Set<Label> labels = new HashSet<>();

    @ManyToOne
    private Category category;

    @ManyToOne
    private Status status;

    
    @ManyToOne
    private TaxCategory taxCategory;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public Product reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getSearchkey() {
        return searchkey;
    }

    public Product searchkey(String searchkey) {
        this.searchkey = searchkey;
        return this;
    }

    public void setSearchkey(String searchkey) {
        this.searchkey = searchkey;
    }

    public String getName() {
        return name;
    }

    public Product name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public Product image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public Product imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getDescription() {
        return description;
    }

    public Product description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public Product sku(String sku) {
        this.sku = sku;
        return this;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getMpn() {
        return mpn;
    }

    public Product mpn(String mpn) {
        this.mpn = mpn;
        return this;
    }

    public void setMpn(String mpn) {
        this.mpn = mpn;
    }

    public Boolean isVisible() {
        return visible;
    }

    public Product visible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public LocalDate getDateOfMfd() {
        return dateOfMfd;
    }

    public Product dateOfMfd(LocalDate dateOfMfd) {
        this.dateOfMfd = dateOfMfd;
        return this;
    }

    public void setDateOfMfd(LocalDate dateOfMfd) {
        this.dateOfMfd = dateOfMfd;
    }

    public LocalDate getDateOfExpiry() {
        return dateOfExpiry;
    }

    public Product dateOfExpiry(LocalDate dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
        return this;
    }

    public void setDateOfExpiry(LocalDate dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public Double getMaximumStockLevel() {
        return maximumStockLevel;
    }

    public Product maximumStockLevel(Double maximumStockLevel) {
        this.maximumStockLevel = maximumStockLevel;
        return this;
    }

    public void setMaximumStockLevel(Double maximumStockLevel) {
        this.maximumStockLevel = maximumStockLevel;
    }

    public Double getReOrderLevel() {
        return reOrderLevel;
    }

    public Product reOrderLevel(Double reOrderLevel) {
        this.reOrderLevel = reOrderLevel;
        return this;
    }

    public void setReOrderLevel(Double reOrderLevel) {
        this.reOrderLevel = reOrderLevel;
    }

    public Barcode getBarcode() {
        return barcode;
    }

    public Product barcode(Barcode barcode) {
        this.barcode = barcode;
        return this;
    }

    public void setBarcode(Barcode barcode) {
        this.barcode = barcode;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public Product notes(Set<Note> notes) {
        this.notes = notes;
        return this;
    }

    public Product addNotes(Note note) {
        this.notes.add(note);
        note.setProduct(this);
        return this;
    }

    public Product removeNotes(Note note) {
        this.notes.remove(note);
        note.setProduct(null);
        return this;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    public Set<StockLine> getStockLines() {
        return stockLines;
    }

    public Product stockLines(Set<StockLine> stockLines) {
        this.stockLines = stockLines;
        return this;
    }

    public Product addStockLines(StockLine stockLine) {
        this.stockLines.add(stockLine);
        stockLine.setProduct(this);
        return this;
    }

    public Product removeStockLines(StockLine stockLine) {
        this.stockLines.remove(stockLine);
        stockLine.setProduct(null);
        return this;
    }

    public void setStockLines(Set<StockLine> stockLines) {
        this.stockLines = stockLines;
    }

    public Set<Label> getLabels() {
        return labels;
    }

    public Product labels(Set<Label> labels) {
        this.labels = labels;
        return this;
    }

    public Product addLabels(Label label) {
        this.labels.add(label);
        return this;
    }

    public Product removeLabels(Label label) {
        this.labels.remove(label);
        return this;
    }

    public void setLabels(Set<Label> labels) {
        this.labels = labels;
    }

    public Category getCategory() {
        return category;
    }

    public Product category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public Product status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TaxCategory getTaxCategory() {
        return taxCategory;
    }

    public Product taxCategory(TaxCategory taxCategory) {
        this.taxCategory = taxCategory;
        return this;
    }

    public void setTaxCategory(TaxCategory taxCategory) {
        this.taxCategory = taxCategory;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        if (product.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", searchkey='" + getSearchkey() + "'" +
            ", name='" + getName() + "'" +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            ", description='" + getDescription() + "'" +
            ", sku='" + getSku() + "'" +
            ", mpn='" + getMpn() + "'" +
            ", visible='" + isVisible() + "'" +
            ", dateOfMfd='" + getDateOfMfd() + "'" +
            ", dateOfExpiry='" + getDateOfExpiry() + "'" +
            ", maximumStockLevel=" + getMaximumStockLevel() +
            ", reOrderLevel=" + getReOrderLevel() +
            "}";
    }
}
