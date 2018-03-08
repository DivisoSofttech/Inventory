package com.diviso.inventory.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.diviso.inventory.model.NoteModel;
import com.diviso.inventory.model.ProductModel;
import com.diviso.inventory.service.ProductService;
import com.diviso.inventory.web.rest.errors.BadRequestAlertException;
import com.diviso.inventory.web.rest.util.HeaderUtil;
import com.diviso.inventory.web.rest.util.PaginationUtil;
import com.diviso.inventory.service.dto.ProductDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Product.
 */
@RestController
@RequestMapping("/api")
public class ProductResource {

    private final Logger log = LoggerFactory.getLogger(ProductResource.class);

    private static final String ENTITY_NAME = "product";

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    /**
     * POST  /products : Create a new product.
     *
     * @param productDTO the productDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new productDTO, or with status 400 (Bad Request) if the product has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/products")
    @Timed
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) throws URISyntaxException {
        log.debug("REST request to save Product : {}", productDTO);
        if (productDTO.getId() != null) {
            throw new BadRequestAlertException("A new product cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductDTO result = productService.save(productDTO);
        return ResponseEntity.created(new URI("/api/products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /products : Updates an existing product.
     *
     * @param productDTO the productDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated productDTO,
     * or with status 400 (Bad Request) if the productDTO is not valid,
     * or with status 500 (Internal Server Error) if the productDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/products")
    @Timed
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO) throws URISyntaxException {
        log.debug("REST request to update Product : {}", productDTO);
        if (productDTO.getId() == null) {
            return createProduct(productDTO);
        }
        ProductDTO result = productService.save(productDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, productDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /products : get all the products.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of products in body
     */
    @GetMapping("/products")
    @Timed
    public ResponseEntity<List<ProductDTO>> getAllProducts(Pageable pageable) {
        log.debug("REST request to get a page of Products");
        Page<ProductDTO> page = productService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/products");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /products : get all the products.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of products in body
     */
    @PostMapping("/products/findAllMarsheld")
    @Timed
    public ResponseEntity<List<ProductModel>> getAllProductsMarsheld(@RequestBody ArrayList<ProductDTO> dtoList,Pageable pageable) {
        log.debug("REST request to get a page of Products");
        List<ProductModel> list = productService.findAllProductsMarsheld(dtoList,pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    

    /**
     * GET  /products/:id : get the "id" product.
     *
     * @param id the id of the productDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productDTO, or with status 404 (Not Found)
     */
    @GetMapping("/products/{id}")
    @Timed
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        log.debug("REST request to get Product : {}", id);
        ProductDTO productDTO = productService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(productDTO));
    }
    
    /**
     * GET  /products/marsheld/:id : get the "id" product.
     *
     * @param id the id of the productDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productDTO, or with status 404 (Not Found)
     */
    @GetMapping("/products/marsheld/{id}")
    @Timed
    public ResponseEntity<ProductModel> getMarsheldProductById(@PathVariable Long id) {
        log.debug("REST request to get Product : {}", id);
        ProductModel productModel = productService.findMarsheldProduct(id);
        List<NoteModel> noteModels=productService.findNoteByProductId(id,new PageRequest(0, 10));
        productModel.setNotes(noteModels);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(productModel));
    }
    

    /**
     * GET  /products/notes/findByProductId/:id : get the "productId" notes.
     *
     * @param id the id of the productDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the NoteModel, or with status 404 (Not Found)
     */
    @GetMapping("/products/notes/findByProductId/{id}")
    @Timed
    public ResponseEntity<List<NoteModel>> getNotesByProductId(@PathVariable Long id,Pageable pageable ){
    	log.debug("REST request to get NoteModels by product id : {}", id);
    	return new ResponseEntity<>(productService.findNoteByProductId(id, pageable),HttpStatus.OK);
    }

