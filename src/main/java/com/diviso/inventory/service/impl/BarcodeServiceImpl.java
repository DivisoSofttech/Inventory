package com.diviso.inventory.service.impl;

import com.diviso.inventory.service.BarcodeService;
import com.diviso.inventory.domain.Barcode;
import com.diviso.inventory.repository.BarcodeRepository;
import com.diviso.inventory.service.dto.BarcodeDTO;
import com.diviso.inventory.service.mapper.BarcodeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Barcode.
 */
@Service
@Transactional
public class BarcodeServiceImpl implements BarcodeService {

    private final Logger log = LoggerFactory.getLogger(BarcodeServiceImpl.class);

    private final BarcodeRepository barcodeRepository;

    private final BarcodeMapper barcodeMapper;

    public BarcodeServiceImpl(BarcodeRepository barcodeRepository, BarcodeMapper barcodeMapper) {
        this.barcodeRepository = barcodeRepository;
        this.barcodeMapper = barcodeMapper;
    }

    /**
     * Save a barcode.
     *
     * @param barcodeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BarcodeDTO save(BarcodeDTO barcodeDTO) {
        log.debug("Request to save Barcode : {}", barcodeDTO);
        Barcode barcode = barcodeMapper.toEntity(barcodeDTO);
        barcode = barcodeRepository.save(barcode);
        return barcodeMapper.toDto(barcode);
    }

    /**
     * Get all the barcodes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BarcodeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Barcodes");
        return barcodeRepository.findAll(pageable)
            .map(barcodeMapper::toDto);
    }

    /**
     * Get one barcode by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BarcodeDTO findOne(Long id) {
        log.debug("Request to get Barcode : {}", id);
        Barcode barcode = barcodeRepository.findOne(id);
        return barcodeMapper.toDto(barcode);
    }

    /**
     * Delete the barcode by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Barcode : {}", id);
        barcodeRepository.delete(id);
    }
}
