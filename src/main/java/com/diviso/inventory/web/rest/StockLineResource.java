package com.diviso.inventory.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.diviso.inventory.model.StockLineModel;
import com.diviso.inventory.service.StockLineService;
import com.diviso.inventory.web.rest.errors.BadRequestAlertException;
import com.diviso.inventory.web.rest.util.HeaderUtil;
import com.diviso.inventory.web.rest.util.PaginationUtil;

import com.diviso.inventory.service.dto.StockLineDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
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
 * REST controller for managing StockLine.
 */
@RestController
@RequestMapping("/api")
public class StockLineResource {

    private final Logger log = LoggerFactory.getLogger(StockLineResource.class);

    private static final String ENTITY_NAME = "stockLine";

    private final StockLineService stockLineService;

    public StockLineResource(StockLineService stockLineService) {
        this.stockLineService = stockLineService;
    }

    /**
     * POST  /stock-lines : Create a new stockLine.
     *
     * @param stockLineDTO the stockLineDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new stockLineDTO, or with status 400 (Bad Request) if the stockLine has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/stock-lines")
    @Timed
    public ResponseEntity<StockLineDTO> createStockLine(@Valid @RequestBody StockLineDTO stockLineDTO) throws URISyntaxException {
        log.debug("REST request to save StockLine : {}", stockLineDTO);
        if (stockLineDTO.getId() != null) {
            throw new BadRequestAlertException("A new stockLine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StockLineDTO result = stockLineService.save(stockLineDTO);
        return ResponseEntity.created(new URI("/api/stock-lines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /stock-lines : Updates an existing stockLine.
     *
     * @param stockLineDTO the stockLineDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated stockLineDTO,
     * or with status 400 (Bad Request) if the stockLineDTO is not valid,
     * or with status 500 (Internal Server Error) if the stockLineDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/stock-lines")
    @Timed
    public ResponseEntity<StockLineDTO> updateStockLine(@Valid @RequestBody StockLineDTO stockLineDTO) throws URISyntaxException {
        log.debug("REST request to update StockLine : {}", stockLineDTO);
        if (stockLineDTO.getId() == null) {
            return createStockLine(stockLineDTO);
        }
        StockLineDTO result = stockLineService.save(stockLineDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, stockLineDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /stock-lines : get all the stockLines.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of stockLines in body
     */
    @GetMapping("/stock-lines")
    @Timed
    public ResponseEntity<List<StockLineDTO>> getAllStockLines(Pageable pageable) {
        log.debug("REST request to get a page of StockLines");
        Page<StockLineDTO> page = stockLineService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stock-lines");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /stock-lines/:id : get the "id" stockLine.
     *
     * @param id the id of the stockLineDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the stockLineDTO, or with status 404 (Not Found)
     */
    @GetMapping("/stock-lines/{id}")
    @Timed
    public ResponseEntity<StockLineDTO> getStockLine(@PathVariable Long id) {
        log.debug("REST request to get StockLine : {}", id);
        StockLineDTO stockLineDTO = stockLineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(stockLineDTO));
    }