    /**
     * DELETE  /products/:id : delete the "id" product.
     *
     * @param id the id of the productDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/products/{id}")
    @Timed
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.debug("REST request to delete Product : {}", id);
        productService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    /**
     * GET  /products/findByName/:name  get all the products by name.
     *
     * @param pageable the pagination information and the name of the product
     * @return the ResponseEntity with status 200 (OK) and the list of products in body
     */
    @GetMapping("/products/findByName/{name}")
    @Timed
    public ResponseEntity<List<ProductDTO>> getAllProductsByName(@PathVariable String name,Pageable pageable) {
        log.debug("REST request to get a page of Products by name ",name);
        Page<ProductDTO> page = productService.findByNameAndVisibleTrue(name,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/products/findByName");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /products/findByNameContaining/:name get all the products by name Containing.
     *
     * @param pageable the pagination information and the name of the product
     * @return the ResponseEntity with status 200 (OK) and the list of products in body
     */
    @GetMapping("/products/findByNameContaining/{name}")
    @Timed
    public ResponseEntity<List<ProductDTO>> getAllProductsByNameContaining(@PathVariable String name,Pageable pageable) {
        log.debug("REST request to get a page of Products by name Containing ",name);
        Page<ProductDTO> page = productService.findByNameContainingAndVisibleTrue(name,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/products/findByNameContaining");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /products/findByVisibleFalse : get all the products by visibility is false.
     *
     * @param pageable the pagination information 
     * @return the ResponseEntity with status 200 (OK) and the list of products in body
     */
    @GetMapping("/products/findByVisibleFalse")
    @Timed
    public ResponseEntity<List<ProductDTO>> getAllProductsByVisibleFalse(Pageable pageable) {
        log.debug("REST request to get a page of Products by visibility false ");
        Page<ProductDTO> page = productService.findByVisibleFalse(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/products/findByVisibleFalse");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /products/findByVisibleTrue : get all the products by visibility is True.
     *
     * @param pageable the pagination information 
     * @return the ResponseEntity with status 200 (OK) and the list of products in body
     */
    @GetMapping("/products/findByVisibleTrue")
    @Timed
    public ResponseEntity<List<ProductDTO>> getAllProductsByVisibleTrue(Pageable pageable) {
        log.debug("REST request to get a page of Products by visibility True ");
        Page<ProductDTO> page = productService.findByVisibleTrue(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/products/findByVisibleTrue");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /products/findByReference/:reference : get the "reference" product.
     *
     * @param reference the reference of the productDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productDTO, or with status 404 (Not Found)
     */
    @GetMapping("/products/findByReference/{reference}")
    @Timed
    public ResponseEntity<ProductDTO> getProductByReference(@PathVariable String reference) {
        log.debug("REST request to get Product by reference : {}", reference);
        ProductDTO productDTO = productService.findByReferenceAndVisibleTrue(reference);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(productDTO));
    }
    
    /**
     * GET  /products/findBySku/:sku : get the "sku" product.
     *
     * @param sku the sku of the productDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productDTO, or with status 404 (Not Found)
     */
    @GetMapping("/products/findBySku/{sku}")
    @Timed
    public ResponseEntity<ProductDTO> getProductBySku(@PathVariable String sku) {
        log.debug("REST request to get Product by sku : {}", sku);
        ProductDTO productDTO = productService.findBySkuAndVisibleTrue(sku);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(productDTO));
    }
    
    /**
     * GET  /products/findBySearchkey/:searchkey : get the "searchkey" product.
     *
     * @param searchkey the searchkey of the productDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productDTO, or with status 404 (Not Found)
     */
    @GetMapping("/products/findBySearchkey/{searchkey}")
    @Timed
    public ResponseEntity<ProductDTO> getProductBySearchkey(@PathVariable String searchkey) {
        log.debug("REST request to get Product by searchkey : {}",searchkey);
        ProductDTO productDTO = productService.findBySearchkeyAndVisibleTrue(searchkey);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(productDTO));
    }
    
    /**
     * GET  /products/findByMpn/:mpn : get the "mpn" product.
     *
     * @param mpn the mpn of the productDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productDTO, or with status 404 (Not Found)
     */
    @GetMapping("/products/findByMpn/{mpn}")
    @Timed
    public ResponseEntity<ProductDTO> getProductByMpn(@PathVariable String mpn) {
        log.debug("REST request to get Product by mpn : {}",mpn);
        ProductDTO productDTO = productService.findByMpnAndVisibleTrue(mpn);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(productDTO));
    }
    
    /**
     * GET  /products/findByBarcode/:barcode : get the "barcode" product.
     *
     * @param barcode the barcode of the productDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productDTO, or with status 404 (Not Found)
     */
    @GetMapping("/products/findByBarcode/{barcode}")
    @Timed
    public ResponseEntity<ProductDTO> getProductByBarcode(@PathVariable String barcode) {
        log.debug("REST request to get Product by barcode : {}",barcode);
        ProductDTO productDTO = productService.findByBarcode_CodeAndVisibleTrue(barcode);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(productDTO));
    }
    
    /**
     * GET  /products/findByCategory/:name  get all the products by category name.
     *
     * @param pageable the pagination information and the name of the category
     * @return the ResponseEntity with status 200 (OK) and the list of products in body
     */
    @GetMapping("/products/findByCategory/{name}")
    @Timed
    public ResponseEntity<List<ProductDTO>> getAllProductsByCategory(@PathVariable String name,Pageable pageable) {
        log.debug("REST request to get a page of Products by category name ",name);
        Page<ProductDTO> page = productService.findByCategories_NameAndVisibleTrue(name,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/products/findByCategory");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /products/findByDateOfMfd/:dateOfMfd  get all the products by dateOfMfd.
     *
     * @param pageable the pagination information and the dateOfMfd of the product
     * @return the ResponseEntity with status 200 (OK) and the list of products in body
     */
    @GetMapping("/products/findByDateOfMfd/{dateOfMfd}")
    @Timed
    public ResponseEntity<List<ProductDTO>> getAllProductsByDateOfMfd(@PathVariable LocalDate dateOfMfd,Pageable pageable) {
        log.debug("REST request to get a page of Products by dateOfMfd ",dateOfMfd);
        Page<ProductDTO> page = productService.findByDateOfMfdAndVisibleTrue(dateOfMfd,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/products/findByDateOfMfd");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /products/findByDateOfExpiry/:dateOfExpiry  get all the products by dateOfExpiry.
     *
     * @param pageable the pagination information and the dateOfExpiry of the product
     * @return the ResponseEntity with status 200 (OK) and the list of products in body
     */
    @GetMapping("/products/findByDateOfExpiry/{dateOfExpiry}")
    @Timed
    public ResponseEntity<List<ProductDTO>> getAllProductsByDateOfExpiry(@PathVariable LocalDate dateOfExpiry,Pageable pageable) {
        log.debug("REST request to get a page of Products by dateOfExpiry ",dateOfExpiry);
        Page<ProductDTO> page = productService.findByDateOfExpiryAndVisibleTrue(dateOfExpiry,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/products/findByDateOfExpiry");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /products/findByDateOfExpiryBetween/:from/:/to  get all the products by dateOfExpiryBetween.
     *
     * @param pageable the pagination information and the dateOfExpiry(from&to) of the product
     * @return the ResponseEntity with status 200 (OK) and the list of products in body
     */
    @GetMapping("/products/findByDateOfExpiryBetween/{from}/{to}")
    @Timed
    public ResponseEntity<List<ProductDTO>> getAllProductsByDateOfExpiryBetween(@PathVariable LocalDate from,@PathVariable LocalDate to,Pageable pageable) {
        log.debug("REST request to get a page of Products by dateOfExpiryBetween from "+from+" to ",to);
        Page<ProductDTO> page = productService.findByDateOfExpiryBetweenAndVisibleTrue(from,to,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/products/findByDateOfExpiryBetween");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /products/findByStatus/:status  get all the products by status.
     *
     * @param pageable the pagination information and the status of the product
     * @return the ResponseEntity with status 200 (OK) and the list of products in body
     */
    @GetMapping("/products/findByStatus/{status}")
    @Timed
    public ResponseEntity<List<ProductDTO>> getAllProductsByStatus(@PathVariable String status,Pageable pageable) {
        log.debug("REST request to get a page of Products by status ",status);
        Page<ProductDTO> page = productService.findByStatus_NameAndVisibleTrue(status,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/products/findByStatus");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    
    
}
