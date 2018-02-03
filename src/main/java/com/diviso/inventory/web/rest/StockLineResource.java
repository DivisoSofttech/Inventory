package com.diviso.inventory.web.rest;

import com.codahale.metrics.annotation.Timed;
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
}
