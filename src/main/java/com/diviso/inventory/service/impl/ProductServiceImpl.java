package com.diviso.inventory.service.impl;

import com.diviso.inventory.service.ProductService;
import com.diviso.inventory.domain.Product;
import com.diviso.inventory.repository.ProductRepository;
import com.diviso.inventory.service.dto.ProductDTO;
import com.diviso.inventory.service.mapper.ProductMapper;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Product.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    /**
     * Save a product.
     *
     * @param productDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProductDTO save(ProductDTO productDTO) {
        log.debug("Request to save Product : {}", productDTO);
        Product product = productMapper.toEntity(productDTO);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    /**
     * Get all the products.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Products");
        return productRepository.findAll(pageable)
            .map(productMapper::toDto);
    }

    /**
     * Get one product by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ProductDTO findOne(Long id) {
        log.debug("Request to get Product : {}", id);
        Product product = productRepository.findOneWithEagerRelationships(id);
        return productMapper.toDto(product);
    }

    /**
     * Delete the product by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Product : {}", id);
        productRepository.delete(id);
    }

    /**
     * Get all the products by name.
     *
     * @param pageable the pagination information and the name of the product
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
	public Page<ProductDTO> findByNameAndVisibleTrue(String name,Pageable pageable) {
    	log.debug("Request to get all Products by name ",name);
        return productRepository.findByNameIgnoreCaseAndVisibleTrue(name,pageable)
            .map(productMapper::toDto);
	}

    /**
     * Get all the products by visibility false.
     *
     * @param pageable the pagination information 
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
	public Page<ProductDTO> findByVisibleFalse(Pageable pageable) {
    	log.debug("Request to get all Products by visibility false ");
        return productRepository.findByVisibleFalse(pageable)
            .map(productMapper::toDto);
	}

    /**
     * Get all the products by visibility True.
     *
     * @param pageable the pagination information 
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
	public Page<ProductDTO> findByVisibleTrue(Pageable pageable) {
    	log.debug("Request to get all Products by visibility True ");
        return productRepository.findByVisibleTrue(pageable)
            .map(productMapper::toDto);
	}

    /**
     * Get all the products by name containing.
     *
     * @param pageable the pagination information and the name of the product
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
	public Page<ProductDTO> findByNameContainingAndVisibleTrue(String name, Pageable pageable) {
    	log.debug("Request to get all Products by name containing ",name);
        return productRepository.findByNameIgnoreCaseContainingAndVisibleTrue(pageable)
            .map(productMapper::toDto);
	}

    /**
     * Get one product by reference.
     *
     * @param reference the reference of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
	public ProductDTO findByReferenceAndVisibleTrue(String reference) {
    	log.debug("Request to get Product by reference : {}", reference);
        Product product = productRepository.findByReferenceIgnoreCaseAndVisibleTrue(reference);
        return productMapper.toDto(product);
	}

    /**
     * Get one product by sku.
     *
     * @param sku the sku of the entity
     * @return the entity
     */
	@Override
	@Transactional(readOnly=true)
	public ProductDTO findBySkuAndVisibleTrue(String sku) {
		log.debug("Request to get Product by sku : {}",  sku);
        Product product = productRepository.findBySkuIgnoreCaseAndVisibleTrue( sku);
        return productMapper.toDto(product);
	}

	 /**
     * Get one product by searchkey.
     *
     * @param searchkey the searchkey of the entity
     * @return the entity
     */
	@Override
	@Transactional(readOnly=true)
	public ProductDTO findBySearchkeyAndVisibleTrue(String searchkey) {
		log.debug("Request to get Product by searchkey : {}",  searchkey);
        Product product = productRepository.findBySearchkeyIgnoreCaseAndVisibleTrue( searchkey);
        return productMapper.toDto(product);
	}

	 /**
     * Get one product by mpn.
     *
     * @param mpn the mpn of the entity
     * @return the entity
     */
	@Override
	@Transactional(readOnly=true)
	public ProductDTO findByMpnAndVisibleTrue(String mpn) {
		log.debug("Request to get Product by mpn : {}",  mpn);
        Product product = productRepository.findByMpnIgnoreCaseAndVisibleTrue( mpn);
        return productMapper.toDto(product);
	}

	/**
     * Get one product by barcode.
     *
     * @param barcode the barcode of the entity
     * @return the entity
     */
	@Override
	@Transactional(readOnly=true)
	public ProductDTO findByBarcode_CodeAndVisibleTrue(String barcode) {
		log.debug("Request to get Product by barcode : {}",  barcode);
        Product product = productRepository.findByBarcode_CodeAndVisibleTrue( barcode);
        return productMapper.toDto(product);
	}


    /**
     * Get all the products by category name.
     *
     * @param pageable the pagination information and the name of the category
     * @return the list of entities
     */
	@Override
	@Transactional(readOnly=true)
	public Page<ProductDTO> findByCategories_NameAndVisibleTrue(String name, Pageable pageable) {
		log.debug("Request to get all Products by category name ",name);
        return productRepository.findByCategories_NameIgnoreCaseAndVisibleTrue(name,pageable)
            .map(productMapper::toDto);
	}

	/**
     * Get all the products by dateOfMfd.
     *
     * @param pageable the pagination information and the dateOfMfd of the product
     * @return the list of entities
     */
	@Override
	@Transactional(readOnly=true)
	public Page<ProductDTO> findByDateOfMfdAndVisibleTrue(LocalDate dateOfMfd, Pageable pageable) {
		log.debug("Request to get all Products by dateOfMfd ",dateOfMfd);
        return productRepository.findByDateOfMfdAndVisibleTrue(dateOfMfd,pageable)
            .map(productMapper::toDto);
	}

	/**
     * Get all the products by dateOfExpiry.
     *
     * @param pageable the pagination information and the dateOfExpiry of the product
     * @return the list of entities
     */
	@Override
	@Transactional(readOnly=true)
	public Page<ProductDTO> findByDateOfExpiryAndVisibleTrue(LocalDate dateOfExpiry, Pageable pageable) {
		log.debug("Request to get all Products by dateOfExpiry ",dateOfExpiry);
        return productRepository.findByDateOfExpiryAndVisibleTrue(dateOfExpiry,pageable)
            .map(productMapper::toDto);
	}


	/**
     * Get all the products by dateOfExpiryBetween.
     *
     * @param pageable the pagination information and the dateOfExpiry(from&to) of the product
     * @return the list of entities
     */
	@Override
	@Transactional(readOnly=true)
	public Page<ProductDTO> findByDateOfExpiryBetweenAndVisibleTrue(LocalDate from, LocalDate to, Pageable pageable) {
		log.debug("Request to get all Products by dateOfExpiry from "+from+" to "+to);
        return productRepository.findByDateOfExpiryBetweenAndVisibleTrue(from,to,pageable)
            .map(productMapper::toDto);
	}

	/**
     * Get all the products by status.
     *
     * @param pageable the pagination information and the status of the product
     * @return the list of entities
     */
	@Override
	@Transactional(readOnly=true)
	public Page<ProductDTO> findByStatus_NameAndVisibleTrue(String status, Pageable pageable) {
		log.debug("Request to get all Products by status ",status);
        return productRepository.findByStatus_NameIgnoreCaseAndVisibleTrue(status,pageable)
            .map(productMapper::toDto);
	}
}
