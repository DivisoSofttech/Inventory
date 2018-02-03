package com.diviso.inventory.service;

import com.diviso.inventory.service.dto.UomDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Uom.
 */
public interface UomService {

    /**
     * Save a uom.
     *
     * @param uomDTO the entity to save
     * @return the persisted entity
     */
    UomDTO save(UomDTO uomDTO);

    /**
     * Get all the uoms.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UomDTO> findAll(Pageable pageable);

    /**
     * Get the "id" uom.
     *
     * @param id the id of the entity
     * @return the entity
     */
    UomDTO findOne(Long id);

    /**
     * Delete the "id" uom.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
