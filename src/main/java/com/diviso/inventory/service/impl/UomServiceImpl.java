package com.diviso.inventory.service.impl;

import com.diviso.inventory.service.UomService;
import com.diviso.inventory.domain.Uom;
import com.diviso.inventory.repository.UomRepository;
import com.diviso.inventory.service.dto.UomDTO;
import com.diviso.inventory.service.mapper.UomMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Uom.
 */
@Service
@Transactional
public class UomServiceImpl implements UomService {

    private final Logger log = LoggerFactory.getLogger(UomServiceImpl.class);

    private final UomRepository uomRepository;

    private final UomMapper uomMapper;

    public UomServiceImpl(UomRepository uomRepository, UomMapper uomMapper) {
        this.uomRepository = uomRepository;
        this.uomMapper = uomMapper;
    }

    /**
     * Save a uom.
     *
     * @param uomDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UomDTO save(UomDTO uomDTO) {
        log.debug("Request to save Uom : {}", uomDTO);
        Uom uom = uomMapper.toEntity(uomDTO);
        uom = uomRepository.save(uom);
        return uomMapper.toDto(uom);
    }

    /**
     * Get all the uoms.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UomDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Uoms");
        return uomRepository.findAll(pageable)
            .map(uomMapper::toDto);
    }

    /**
     * Get one uom by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public UomDTO findOne(Long id) {
        log.debug("Request to get Uom : {}", id);
        Uom uom = uomRepository.findOne(id);
        return uomMapper.toDto(uom);
    }

    /**
     * Delete the uom by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Uom : {}", id);
        uomRepository.delete(id);
    }
}
