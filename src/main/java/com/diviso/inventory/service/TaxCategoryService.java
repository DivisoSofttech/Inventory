package com.diviso.inventory.service;

import com.diviso.inventory.service.dto.TaxCategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing TaxCategory.
 */
public interface TaxCategoryService {

    /**
     * Save a taxCategory.
     *
     * @param taxCategoryDTO the entity to save
     * @return the persisted entity
     */
    TaxCategoryDTO save(TaxCategoryDTO taxCategoryDTO);

    /**
     * Get all the taxCategories.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TaxCategoryDTO> findAll(Pageable pageable);

    /**
     * Get the "id" taxCategory.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TaxCategoryDTO findOne(Long id);

    /**
     * Delete the "id" taxCategory.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
