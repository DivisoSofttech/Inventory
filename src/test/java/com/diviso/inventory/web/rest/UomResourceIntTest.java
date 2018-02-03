package com.diviso.inventory.web.rest;

import com.diviso.inventory.InventoryApp;

import com.diviso.inventory.domain.Uom;
import com.diviso.inventory.repository.UomRepository;
import com.diviso.inventory.service.UomService;
import com.diviso.inventory.service.dto.UomDTO;
import com.diviso.inventory.service.mapper.UomMapper;
import com.diviso.inventory.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.diviso.inventory.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UomResource REST controller.
 *
 * @see UomResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InventoryApp.class)
public class UomResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private UomRepository uomRepository;

    @Autowired
    private UomMapper uomMapper;

    @Autowired
    private UomService uomService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUomMockMvc;

    private Uom uom;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UomResource uomResource = new UomResource(uomService);
        this.restUomMockMvc = MockMvcBuilders.standaloneSetup(uomResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Uom createEntity(EntityManager em) {
        Uom uom = new Uom()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION);
        return uom;
    }

    @Before
    public void initTest() {
        uom = createEntity(em);
    }

    @Test
    @Transactional
    public void createUom() throws Exception {
        int databaseSizeBeforeCreate = uomRepository.findAll().size();

        // Create the Uom
        UomDTO uomDTO = uomMapper.toDto(uom);
        restUomMockMvc.perform(post("/api/uoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uomDTO)))
            .andExpect(status().isCreated());

        // Validate the Uom in the database
        List<Uom> uomList = uomRepository.findAll();
        assertThat(uomList).hasSize(databaseSizeBeforeCreate + 1);
        Uom testUom = uomList.get(uomList.size() - 1);
        assertThat(testUom.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testUom.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createUomWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = uomRepository.findAll().size();

        // Create the Uom with an existing ID
        uom.setId(1L);
        UomDTO uomDTO = uomMapper.toDto(uom);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUomMockMvc.perform(post("/api/uoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uomDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Uom in the database
        List<Uom> uomList = uomRepository.findAll();
        assertThat(uomList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = uomRepository.findAll().size();
        // set the field null
        uom.setName(null);

        // Create the Uom, which fails.
        UomDTO uomDTO = uomMapper.toDto(uom);

        restUomMockMvc.perform(post("/api/uoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uomDTO)))
            .andExpect(status().isBadRequest());

        List<Uom> uomList = uomRepository.findAll();
        assertThat(uomList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUoms() throws Exception {
        // Initialize the database
        uomRepository.saveAndFlush(uom);

        // Get all the uomList
        restUomMockMvc.perform(get("/api/uoms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uom.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getUom() throws Exception {
        // Initialize the database
        uomRepository.saveAndFlush(uom);

        // Get the uom
        restUomMockMvc.perform(get("/api/uoms/{id}", uom.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(uom.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUom() throws Exception {
        // Get the uom
        restUomMockMvc.perform(get("/api/uoms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUom() throws Exception {
        // Initialize the database
        uomRepository.saveAndFlush(uom);
        int databaseSizeBeforeUpdate = uomRepository.findAll().size();

        // Update the uom
        Uom updatedUom = uomRepository.findOne(uom.getId());
        // Disconnect from session so that the updates on updatedUom are not directly saved in db
        em.detach(updatedUom);
        updatedUom
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);
        UomDTO uomDTO = uomMapper.toDto(updatedUom);

        restUomMockMvc.perform(put("/api/uoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uomDTO)))
            .andExpect(status().isOk());

        // Validate the Uom in the database
        List<Uom> uomList = uomRepository.findAll();
        assertThat(uomList).hasSize(databaseSizeBeforeUpdate);
        Uom testUom = uomList.get(uomList.size() - 1);
        assertThat(testUom.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testUom.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingUom() throws Exception {
        int databaseSizeBeforeUpdate = uomRepository.findAll().size();

        // Create the Uom
        UomDTO uomDTO = uomMapper.toDto(uom);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUomMockMvc.perform(put("/api/uoms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uomDTO)))
            .andExpect(status().isCreated());

        // Validate the Uom in the database
        List<Uom> uomList = uomRepository.findAll();
        assertThat(uomList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUom() throws Exception {
        // Initialize the database
        uomRepository.saveAndFlush(uom);
        int databaseSizeBeforeDelete = uomRepository.findAll().size();

        // Get the uom
        restUomMockMvc.perform(delete("/api/uoms/{id}", uom.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Uom> uomList = uomRepository.findAll();
        assertThat(uomList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Uom.class);
        Uom uom1 = new Uom();
        uom1.setId(1L);
        Uom uom2 = new Uom();
        uom2.setId(uom1.getId());
        assertThat(uom1).isEqualTo(uom2);
        uom2.setId(2L);
        assertThat(uom1).isNotEqualTo(uom2);
        uom1.setId(null);
        assertThat(uom1).isNotEqualTo(uom2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UomDTO.class);
        UomDTO uomDTO1 = new UomDTO();
        uomDTO1.setId(1L);
        UomDTO uomDTO2 = new UomDTO();
        assertThat(uomDTO1).isNotEqualTo(uomDTO2);
        uomDTO2.setId(uomDTO1.getId());
        assertThat(uomDTO1).isEqualTo(uomDTO2);
        uomDTO2.setId(2L);
        assertThat(uomDTO1).isNotEqualTo(uomDTO2);
        uomDTO1.setId(null);
        assertThat(uomDTO1).isNotEqualTo(uomDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(uomMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(uomMapper.fromId(null)).isNull();
    }
}
