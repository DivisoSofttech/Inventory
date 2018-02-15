package com.diviso.inventory.service;

import com.diviso.inventory.domain.Product;
import com.diviso.inventory.model.NoteModel;
import com.diviso.inventory.model.ProductModel;
import com.diviso.inventory.service.dto.ProductDTO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Product.
 */
public interface ProductService {

    /**
     * Save a product.
     *
     * @param productDTO the entity to save
     * @return the persisted entity
     */
    ProductDTO save(ProductDTO productDTO);

    /**
     * Get all the products.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ProductDTO> findAll(Pageable pageable);

    /**
     * Get the "id" product.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ProductDTO findOne(Long id);

    /**
     * Delete the "id" product.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get all the products by name.
     *
     * @param pageable the pagination information and the name of the product
     * @return the list of entities
     */
	Page<ProductDTO> findByNameAndVisibleTrue(String name, Pageable pageable);

	 /**
     * Get all the products by visibility false.
     *
     * @param pageable the pagination information 
     * @return the list of entities
     */
	Page<ProductDTO> findByVisibleFalse(Pageable pageable);

	/**
     * Get all the products by visibility True.
     *
     * @param pageable the pagination information 
     * @return the list of entities
     */
	Page<ProductDTO> findByVisibleTrue(Pageable pageable);

	/**
     * Get all the products by name containing.
     *
     * @param pageable the pagination information and the name of the product
     * @return the list of entities
     */
	Page<ProductDTO> findByNameContainingAndVisibleTrue(String name, Pageable pageable);

	 /**
     * Get one product by reference.
     *
     * @param reference the reference of the entity
     * @return the entity
     */
	ProductDTO findByReferenceAndVisibleTrue(String reference);
	
	 /**
     * Get one product by sku.
     *
     * @param sku the sku of the entity
     * @return the entity
     */
	ProductDTO findBySkuAndVisibleTrue(String sku);

	/**
     * Get one product by searchkey.
     *
     * @param searchkey the searchkey of the entity
     * @return the entity
     */
	ProductDTO findBySearchkeyAndVisibleTrue(String searchkey);

	/**
     * Get one product by mpn.
     *
     * @param mpn the mpn of the entity
     * @return the entity
     */
	ProductDTO findByMpnAndVisibleTrue(String mpn);

	/**
     * Get one product by barcode.
     *
     * @param barcode the barcode of the entity
     * @return the entity
     */
	ProductDTO findByBarcode_CodeAndVisibleTrue(String barcode);

	/**
     * Get all the products by category name.
     *
     * @param pageable the pagination information and the name of the category
     * @return the list of entities
     */
	Page<ProductDTO> findByCategories_NameAndVisibleTrue(String name, Pageable pageable);

	/**
     * Get all the products by dateOfMfd.
     *
     * @param pageable the pagination information and the dateOfMfd of the product
     * @return the list of entities
     */
	Page<ProductDTO> findByDateOfMfdAndVisibleTrue(LocalDate dateOfMfd, Pageable pageable);

	/**
     * Get all the products by dateOfExpiry.
     *
     * @param pageable the pagination information and the dateOfExpiry of the product
     * @return the list of entities
     */
	Page<ProductDTO> findByDateOfExpiryAndVisibleTrue(LocalDate dateOfExpiry, Pageable pageable);

	/**
     * Get all the products by dateOfExpiryBetween.
     *
     * @param pageable the pagination information and the dateOfExpiry(from&to) of the product
     * @return the list of entities
     */
	Page<ProductDTO> findByDateOfExpiryBetweenAndVisibleTrue(LocalDate from, LocalDate to, Pageable pageable);

	/**
     * Get all the products by status.
     *
     * @param pageable the pagination information and the status of the product
     * @return the list of entities
     */
	Page<ProductDTO> findByStatus_NameAndVisibleTrue(String status, Pageable pageable);

	ProductModel findMarsheldProduct(Long id);

	List<NoteModel> findNoteByProductId(Long id,Pageable pageable);
}
