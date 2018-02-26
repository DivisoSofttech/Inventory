package com.diviso.inventory.service.impl;

import com.diviso.inventory.service.TaxService;
import com.diviso.inventory.domain.Tax;
import com.diviso.inventory.model.TaxModel;
import com.diviso.inventory.repository.TaxRepository;
import com.diviso.inventory.service.dto.TaxDTO;
import com.diviso.inventory.service.mapper.TaxMapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Tax.
 */
@Service
@Transactional
public class TaxServiceImpl implements TaxService {

    private final Logger log = LoggerFactory.getLogger(TaxServiceImpl.class);

    private final TaxRepository taxRepository;

    private final TaxMapper taxMapper;

    public TaxServiceImpl(TaxRepository taxRepository, TaxMapper taxMapper) {
        this.taxRepository = taxRepository;
        this.taxMapper = taxMapper;
    }

    /**
     * Save a tax.
     *
     * @param taxDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TaxDTO save(TaxDTO taxDTO) {
        log.debug("Request to save Tax : {}", taxDTO);
        Tax tax = taxMapper.toEntity(taxDTO);
        tax = taxRepository.save(tax);
        return taxMapper.toDto(tax);
    }

    /**
     * Get all the taxes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TaxDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Taxes");
        return taxRepository.findAll(pageable)
            .map(taxMapper::toDto);
    }

    /**
     * Get one tax by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TaxDTO findOne(Long id) {
        log.debug("Request to get Tax : {}", id);
        Tax tax = taxRepository.findOne(id);
        return taxMapper.toDto(tax);
    }

    /**
     * Delete the tax by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Tax : {}", id);
        taxRepository.delete(id);
    }

	@Override
	public List<TaxModel> findByTaxCategoryId(Long id) {
		Page<Tax> taxes=taxRepository.findByTaxCategory_Id(id,new PageRequest(0, 10));
		List<TaxModel> taxModels=new ArrayList<TaxModel>();
		for(Tax tax:taxes) {
			TaxModel taxModel=new TaxModel(tax.getId(),tax.getName(),tax.getRate(),tax.getType());
			taxModels.add(taxModel);
		}
		return taxModels;
	}
}
