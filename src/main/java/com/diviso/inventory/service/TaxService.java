package com.diviso.inventory.service;

import com.diviso.inventory.model.TaxModel;
import com.diviso.inventory.service.dto.TaxDTO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Tax.
 */
public interface TaxService {

    /**
     * Save a tax.
     *
     * @param taxDTO the entity to save
     * @return the persisted entity
     */
    TaxDTO save(TaxDTO taxDTO);

    /**
     * Get all the taxes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TaxDTO> findAll(Pageable pageable);

    /**
     * Get the "id" tax.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TaxDTO findOne(Long id);

    /**
     * Delete the "id" tax.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

	List<TaxModel> findByTaxCategoryId(Long id);
}
