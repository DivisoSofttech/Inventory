package com.diviso.inventory.service;

import com.diviso.inventory.service.dto.LabelDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Label.
 */
public interface LabelService {

    /**
     * Save a label.
     *
     * @param labelDTO the entity to save
     * @return the persisted entity
     */
    LabelDTO save(LabelDTO labelDTO);

    /**
     * Get all the labels.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LabelDTO> findAll(Pageable pageable);

    /**
     * Get the "id" label.
     *
     * @param id the id of the entity
     * @return the entity
     */
    LabelDTO findOne(Long id);

    /**
     * Delete the "id" label.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
