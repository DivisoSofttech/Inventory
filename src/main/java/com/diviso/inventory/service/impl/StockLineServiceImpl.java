package com.diviso.inventory.service.impl;

import com.diviso.inventory.service.StockLineService;
import com.diviso.inventory.domain.StockLine;
import com.diviso.inventory.repository.StockLineRepository;
import com.diviso.inventory.service.dto.StockLineDTO;
import com.diviso.inventory.service.mapper.StockLineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing StockLine.
 */
@Service
@Transactional
public class StockLineServiceImpl implements StockLineService {

    private final Logger log = LoggerFactory.getLogger(StockLineServiceImpl.class);

    private final StockLineRepository stockLineRepository;

    private final StockLineMapper stockLineMapper;

    public StockLineServiceImpl(StockLineRepository stockLineRepository, StockLineMapper stockLineMapper) {
        this.stockLineRepository = stockLineRepository;
        this.stockLineMapper = stockLineMapper;
    }

    /**
     * Save a stockLine.
     *
     * @param stockLineDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StockLineDTO save(StockLineDTO stockLineDTO) {
        log.debug("Request to save StockLine : {}", stockLineDTO);
        StockLine stockLine = stockLineMapper.toEntity(stockLineDTO);
        stockLine = stockLineRepository.save(stockLine);
        return stockLineMapper.toDto(stockLine);
    }

    /**
     * Get all the stockLines.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StockLineDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StockLines");
        return stockLineRepository.findAll(pageable)
            .map(stockLineMapper::toDto);
    }

    /**
     * Get one stockLine by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public StockLineDTO findOne(Long id) {
        log.debug("Request to get StockLine : {}", id);
        StockLine stockLine = stockLineRepository.findOne(id);
        return stockLineMapper.toDto(stockLine);
    }

    /**
     * Delete the stockLine by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StockLine : {}", id);
        stockLineRepository.delete(id);
    }
}
