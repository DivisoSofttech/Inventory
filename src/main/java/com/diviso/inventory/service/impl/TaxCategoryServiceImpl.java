package com.diviso.inventory.service.impl;

import com.diviso.inventory.service.TaxCategoryService;
import com.diviso.inventory.domain.TaxCategory;
import com.diviso.inventory.repository.TaxCategoryRepository;
import com.diviso.inventory.service.dto.TaxCategoryDTO;
import com.diviso.inventory.service.mapper.TaxCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing TaxCategory.
 */
@Service
@Transactional
public class TaxCategoryServiceImpl implements TaxCategoryService {

    private final Logger log = LoggerFactory.getLogger(TaxCategoryServiceImpl.class);

    private final TaxCategoryRepository taxCategoryRepository;

    private final TaxCategoryMapper taxCategoryMapper;

    public TaxCategoryServiceImpl(TaxCategoryRepository taxCategoryRepository, TaxCategoryMapper taxCategoryMapper) {
        this.taxCategoryRepository = taxCategoryRepository;
        this.taxCategoryMapper = taxCategoryMapper;
    }

    /**
     * Save a taxCategory.
     *
     * @param taxCategoryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TaxCategoryDTO save(TaxCategoryDTO taxCategoryDTO) {
        log.debug("Request to save TaxCategory : {}", taxCategoryDTO);
        TaxCategory taxCategory = taxCategoryMapper.toEntity(taxCategoryDTO);
        taxCategory = taxCategoryRepository.save(taxCategory);
        return taxCategoryMapper.toDto(taxCategory);
    }

    /**
     * Get all the taxCategories.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TaxCategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TaxCategories");
        return taxCategoryRepository.findAll(pageable)
            .map(taxCategoryMapper::toDto);
    }

    /**
     * Get one taxCategory by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TaxCategoryDTO findOne(Long id) {
        log.debug("Request to get TaxCategory : {}", id);
        TaxCategory taxCategory = taxCategoryRepository.findOne(id);
        return taxCategoryMapper.toDto(taxCategory);
    }

    /**
     * Delete the taxCategory by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TaxCategory : {}", id);
        taxCategoryRepository.delete(id);
    }
}
