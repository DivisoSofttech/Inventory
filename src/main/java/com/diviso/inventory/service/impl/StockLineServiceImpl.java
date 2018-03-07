package com.diviso.inventory.service.impl;

import com.diviso.inventory.service.StockLineService;
import com.diviso.inventory.domain.Product;
import com.diviso.inventory.domain.StockLine;
import com.diviso.inventory.domain.Uom;
import com.diviso.inventory.model.ProductModel;
import com.diviso.inventory.model.StockLineModel;
import com.diviso.inventory.model.TaxCategoryModel;
import com.diviso.inventory.model.UomModel;
import com.diviso.inventory.repository.StockLineRepository;
import com.diviso.inventory.service.dto.StockLineDTO;
import com.diviso.inventory.service.mapper.StockLineMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	 * @param stockLineDTO
	 *            the entity to save
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
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<StockLineDTO> findAll(Pageable pageable) {
		log.debug("Request to get all StockLines");
		return stockLineRepository.findAll(pageable).map(stockLineMapper::toDto);
	}

	/**
	 * Get one stockLine by id.
	 *
	 * @param id
	 *            the id of the entity
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
	 * @param id
	 *            the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete StockLine : {}", id);
		stockLineRepository.delete(id);
	}

	/**
	 * Get all the stock-lines by product name.
	 *
	 * @param pageable
	 *            the pagination information and the name of the product
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<StockLineDTO> findByProduct_NameAndProduct_VisibleTrue(String name, Pageable pageable) {
		log.debug("Request to get all stock-lines by product  name ", name);
		return stockLineRepository.findByProduct_NameIgnoreCaseAndProduct_VisibleTrue(name, pageable)
				.map(stockLineMapper::toDto);
	}

	/**
	 * Get all the stock-lines by product name containing.
	 *
	 * @param pageable
	 *            the pagination information and the name of the product
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<StockLineDTO> findByProduct_NameContainingAndProduct_VisibleTrue(String name, Pageable pageable) {
		log.debug("Request to get all stock-lines by product  name containing ", name);
		return stockLineRepository.findByProduct_NameIgnoreCaseContainingAndProduct_VisibleTrue(pageable)
				.map(stockLineMapper::toDto);
	}

	/**
	 * Get all the stock-lines by product visibility false.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<StockLineDTO> findByProduct_VisibleFalse(Pageable pageable) {
		log.debug("Request to get all stock-lines by product  visibility false ");
		return stockLineRepository.findByProduct_VisibleFalse(pageable).map(stockLineMapper::toDto);
	}

	/**
	 * Get all the stock-lines by product visibility True.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<StockLineDTO> findByProduct_VisibleTrue(Pageable pageable) {
		log.debug("Request to get all stock-lines by product  visibility True ");
		return stockLineRepository.findByProduct_VisibleTrue(pageable).map(stockLineMapper::toDto);
	}

	/**
	 * Get one stock-line by product reference.
	 *
	 * @param reference
	 *            the reference of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public StockLineDTO findByProduct_ReferenceAndProduct_VisibleTrue(String reference) {
		log.debug("Request to get stock-line by product reference : {}", reference);
		StockLine stockLine = stockLineRepository.findByProduct_ReferenceIgnoreCaseAndProduct_VisibleTrue(reference);
		return stockLineMapper.toDto(stockLine);
	}

	/**
	 * Get one stock-line by product sku.
	 *
	 * @param sku
	 *            the sku of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public StockLineDTO findByProduct_SkuAndProduct_VisibleTrue(String sku) {
		log.debug("Request to get stock-line by product sku : {}", sku);
		StockLine stockLine = stockLineRepository.findByProduct_SkuIgnoreCaseAndProduct_VisibleTrue(sku);
		return stockLineMapper.toDto(stockLine);
	}

	/**
	 * Get one stock-line by product searchkey.
	 *
	 * @param searchkey
	 *            the searchkey of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public StockLineDTO findByProduct_SearchkeyAndProduct_VisibleTrue(String searchkey) {
		log.debug("Request to get stock-line by product searchkey : {}", searchkey);
		StockLine stockLine = stockLineRepository.findByProduct_SearchkeyIgnoreCaseAndProduct_VisibleTrue(searchkey);
		return stockLineMapper.toDto(stockLine);
	}

	/**
	 * Get one stock-line by product mpn.
	 *
	 * @param mpn
	 *            the mpn of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public StockLineDTO findByProduct_MpnAndProduct_VisibleTrue(String mpn) {
		log.debug("Request to get stock-line by product mpn : {}", mpn);
		StockLine stockLine = stockLineRepository.findByProduct_MpnIgnoreCaseAndProduct_VisibleTrue(mpn);
		return stockLineMapper.toDto(stockLine);
	}

	/**
	 * Get one stock-line by product barcode.
	 *
	 * @param barcode
	 *            the barcode of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public StockLineDTO findByProduct_Barcode_CodeAndProduct_VisibleTrue(String barcode) {
		log.debug("Request to get stock-line by product barcode : {}", barcode);
		StockLine stockLine = stockLineRepository.findByProduct_Barcode_CodeAndProduct_VisibleTrue(barcode);
		return stockLineMapper.toDto(stockLine);
	}

	/**
	 * Get all the stock-lines by product dateOfMfd.
	 *
	 * @param pageable
	 *            the pagination information and the dateOfMfd of the product
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<StockLineDTO> findByProduct_DateOfMfdAndProduct_VisibleTrue(LocalDate dateOfMfd, Pageable pageable) {
		log.debug("Request to get all stock-lines by product  dateOfMfd ", dateOfMfd);
		return stockLineRepository.findByProduct_DateOfMfdAndProduct_VisibleTrue(dateOfMfd, pageable)
				.map(stockLineMapper::toDto);
	}

	/**
	 * Get all the stock-lines by product dateOfExpiry.
	 *
	 * @param pageable
	 *            the pagination information and the dateOfExpiry of the product
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<StockLineDTO> findByProduct_DateOfExpiryAndProduct_VisibleTrue(LocalDate dateOfExpiry,
			Pageable pageable) {
		log.debug("Request to get all stock-lines by product  dateOfExpiry ", dateOfExpiry);
		return stockLineRepository.findByProduct_DateOfExpiryAndProduct_VisibleTrue(dateOfExpiry, pageable)
				.map(stockLineMapper::toDto);
	}

	/**
	 * Get all the stock-lines by product dateOfExpiryBetween.
	 *
	 * @param pageable
	 *            the pagination information and the dateOfExpiry(from&to) of the
	 *            product
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<StockLineDTO> findByProduct_DateOfExpiryBetweenAndProduct_VisibleTrue(LocalDate from, LocalDate to,
			Pageable pageable) {
		log.debug("Request to get all stock-lines by product  dateOfExpiry from " + from + " to " + to);
		return stockLineRepository.findByProduct_DateOfExpiryBetweenAndProduct_VisibleTrue(from, to, pageable)
				.map(stockLineMapper::toDto);
	}

	/**
	 * Get all the stock-lines by product status.
	 *
	 * @param pageable
	 *            the pagination information and the status of the product
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<StockLineDTO> findByProduct_Status_NameAndProduct_VisibleTrue(String status, Pageable pageable) {
		log.debug("Request to get all stock-lines by product  status ", status);
		return stockLineRepository.findByProduct_Status_NameIgnoreCaseAndProduct_VisibleTrue(status, pageable)
				.map(stockLineMapper::toDto);
	}

	/**
	 * Get all the stock-lines by product category name.
	 *
	 * @param pageable
	 *            the pagination information and the name of the category
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<StockLineDTO> findByProduct_Categories_NameAndProduct_VisibleTrue(String name, Pageable pageable) {
		log.debug("Request to get all stock-lines by product  category name ", name);
		return stockLineRepository.findByProduct_Category_NameIgnoreCaseAndProduct_VisibleTrue(name, pageable)
				.map(stockLineMapper::toDto);
	}

	/**
	 * Get the "reference" stockLine.
	 *
	 * @param reference
	 *            the reference of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public StockLineDTO findByReference(String reference) {
		log.debug("Request to get StockLine : {}", reference);
		StockLine stockLine = stockLineRepository.findByReference(reference);
		return stockLineMapper.toDto(stockLine);
	}

	/**
	 * Get all the stockLines by infrastructureId.
	 *
	 * @param pageable
	 *            the pagination information and the infrastructureId of the
	 *            stockLine
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<StockLineDTO> findByInfrastructureId(Long infrastructureId, Pageable pageable) {
		log.debug("Request to get all StockLines by infrastructureId ", infrastructureId);
		return stockLineRepository.findByInfrastructureId(infrastructureId, pageable).map(stockLineMapper::toDto);
	}

	/**
	 * Get all the stockLines by locationId.
	 *
	 * @param pageable
	 *            the pagination information and the locationId of the stockLine
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<StockLineDTO> findByLocationId(Long locationId, Pageable pageable) {
		log.debug("Request to get all StockLines by locationId ", locationId);
		return stockLineRepository.findByLocationId(locationId, pageable).map(stockLineMapper::toDto);
	}

	/**
	 * Get all the stockLines by supplierRef.
	 *
	 * @param pageable
	 *            the pagination information and the supplierRef of the stockLine
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<StockLineDTO> findBySupplierRef(Long supplierRef, Pageable pageable) {
		log.debug("Request to get all StockLines by supplierRef ", supplierRef);
		return stockLineRepository.findBySupplierRef(supplierRef, pageable).map(stockLineMapper::toDto);
	}

	@Override
	public List<StockLineDTO> updateStockLevel(ArrayList<StockLineDTO> stockLines) {
		for (int i = 0; i < stockLines.size(); i++) {
			StockLine line = stockLineRepository.findOne(stockLines.get(i).getId());
			Double remaining = line.getUnits() - stockLines.get(i).getUnits();
			line.setUnits(remaining);
			stockLineRepository.save(line);
		}
		return stockLines;
	}

	@Override
	public StockLineModel findMarsheldStockLine(Long id) {
		StockLine stockLine=stockLineRepository.findOne(id);
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
		return stockLineModel;
	}

	@Override
	public List<StockLineModel> findAllStockLinesMarsheld(ArrayList<StockLineDTO> dtoList) {
		List<StockLineModel> list=new ArrayList<StockLineModel>();
		for(StockLineDTO stockLineDTO:dtoList) {
			StockLineModel stockLineModel=findMarsheldStockLine(stockLineDTO.getId());
			list.add(stockLineModel);
		}
		return list;
	}

	@Override
	public Page<StockLineDTO> findByProduct_Category_IdAndProduct_VisibleTrue(Long id, Pageable pageable) {
		log.debug("Request to get all stock-lines by product  category id ", id);
		return stockLineRepository.findByProduct_Category_IdAndProduct_VisibleTrue(id, pageable)
				.map(stockLineMapper::toDto);
	}

	
}
