package com.diviso.inventory.service;

import com.diviso.inventory.service.dto.BarcodeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Barcode.
 */
public interface BarcodeService {

    /**
     * Save a barcode.
     *
     * @param barcodeDTO the entity to save
     * @return the persisted entity
     */
    BarcodeDTO save(BarcodeDTO barcodeDTO);

    /**
     * Get all the barcodes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BarcodeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" barcode.
     *
     * @param id the id of the entity
     * @return the entity
     */
    BarcodeDTO findOne(Long id);

    /**
     * Delete the "id" barcode.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
