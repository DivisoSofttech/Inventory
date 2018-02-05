package com.diviso.inventory.service;

import com.diviso.inventory.service.dto.StockLineDTO;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing StockLine.
 */
public interface StockLineService {

    /**
     * Save a stockLine.
     *
     * @param stockLineDTO the entity to save
     * @return the persisted entity
     */
    StockLineDTO save(StockLineDTO stockLineDTO);

    /**
     * Get all the stockLines.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<StockLineDTO> findAll(Pageable pageable);

    /**
     * Get the "id" stockLine.
     *
     * @param id the id of the entity
     * @return the entity
     */
    StockLineDTO findOne(Long id);

    /**
     * Delete the "id" stockLine.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get all the stock-lines by product name.
     *
     * @param pageable the pagination information and the name of the product
     * @return the list of entities
     */
	Page<StockLineDTO> findByProduct_NameAndProduct_VisibleTrue(String name, Pageable pageable);

	/**
     * Get all the stock-lines by product name containing.
     *
     * @param pageable the pagination information and the name of the product
     * @return the list of entities
     */
	Page<StockLineDTO> findByProduct_NameContainingAndProduct_VisibleTrue(String name, Pageable pageable);

	/**
     * Get all the stock-lines by product visibility false.
     *
     * @param pageable the pagination information 
     * @return the list of entities
     */
	Page<StockLineDTO> findByProduct_VisibleFalse(Pageable pageable);

	/**
     * Get all the stock-lines by product visibility True.
     *
     * @param pageable the pagination information 
     * @return the list of entities
     */
	Page<StockLineDTO> findByProduct_VisibleTrue(Pageable pageable);

	/**
     * Get one stock-line by product reference.
     *
     * @param reference the reference of the entity
     * @return the entity
     */
	StockLineDTO findByProduct_ReferenceAndProduct_VisibleTrue(String reference);

	/**
     * Get one stock-line by product sku.
     *
     * @param sku the sku of the entity
     * @return the entity
     */
	StockLineDTO findByProduct_SkuAndProduct_VisibleTrue(String sku);

	/**
     * Get one stock-line by product searchkey.
     *
     * @param searchkey the searchkey of the entity
     * @return the entity
     */
	StockLineDTO findByProduct_SearchkeyAndProduct_VisibleTrue(String searchkey);

	/**
     * Get one stock-line by product mpn.
     *
     * @param mpn the mpn of the entity
     * @return the entity
     */
	StockLineDTO findByProduct_MpnAndProduct_VisibleTrue(String mpn);

	/**
     * Get one stock-line by product barcode.
     *
     * @param barcode the barcode of the entity
     * @return the entity
     */
	StockLineDTO findByProduct_Barcode_CodeAndProduct_VisibleTrue(String barcode);

	/**
     * Get all the stock-lines by product dateOfMfd.
     *
     * @param pageable the pagination information and the dateOfMfd of the product
     * @return the list of entities
     */
	Page<StockLineDTO> findByProduct_DateOfMfdAndProduct_VisibleTrue(LocalDate dateOfMfd, Pageable pageable);

	/**
     * Get all the stock-lines by product dateOfExpiry.
     *
     * @param pageable the pagination information and the dateOfExpiry of the product
     * @return the list of entities
     */
	Page<StockLineDTO> findByProduct_DateOfExpiryAndProduct_VisibleTrue(LocalDate dateOfExpiry, Pageable pageable);

	/**
     * Get all the stock-lines by product dateOfExpiryBetween.
     *
     * @param pageable the pagination information and the dateOfExpiry(from&to) of the product
     * @return the list of entities
     */
	Page<StockLineDTO> findByProduct_DateOfExpiryBetweenAndProduct_VisibleTrue(LocalDate from, LocalDate to,
			Pageable pageable);

	/**
     * Get all the stock-lines by product status.
     *
     * @param pageable the pagination information and the status of the product
     * @return the list of entities
     */
	Page<StockLineDTO> findByProduct_Status_NameAndProduct_VisibleTrue(String status, Pageable pageable);


	/**
     * Get all the stock-lines by product category name.
     *
     * @param pageable the pagination information and the name of the product category
     * @return the list of entities
     */
	Page<StockLineDTO> findByProduct_Categories_NameAndProduct_VisibleTrue(String name, Pageable pageable);

	/**
     * Get the "reference" stockLine.
     *
     * @param reference the reference of the entity
     * @return the entity
     */
	StockLineDTO findByReference(String reference);

	/**
     * Get all the stockLines by infrastructureId.
     *
     * @param pageable the pagination information and the infrastructureId of the stockLine
     * @return the list of entities
     */
	Page<StockLineDTO> findByInfrastructureId(Long infrastructureId, Pageable pageable);

	/**
     * Get all the stockLines by locationId.
     *
     * @param pageable the pagination information and the locationId of the stockLine
     * @return the list of entities
     */
	Page<StockLineDTO> findByLocationId(Long locationId, Pageable pageable);

	/**
     * Get all the stockLines by supplierRef.
     *
     * @param pageable the pagination information and the supplierRef of the stockLine
     * @return the list of entities
     */
	Page<StockLineDTO> findBySupplierRef(Long supplierRef, Pageable pageable);
}
