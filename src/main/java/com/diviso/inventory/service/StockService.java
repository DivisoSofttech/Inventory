package com.diviso.inventory.service;


import com.diviso.inventory.service.dto.StockDTO;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Stock.
 */
public interface StockService {

    /**
     * Save a stock.
     *
     * @param stockDTO the entity to save
     * @return the persisted entity
     */
    StockDTO save(StockDTO stockDTO);

    /**
     * Get all the stocks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<StockDTO> findAll(Pageable pageable);

    /**
     * Get the "id" stock.
     *
     * @param id the id of the entity
     * @return the entity
     */
    StockDTO findOne(Long id);

    /**
     * Delete the "id" stock.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Get the "reference" stock.
     *
     * @param reference the reference of the entity
     * @return the entity
     */
	StockDTO findByReference(String reference);

	 /**
     * Get the "deliveryNoteRef" stock.
     *
     * @param deliveryNoteRef the deliveryNoteRef of the entity
     * @return the entity
     */
	StockDTO findByDeliveryNoteRef(String deliveryNoteRef);

	/**
     * Get all the stocks by dateOfStockUpdated.
     *
     * @param pageable the pagination information and the dateOfStockUpdated of the stock
     * @return the list of entities
     */
	Page<StockDTO> findByDateOfStockUpdated(LocalDate dateOfStockUpdated, Pageable pageable);

	/**
     * Get all the stocks by dateOfStockUpdatedBetween.
     *
     * @param pageable the pagination information and the dateOfStockUpdated(from&to) of the stock
     * @return the list of entities
     */
	Page<StockDTO> findByDateOfStockUpdatedBetween(LocalDate from, LocalDate to, Pageable pageable);

	/**
     * Get all the stocks by status.
     *
     * @param pageable the pagination information and the status of the stock
     * @return the list of entities
     */
	Page<StockDTO> findByStatus_Name(LocalDate status, Pageable pageable);
   
}
