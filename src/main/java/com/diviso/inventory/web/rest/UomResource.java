package com.diviso.inventory.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.diviso.inventory.service.UomService;
import com.diviso.inventory.web.rest.errors.BadRequestAlertException;
import com.diviso.inventory.web.rest.util.HeaderUtil;
import com.diviso.inventory.web.rest.util.PaginationUtil;
import com.diviso.inventory.service.dto.UomDTO;
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
 * REST controller for managing Uom.
 */
@RestController
@RequestMapping("/api")
public class UomResource {

    private final Logger log = LoggerFactory.getLogger(UomResource.class);

    private static final String ENTITY_NAME = "uom";

    private final UomService uomService;

    public UomResource(UomService uomService) {
        this.uomService = uomService;
    }

    /**
     * POST  /uoms : Create a new uom.
     *
     * @param uomDTO the uomDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new uomDTO, or with status 400 (Bad Request) if the uom has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/uoms")
    @Timed
    public ResponseEntity<UomDTO> createUom(@Valid @RequestBody UomDTO uomDTO) throws URISyntaxException {
        log.debug("REST request to save Uom : {}", uomDTO);
        if (uomDTO.getId() != null) {
            throw new BadRequestAlertException("A new uom cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UomDTO result = uomService.save(uomDTO);
        return ResponseEntity.created(new URI("/api/uoms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /uoms : Updates an existing uom.
     *
     * @param uomDTO the uomDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated uomDTO,
     * or with status 400 (Bad Request) if the uomDTO is not valid,
     * or with status 500 (Internal Server Error) if the uomDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/uoms")
    @Timed
    public ResponseEntity<UomDTO> updateUom(@Valid @RequestBody UomDTO uomDTO) throws URISyntaxException {
        log.debug("REST request to update Uom : {}", uomDTO);
        if (uomDTO.getId() == null) {
            return createUom(uomDTO);
        }
        UomDTO result = uomService.save(uomDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, uomDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /uoms : get all the uoms.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of uoms in body
     */
    @GetMapping("/uoms")
    @Timed
    public ResponseEntity<List<UomDTO>> getAllUoms(Pageable pageable) {
        log.debug("REST request to get a page of Uoms");
        Page<UomDTO> page = uomService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/uoms");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /uoms/:id : get the "id" uom.
     *
     * @param id the id of the uomDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the uomDTO, or with status 404 (Not Found)
     */
    @GetMapping("/uoms/{id}")
    @Timed
    public ResponseEntity<UomDTO> getUom(@PathVariable Long id) {
        log.debug("REST request to get Uom : {}", id);
        UomDTO uomDTO = uomService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(uomDTO));
    }

    /**
     * DELETE  /uoms/:id : delete the "id" uom.
     *
     * @param id the id of the uomDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/uoms/{id}")
    @Timed
    public ResponseEntity<Void> deleteUom(@PathVariable Long id) {
        log.debug("REST request to delete Uom : {}", id);
        uomService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
