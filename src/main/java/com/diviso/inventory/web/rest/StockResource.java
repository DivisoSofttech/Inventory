package com.diviso.inventory.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.diviso.inventory.model.StockModel;
import com.diviso.inventory.service.StockService;
import com.diviso.inventory.web.rest.errors.BadRequestAlertException;
import com.diviso.inventory.web.rest.util.HeaderUtil;
import com.diviso.inventory.web.rest.util.PaginationUtil;

import com.diviso.inventory.service.dto.StockDTO;
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
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Stock.
 */
@RestController
@RequestMapping("/api")
public class StockResource {

    private final Logger log = LoggerFactory.getLogger(StockResource.class);

    private static final String ENTITY_NAME = "stock";

    private final StockService stockService;

    public StockResource(StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * POST  /stocks : Create a new stock.
     *
     * @param stockDTO the stockDTO to create
     * @return the ResponseEntity with status 201 (Created) StockLine_Product_ with body the new stockDTO, or with status 400 (Bad Request) if the stock has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/stocks")
    @Timed
    public ResponseEntity<StockDTO> createStock(@Valid @RequestBody StockDTO stockDTO) throws URISyntaxException {
        log.debug("REST request to save Stock : {}", stockDTO);
        if (stockDTO.getId() != null) {
            throw new BadRequestAlertException("A new stock cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StockDTO result = stockService.save(stockDTO);
        return ResponseEntity.created(new URI("/api/stocks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /stocks : Updates an existing stock.
     *
     * @param stockDTO the stockDTO to update
     * @return the ResponseEntity with status 200 (OK) StockLine_Product_ with body the updated stockDTO,
     * or with status 400 (Bad Request) if the stockDTO is not valid,
     * or with status 500 (Internal Server Error) if the stockDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/stocks")
    @Timed
    public ResponseEntity<StockDTO> updateStock(@Valid @RequestBody StockDTO stockDTO) throws URISyntaxException {
        log.debug("REST request to update Stock : {}", stockDTO);
        if (stockDTO.getId() == null) {
            return createStock(stockDTO);
        }
        StockDTO result = stockService.save(stockDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, stockDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /stocks : get all the stocks.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) StockLine_Product_ the list of stocks in body
     */
    @GetMapping("/stocks")
    @Timed
    public ResponseEntity<List<StockDTO>> getAllStocks(Pageable pageable) {
        log.debug("REST request to get a page of Stocks");
        Page<StockDTO> page = stockService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stocks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /stocks/:id : get the "id" stock.
     *
     * @param id the id of the stockDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) StockLine_Product_ with body the stockDTO, or with status 404 (Not Found)
     */
    @GetMapping("/stocks/{id}")
    @Timed
    public ResponseEntity<StockDTO> getStock(@PathVariable Long id) {
        log.debug("REST request to get Stock : {}", id);
        StockDTO stockDTO = stockService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(stockDTO));
    }

    /**
     * DELETE  /stocks/:id : delete the "id" stock.
     *
     * @param id the id of the stockDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/stocks/{id}")
    @Timed
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        log.debug("REST request to delete Stock : {}", id);
        stockService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    /**
     * GET  /stocks/findByReference/:reference : get the "reference" stock.
     *
     * @param reference the reference of the stockDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) StockLine_Product_ with body the stockDTO, or with status 404 (Not Found)
     */
    @GetMapping("/stocks/findByReference/{reference}")
    @Timed
    public ResponseEntity<StockDTO> getStockByReference(@PathVariable String reference) {
        log.debug("REST request to get Stock : {}", reference);
        StockDTO stockDTO = stockService.findByReference(reference);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(stockDTO));
    }
    
    /**
     * GET  /stocks/findByDeliveryNoteRef/:deliveryNoteRef : get the "deliveryNoteRef" stock.
     *
     * @param deliveryNoteRef the deliveryNoteRef of the stockDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) StockLine_Product_ with body the stockDTO, or with status 404 (Not Found)
     */
    @GetMapping("/stocks/findByDeliveryNoteRef/{deliveryNoteRef}")
    @Timed
    public ResponseEntity<StockDTO> getStockByDeliveryNoteRef(@PathVariable String deliveryNoteRef) {
        log.debug("REST request to get Stock : {}", deliveryNoteRef);
        StockDTO stockDTO = stockService.findByDeliveryNoteRef(deliveryNoteRef);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(stockDTO));
    }
    
    /**
     * GET  /stocks/findByDateOfStockUpdated/:dateOfStockUpdated get all the stocks by dateOfStockUpdated.
     *
     * @param pageable the pagination information and the dateOfStockUpdated of the stock
     * @return the ResponseEntity with status 200 (OK) StockLine_Product_ the list of stocks in body
     */
    @GetMapping("/stocks/findByDateOfStockUpdated/{dateOfStockUpdated}")
    @Timed
    public ResponseEntity<List<StockDTO>> getAllStocksByDateOfStockUpdated(@PathVariable LocalDate dateOfStockUpdated,Pageable pageable) {
        log.debug("REST request to get a page of Stocks by dateOfStockUpdated",dateOfStockUpdated);
        Page<StockDTO> page = stockService.findByDateOfStockUpdated(dateOfStockUpdated,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stocks/findByDateOfStockUpdated");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /stocks/findByDateOfStockUpdatedBetween/:from/:to get all the stocks by dateOfStockUpdated.
     *
     * @param pageable the pagination information and the dateOfStockUpdated(from&to) of the stock
     * @return the ResponseEntity with status 200 (OK) StockLine_Product_ the list of stocks in body
     */
    @GetMapping("/stocks/findByDateOfStockUpdatedBetween/{from}/{to}")
    @Timed
    public ResponseEntity<List<StockDTO>> getAllStocksByDateOfStockUpdatedBetween(@PathVariable LocalDate from,@PathVariable LocalDate to,Pageable pageable) {
        log.debug("REST request to get a page of Stocks by dateOfStockUpdated between from "+from+" to "+to);
        Page<StockDTO> page = stockService.findByDateOfStockUpdatedBetween(from,to,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stocks/findByDateOfStockUpdatedBetween");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /stocks/findByStatus/:status get all the stocks by status.
     *
     * @param pageable the pagination information and the status of the stock
     * @return the ResponseEntity with status 200 (OK) StockLine_Product_ the list of stocks in body
     */
    @GetMapping("/stocks/findByStatus/{status}")
    @Timed
    public ResponseEntity<List<StockDTO>> getAllStocksByStatus(@PathVariable LocalDate status,Pageable pageable) {
        log.debug("REST request to get a page of Stocks by Status ",status);
        Page<StockDTO> page = stockService.findByStatus_Name(status,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stocks/findByStatus");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /stocks/marsheld/:id : get the "id" stock.
     *
     * @param id the id of the stockModel to retrieve
     * @return the ResponseEntity with status 200 (OK) StockLine_Product_ with body the stockDTO, or with status 404 (Not Found)
     */
    @GetMapping("/stocks/marsheld{id}")
    @Timed
    public ResponseEntity<StockModel> getMarsheldStockById(@PathVariable Long id) {
        log.debug("REST request to get Stock : {}", id);
        StockModel stockModel = stockService.findMarsheldStockById(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(stockModel));
    }
   
}