    /**
     * DELETE  /stock-lines/:id : delete the "id" stockLine.
     *
     * @param id the id of the stockLineDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/stock-lines/{id}")
    @Timed
    public ResponseEntity<Void> deleteStockLine(@PathVariable Long id) {
        log.debug("REST request to delete StockLine : {}", id);
        stockLineService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    /**
     * GET  /stock-lines/findByReference/:reference : get the "reference" stock.
     *
     * @param reference the reference of the stockLineDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) StockLine_Product_ with body the stockDTO, or with status 404 (Not Found)
     */
    @GetMapping("/stock-lines/findByReference/{reference}")
    @Timed
    public ResponseEntity<StockLineDTO> getStockLineByReference(@PathVariable String reference) {
        log.debug("REST request to get StockLine : {}", reference);
        StockLineDTO stockLineDTO = stockLineService.findByReference(reference);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(stockLineDTO));
    }
    
    /**
     * GET  /stock-lines/findByProductName/:name  get all the stockLines by product name.
     *
     * @param pageable the pagination information Product_ the name of the product
     * @return the ResponseEntity with status 200 (OK) Product_ the list of stockLines in body
     */
    @GetMapping("/stock-lines/findByProductName/{name}")
    @Timed
    public ResponseEntity<List<StockLineDTO>> getAllStocksByProdcutName(@PathVariable String name,Pageable pageable) {
        log.debug("REST request to get a page of stockLines by produt name ",name);
        Page<StockLineDTO> page = stockLineService.findByProduct_NameAndProduct_VisibleTrue(name,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stock-lines/findByProductName");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /stock-lines/findByProductNameContaining/:name get all the stockLines by Product name Containing.
     *
     * @param pageable the pagination information Product_ the name of the product
     * @return the ResponseEntity with status 200 (OK) Product_ the list of stockLines in body
     */
    @GetMapping("/stock-lines/findByProductNameContaining/{name}")
    @Timed
    public ResponseEntity<List<StockLineDTO>> getAllStocksByProductNameContaining(@PathVariable String name,Pageable pageable) {
        log.debug("REST request to get a page of stockLines by Product name Containing ",name);
        Page<StockLineDTO> page = stockLineService.findByProduct_NameContainingAndProduct_VisibleTrue(name,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stock-lines/findByProductNameContaining");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /stock-lines/findByProductVisibleFalse : get all the stockLines by product visibility is false.
     *
     * @param pageable the pagination information 
     * @return the ResponseEntity with status 200 (OK) Product_ the list of stockLines in body
     */
    @GetMapping("/stock-lines/findByProductVisibleFalse")
    @Timed
    public ResponseEntity<List<StockLineDTO>> getAllStocksByVisibleFalse(Pageable pageable) {
        log.debug("REST request to get a page of stockLines by product visibility false ");
        Page<StockLineDTO> page = stockLineService.findByProduct_VisibleFalse(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stock-lines/findByProductVisibleFalse");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /stock-lines/findByProductVisibleTrue : get all the stockLines by product visibility is True.
     *
     * @param pageable the pagination information 
     * @return the ResponseEntity with status 200 (OK) Product_ the list of stockLines in body
     */
    @GetMapping("/stock-lines/findByProductVisibleTrue")
    @Timed
    public ResponseEntity<List<StockLineDTO>> getAllStocksByVisibleTrue(Pageable pageable) {
        log.debug("REST request to get a page of stockLines by product visibility True ");
        Page<StockLineDTO> page = stockLineService.findByProduct_VisibleTrue(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stock-lines/findByProductVisibleTrue");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /stock-lines/findByProductReference/:reference : get the "reference" product.
     *
     * @param reference the reference of the productDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) Product_ with body the productDTO, or with status 404 (Not Found)
     */
    @GetMapping("/stock-lines/findByProductReference/{reference}")
    @Timed
    public ResponseEntity<StockLineDTO> getStockByProductReference(@PathVariable String reference) {
        log.debug("REST request to get Product by product reference : {}", reference);
        StockLineDTO productDTO = stockLineService.findByProduct_ReferenceAndProduct_VisibleTrue(reference);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(productDTO));
    }
    
    /**
     * GET  /stock-lines/findByProductSku/:sku : get the "sku" product.
     *
     * @param sku the sku of the productDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) Product_ with body the productDTO, or with status 404 (Not Found)
     */
    @GetMapping("/stock-lines/findByProductSku/{sku}")
    @Timed
    public ResponseEntity<StockLineDTO> getStockByProductSku(@PathVariable String sku) {
        log.debug("REST request to get Product by sku : {}", sku);
        StockLineDTO productDTO = stockLineService.findByProduct_SkuAndProduct_VisibleTrue(sku);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(productDTO));
    }
    
    /**
     * GET  /stock-lines/findByProductSearchkey/:searchkey : get the "searchkey" product.
     *
     * @param searchkey the searchkey of the productDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) Product_ with body the productDTO, or with status 404 (Not Found)
     */
    @GetMapping("/stock-lines/findByProductSearchkey/{searchkey}")
    @Timed
    public ResponseEntity<StockLineDTO> getStockByProductSearchkey(@PathVariable String searchkey) {
        log.debug("REST request to get Product by searchkey : {}",searchkey);
        StockLineDTO productDTO = stockLineService.findByProduct_SearchkeyAndProduct_VisibleTrue(searchkey);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(productDTO));
    }
    
    /**
     * GET  /stock-lines/findByProductMpn/:mpn : get the "mpn" product.
     *
     * @param mpn the mpn of the productDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) Product_ with body the productDTO, or with status 404 (Not Found)
     */
    @GetMapping("/stock-lines/findByProductMpn/{mpn}")
    @Timed
    public ResponseEntity<StockLineDTO> getStockByProductMpn(@PathVariable String mpn) {
        log.debug("REST request to get Product by mpn : {}",mpn);
        StockLineDTO productDTO = stockLineService.findByProduct_MpnAndProduct_VisibleTrue(mpn);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(productDTO));
    }
    
    /**
     * GET  /stock-lines/findByProductBarcode/:barcode : get the "barcode" product.
     *
     * @param barcode the barcode of the productDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) Product_ with body the productDTO, or with status 404 (Not Found)
     */
    @GetMapping("/stock-lines/findByProductBarcode/{barcode}")
    @Timed
    public ResponseEntity<StockLineDTO> getStockByProductBarcode(@PathVariable String barcode) {
        log.debug("REST request to get Product by barcode : {}",barcode);
        StockLineDTO productDTO = stockLineService.findByProduct_Barcode_CodeAndProduct_VisibleTrue(barcode);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(productDTO));
    }
    
    /**
     * GET  /stock-lines/findByProductCategoryName/:name  get all the stockLines by category name.
     *
     * @param pageable the pagination information Product_ the name of the category
     * @return the ResponseEntity with status 200 (OK) Product_ the list of stockLines in body
     */
    @GetMapping("/stock-lines/findByProductCategoryName/{name}")
    @Timed
    public ResponseEntity<List<StockLineDTO>> getAllStocksByProductCategoryName(@PathVariable String name,Pageable pageable) {
        log.debug("REST request to get a page of stockLines by category name ",name);
        Page<StockLineDTO> page = stockLineService.findByProduct_Categories_NameAndProduct_VisibleTrue(name,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stock-lines/findByProductCategory");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /stock-lines/findByProductCategoryId/:id  get all the stockLines by category name.
     *
     * @param pageable the pagination information Product_ the name of the category
     * @return the ResponseEntity with status 200 (OK) Product_ the list of stockLines in body
     */
    @GetMapping("/stock-lines/findByProductCategoryId/{id}")
    @Timed
    public ResponseEntity<List<StockLineDTO>> getAllStocksByProductCategoryId(@PathVariable Long id,Pageable pageable) {
         log.debug("REST request to get a page of stockLines by category id ",id);
        Page<StockLineDTO> page = stockLineService.findByProduct_Category_IdAndProduct_VisibleTrue(id,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stock-lines/findByProductCategoryId");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    
    
    /**
     * GET  /stock-lines/findByProductDateOfMfd/:dateOfMfd  get all the stockLines by dateOfMfd.
     *
     * @param pageable the pagination information Product_ the dateOfMfd of the product
     * @return the ResponseEntity with status 200 (OK) Product_ the list of stockLines in body
     */
    @GetMapping("/stock-lines/findByProductDateOfMfd/{dateOfMfd}")
    @Timed
    public ResponseEntity<List<StockLineDTO>> getAllStocksByProductDateOfMfd(@PathVariable LocalDate dateOfMfd,Pageable pageable) {
        log.debug("REST request to get a page of stockLines by dateOfMfd ",dateOfMfd);
        Page<StockLineDTO> page = stockLineService.findByProduct_DateOfMfdAndProduct_VisibleTrue(dateOfMfd,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stock-lines/findByProductDateOfMfd");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /stock-lines/findByProductDateOfExpiry/:dateOfExpiry  get all the stockLines by dateOfExpiry.
     *
     * @param pageable the pagination information Product_ the dateOfExpiry of the product
     * @return the ResponseEntity with status 200 (OK) Product_ the list of stockLines in body
     */
    @GetMapping("/stock-lines/findByProductDateOfExpiry/{dateOfExpiry}")
    @Timed
    public ResponseEntity<List<StockLineDTO>> getAllStocksByProductDateOfExpiry(@PathVariable LocalDate dateOfExpiry,Pageable pageable) {
        log.debug("REST request to get a page of stockLines by dateOfExpiry ",dateOfExpiry);
        Page<StockLineDTO> page = stockLineService.findByProduct_DateOfExpiryAndProduct_VisibleTrue(dateOfExpiry,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stock-lines/findByProductDateOfExpiry");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /stock-lines/findByProductDateOfExpiryBetween/:from/:/to  get all the stockLines by dateOfExpiryBetween.
     *
     * @param pageable the pagination information Product_ the dateOfExpiry(from&to) of the product
     * @return the ResponseEntity with status 200 (OK) Product_ the list of stockLines in body
     */
    @GetMapping("/stock-lines/findByProductDateOfExpiryBetween/{from}/{to}")
    @Timed
    public ResponseEntity<List<StockLineDTO>> getAllStocksByProductDateOfExpiryBetween(@PathVariable LocalDate from,@PathVariable LocalDate to,Pageable pageable) {
        log.debug("REST request to get a page of stockLines by dateOfExpiryBetween from "+from+" to ",to);
        Page<StockLineDTO> page = stockLineService.findByProduct_DateOfExpiryBetweenAndProduct_VisibleTrue(from,to,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stock-lines/findByProductDateOfExpiryBetween");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /stock-lines/findByProductStatus/:status  get all the stockLines by status.
     *
     * @param pageable the pagination information Product_ the status of the product
     * @return the ResponseEntity with status 200 (OK) Product_ the list of stockLines in body
     */
    @GetMapping("/stock-lines/findByProductStatus/{status}")
    @Timed
    public ResponseEntity<List<StockLineDTO>> getAllStocksByProductStatus(@PathVariable String status,Pageable pageable) {
        log.debug("REST request to get a page of stockLines by status ",status);
        Page<StockLineDTO> page = stockLineService.findByProduct_Status_NameAndProduct_VisibleTrue(status,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stock-lines/findByProductStatus");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /stock-lines/findByInfrastructureId/:infrastructureId get all the stockLines by infrastructureId.
     *
     * @param pageable the pagination information and the infrastructureId of the stock-line
     * @return the ResponseEntity with status 200 (OK) and the list of stockLines in body
     */
    @GetMapping("/stock-lines/findByInfrastructureId/{infrastructureId}")
    @Timed
    public ResponseEntity<List<StockLineDTO>> getAllStockLinesInfrastructureId(@PathVariable Long infrastructureId,Pageable pageable) {
        log.debug("REST request to get a page of StockLines by infrastructureId",infrastructureId);
        Page<StockLineDTO> page = stockLineService.findByInfrastructureId(infrastructureId,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stock-lines/findByInfrastructureId");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /stock-lines/findByLocationId/:locationId get all the stockLines by locationId.
     *
     * @param pageable the pagination information and the locationId of the stock-line
     * @return the ResponseEntity with status 200 (OK) and the list of stockLines in body
     */
    @GetMapping("/stock-lines/findByLocationId/{locationId}")
    @Timed
    public ResponseEntity<List<StockLineDTO>> getAllStockLinesLocationId(@PathVariable Long locationId,Pageable pageable) {
        log.debug("REST request to get a page of StockLines by locationId",locationId);
        Page<StockLineDTO> page = stockLineService.findByLocationId(locationId,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stock-lines/findByLocationId");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /stock-lines/findBySupplierRef/:supplierRef get all the stockLines by supplierRef.
     *
     * @param pageable the pagination information and the supplierRef of the stock-line
     * @return the ResponseEntity with status 200 (OK) and the list of stockLines in body
     */
    @GetMapping("/stock-lines/findBySupplierRef/{supplierRef}")
    @Timed
    public ResponseEntity<List<StockLineDTO>> getAllStockLinesSupplierRef(@PathVariable Long supplierRef,Pageable pageable) {
        log.debug("REST request to get a page of StockLines by supplierRef",supplierRef);
        Page<StockLineDTO> page = stockLineService.findBySupplierRef(supplierRef,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stock-lines/findBySupplierRef");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    @PutMapping("/stock-lines/updateStockLevel")
    public ResponseEntity<List<StockLineDTO>>  updateStockLevel(@RequestBody ArrayList<StockLineDTO> stockLines ){
    	List<StockLineDTO> list=stockLineService.updateStockLevel(stockLines);
    	return new ResponseEntity<>(list,  HttpStatus.OK);
                
    }
    
    /**
     * GET  /stock-lines/marsheld/:id : get the "id" stockLine.
     *
     * @param id the id of the stockLineModel to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the stockLineDTO, or with status 404 (Not Found)
     */
    @GetMapping("/stock-lines/marsheld/{id}")
    @Timed
    public ResponseEntity<StockLineModel> getMarsheldStockLine(@PathVariable Long id) {
        log.debug("REST request to get StockLine : {}", id);
        StockLineModel stockLineModel = stockLineService.findMarsheldStockLine(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(stockLineModel));
    }
    
    /**
     * GET  /stock-lines : get all the stockLines.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of stockLines in body
     */
    @GetMapping("/stock-lines/findAllMarsheld")
    @Timed
    public ResponseEntity<List<StockLineModel>> getAllStockLinesMarsheld(ArrayList<StockLineDTO> dtoList) {
        log.debug("REST request to get a page of StockLines");
        List<StockLineModel> list = stockLineService.findAllStockLinesMarsheld(dtoList);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    
    
    
    
    
    
    
    

}
