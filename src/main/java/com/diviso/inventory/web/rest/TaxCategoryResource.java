package com.diviso.inventory.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.diviso.inventory.service.TaxCategoryService;
import com.diviso.inventory.web.rest.errors.BadRequestAlertException;
import com.diviso.inventory.web.rest.util.HeaderUtil;
import com.diviso.inventory.web.rest.util.PaginationUtil;
import com.diviso.inventory.service.dto.TaxCategoryDTO;
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
 * REST controller for managing TaxCategory.
 */
@RestController
@RequestMapping("/api")
public class TaxCategoryResource {

    private final Logger log = LoggerFactory.getLogger(TaxCategoryResource.class);

    private static final String ENTITY_NAME = "taxCategory";

    private final TaxCategoryService taxCategoryService;

    public TaxCategoryResource(TaxCategoryService taxCategoryService) {
        this.taxCategoryService = taxCategoryService;
    }

    /**
     * POST  /tax-categories : Create a new taxCategory.
     *
     * @param taxCategoryDTO the taxCategoryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new taxCategoryDTO, or with status 400 (Bad Request) if the taxCategory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tax-categories")
    @Timed
    public ResponseEntity<TaxCategoryDTO> createTaxCategory(@Valid @RequestBody TaxCategoryDTO taxCategoryDTO) throws URISyntaxException {
        log.debug("REST request to save TaxCategory : {}", taxCategoryDTO);
        if (taxCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new taxCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TaxCategoryDTO result = taxCategoryService.save(taxCategoryDTO);
        return ResponseEntity.created(new URI("/api/tax-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tax-categories : Updates an existing taxCategory.
     *
     * @param taxCategoryDTO the taxCategoryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated taxCategoryDTO,
     * or with status 400 (Bad Request) if the taxCategoryDTO is not valid,
     * or with status 500 (Internal Server Error) if the taxCategoryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tax-categories")
    @Timed
    public ResponseEntity<TaxCategoryDTO> updateTaxCategory(@Valid @RequestBody TaxCategoryDTO taxCategoryDTO) throws URISyntaxException {
        log.debug("REST request to update TaxCategory : {}", taxCategoryDTO);
        if (taxCategoryDTO.getId() == null) {
            return createTaxCategory(taxCategoryDTO);
        }
        TaxCategoryDTO result = taxCategoryService.save(taxCategoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, taxCategoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tax-categories : get all the taxCategories.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of taxCategories in body
     */
    @GetMapping("/tax-categories")
    @Timed
    public ResponseEntity<List<TaxCategoryDTO>> getAllTaxCategories(Pageable pageable) {
        log.debug("REST request to get a page of TaxCategories");
        Page<TaxCategoryDTO> page = taxCategoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tax-categories");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /tax-categories/:id : get the "id" taxCategory.
     *
     * @param id the id of the taxCategoryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the taxCategoryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tax-categories/{id}")
    @Timed
    public ResponseEntity<TaxCategoryDTO> getTaxCategory(@PathVariable Long id) {
        log.debug("REST request to get TaxCategory : {}", id);
        TaxCategoryDTO taxCategoryDTO = taxCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(taxCategoryDTO));
    }

    /**
     * DELETE  /tax-categories/:id : delete the "id" taxCategory.
     *
     * @param id the id of the taxCategoryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tax-categories/{id}")
    @Timed
    public ResponseEntity<Void> deleteTaxCategory(@PathVariable Long id) {
        log.debug("REST request to delete TaxCategory : {}", id);
        taxCategoryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
