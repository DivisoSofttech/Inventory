package com.diviso.inventory.service;

import com.diviso.inventory.service.dto.StockLineDTO;
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
}
