package com.diviso.inventory.service.impl;

import com.diviso.inventory.service.StockService;
import com.diviso.inventory.domain.Product;
import com.diviso.inventory.domain.Status;
import com.diviso.inventory.domain.Stock;
import com.diviso.inventory.domain.StockLine;
import com.diviso.inventory.domain.Uom;
import com.diviso.inventory.model.ProductModel;
import com.diviso.inventory.model.StatusModel;
import com.diviso.inventory.model.StockLineModel;
import com.diviso.inventory.model.StockModel;
import com.diviso.inventory.model.TaxCategoryModel;
import com.diviso.inventory.model.UomModel;
import com.diviso.inventory.repository.StockRepository;
import com.diviso.inventory.service.dto.StockDTO;
import com.diviso.inventory.service.mapper.StockMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Stock.
 */
@Service
@Transactional
public class StockServiceImpl implements StockService {

    private final Logger log = LoggerFactory.getLogger(StockServiceImpl.class);

    private final StockRepository stockRepository;

    private final StockMapper stockMapper;

    public StockServiceImpl(StockRepository stockRepository, StockMapper stockMapper) {
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
    }

    /**
     * Save a stock.
     *
     * @param stockDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StockDTO save(StockDTO stockDTO) {
        log.debug("Request to save Stock : {}", stockDTO);
        Stock stock = stockMapper.toEntity(stockDTO);
        stock = stockRepository.save(stock);
        return stockMapper.toDto(stock);
    }

    /**
     * Get all the stocks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StockDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Stocks");
        return stockRepository.findAll(pageable)
            .map(stockMapper::toDto);
    }

    /**
     * Get one stock by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public StockDTO findOne(Long id) {
        log.debug("Request to get Stock : {}", id);
        Stock stock = stockRepository.findOneWithEagerRelationships(id);
        return stockMapper.toDto(stock);
    }

    /**
     * Delete the stock by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Stock : {}", id);
        stockRepository.delete(id);
    }

    /**
     * Get one stock by reference.
     *
     * @param reference the reference of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
	public StockDTO findByReference(String reference) {
    	log.debug("Request to get Stock by reference : {}", reference);
        Stock stock = stockRepository.findByReference(reference);
        return stockMapper.toDto(stock);
	}

    /**
     * Get one stock by deliveryNoteRef.
     *
     * @param deliveryNoteRef the deliveryNoteRef of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
	public StockDTO findByDeliveryNoteRef(String deliveryNoteRef) {
    	log.debug("Request to get Stock by deliveryNoteRef : {}", deliveryNoteRef);
        Stock stock = stockRepository.findByDeliveryNoteRef(deliveryNoteRef);
        return stockMapper.toDto(stock);
	}

    /**
     * Get all the stocks by dateOfStockUpdated.
     *
     * @param pageable the pagination information and the dateOfStockUpdated of the stock
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
	public Page<StockDTO> findByDateOfStockUpdated(LocalDate dateOfStockUpdated, Pageable pageable) {
    	log.debug("Request to get all Stocks by  dateOfStockUpdated ",dateOfStockUpdated);
        return stockRepository.findByDateOfStockUpdated(dateOfStockUpdated,pageable)
            .map(stockMapper::toDto);
	}

    /**
     * Get all the stocks by dateOfStockUpdatedBetween.
     *
     * @param pageable the pagination information and the dateOfStockUpdated(from&to) of the stock
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
	public Page<StockDTO> findByDateOfStockUpdatedBetween(LocalDate from, LocalDate to, Pageable pageable) {
    	log.debug("Request to get all Stocks by  dateOfStockUpdated between from "+from+" to "+to);
        return stockRepository.findByDateOfStockUpdatedBetween(from,to,pageable)
            .map(stockMapper::toDto);
	}

    /**
     * Get all the stocks by status.
     *
     * @param pageable the pagination information and the status of the stock
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
	public Page<StockDTO> findByStatus_Name(String status, Pageable pageable) {
    	log.debug("Request to get all Stocks by  status ",status);
        return stockRepository.findByStatus_Name(status,pageable)
            .map(stockMapper::toDto);
	}

	@Override
	@Transactional(readOnly=true)
	public StockModel findMarsheldStockById(Long id) {
		Stock stock=stockRepository.findOne(id);
		Set<StockLine> stockLines=stockRepository.findStockLinesByStockId(stock.getId());
		List<StockLineModel> stockLineModelList=new ArrayList<StockLineModel>();
		while(stockLines.iterator().hasNext()) {
			StockLine stockLine=stockLines.iterator().next();
			Product product=stockLine.getProduct();
			Uom uom=stockLine.getUom();
			UomModel uomModel=new UomModel();
			uomModel.setId(uom.getId());
			uomModel.setName(uom.getName());
			ProductModel productModel=new ProductModel();
			productModel.setId(product.getId());
			productModel.setName(product.getName());
			productModel.setTaxCategoryModel(new TaxCategoryModel(product.getTaxCategory().getId(), product.getTaxCategory().getDescription(), product.getTaxCategory().getName()));
			StockLineModel stockLineModel=new StockLineModel(stockLine.getId(),stockLine.getReference(),stockLine.getBuyPrice(),stockLine.getGrossProfit(),stockLine.getSellPriceExclusive(),stockLine.getSellPriceInclusive(),stockLine.getMargin(),stockLine.getInfrastructureId(),stockLine.getLocationId(),productModel,stockLine.getUnits(),uomModel);
			stockLineModelList.add(stockLineModel);
		}
		Status status=stock.getStatus();
		StatusModel statusModel=new StatusModel(status.getId(),status.getDescription(),status.getName(),status.getReference()) ;

		StockModel stockModel=new StockModel(stock.getId(),stock.getDateOfStockUpdated(),stock.getDeliveryNoteRef(),stock.getReference(),stockLineModelList,stock.getStorageCost(),statusModel
				);
		stockModel.setStatus(statusModel);
		System.out.println(stockModel);
		return stockModel;
	}
}
