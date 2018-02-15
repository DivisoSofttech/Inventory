package com.diviso.inventory.service.impl;

import com.diviso.inventory.service.ProductService;
import com.diviso.inventory.domain.Barcode;
import com.diviso.inventory.domain.Category;
import com.diviso.inventory.domain.Label;
import com.diviso.inventory.domain.Note;
import com.diviso.inventory.domain.Product;
import com.diviso.inventory.domain.Status;
import com.diviso.inventory.domain.TaxCategory;
import com.diviso.inventory.model.BarcodeModel;
import com.diviso.inventory.model.CategoryModel;
import com.diviso.inventory.model.LabelModel;
import com.diviso.inventory.model.NoteModel;
import com.diviso.inventory.model.ProductModel;
import com.diviso.inventory.model.StatusModel;
import com.diviso.inventory.model.TaxCategoryModel;
import com.diviso.inventory.repository.ProductRepository;
import com.diviso.inventory.service.dto.ProductDTO;
import com.diviso.inventory.service.mapper.ProductMapper;

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
        return productRepository.findByCategory_NameIgnoreCaseAndVisibleTrue(name,pageable)
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

	@Override
	@Transactional(readOnly=true)
	public ProductModel findMarsheldProduct(Long id) {
		
		Product product= productRepository.findOne(id);
		ProductModel productModel=new ProductModel(product.getId(),product.getName(),product.isVisible(),product.getDateOfExpiry(),product.getDateOfMfd(),product.getImage(),product.getImageContentType(),product.getDescription(),product.getMaximumStockLevel(),product.getSearchkey(),product.getSku(),product.getMpn(),product.getReOrderLevel(),product.getReference());
		Barcode barcode=product.getBarcode();
		Category category=product.getCategory();
		TaxCategory taxCategory=product.getTaxCategory();
		Status status=product.getStatus();
		productModel.setBarcode(new BarcodeModel(barcode.getId(),barcode.getCode(),barcode.getDescription()));
		productModel.setCategoryModel(new CategoryModel(category.getId(),category.getDescription(),category.getImage(),category.getImageContentType(),category.getName()));
		productModel.setTaxCategoryModel(new TaxCategoryModel(taxCategory.getId(),taxCategory.getDescription(),taxCategory.getName()));
		List<LabelModel> list=new ArrayList<LabelModel>();
		for(Label label:product.getLabels()) {
			LabelModel labelModel=new LabelModel(label.getId(),label.getDescription(),label.getName());
			list.add(labelModel);
		}
		productModel.setLabels(list);
		productModel.setStatus(new StatusModel(status.getId(),status.getDescription(),status.getName(),status.getReference()));
		return productModel;
	}

	@Override
	public List<NoteModel> findNoteByProductId(Long id,Pageable pageable) {
		Page<Note> notes=productRepository.findNotesByProductId(id, pageable);
		List<NoteModel> noteModels=new ArrayList<NoteModel> ();
		for(Note note:notes.getContent()) {
			noteModels.add(new NoteModel(note.getId(),note.getDateOfCreation(),note.getMatter()));
		}
		return noteModels;
	}
}
