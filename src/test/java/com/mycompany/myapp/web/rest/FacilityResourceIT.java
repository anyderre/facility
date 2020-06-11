package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.FacilityApp;
import com.mycompany.myapp.domain.Facility;
import com.mycompany.myapp.repository.FacilityRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link FacilityResource} REST controller.
 */
@SpringBootTest(classes = FacilityApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FacilityResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Float DEFAULT_AREA = 1F;
    private static final Float UPDATED_AREA = 2F;

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final Instant DEFAULT_CREATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFICATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFICATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private FacilityRepository facilityRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFacilityMockMvc;

    private Facility facility;

//    /**
//     * Create an entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static Facility createEntity(EntityManager em) {
//        Facility facility = new Facility()
//            .description(DEFAULT_DESCRIPTION)
//            .area(DEFAULT_AREA)
//            .quantity(DEFAULT_QUANTITY)
//            .creationDate(DEFAULT_CREATION_DATE)
//            .modificationDate(DEFAULT_MODIFICATION_DATE);
//        return facility;
//    }
//    /**
//     * Create an updated entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static Facility createUpdatedEntity(EntityManager em) {
//        Facility facility = new Facility()
//            .description(UPDATED_DESCRIPTION)
//            .area(UPDATED_AREA)
//            .quantity(UPDATED_QUANTITY)
//            .creationDate(UPDATED_CREATION_DATE)
//            .modificationDate(UPDATED_MODIFICATION_DATE);
//        return facility;
//    }
//
//    @BeforeEach
//    public void initTest() {
//        facility = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    public void createFacility() throws Exception {
//        int databaseSizeBeforeCreate = facilityRepository.findAll().size();
//        // Create the Facility
//        restFacilityMockMvc.perform(post("/api/facilities").with(csrf())
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(TestUtil.convertObjectToJsonBytes(facility)))
//            .andExpect(status().isCreated());
//
//        // Validate the Facility in the database
//        List<Facility> facilityList = facilityRepository.findAll();
//        assertThat(facilityList).hasSize(databaseSizeBeforeCreate + 1);
//        Facility testFacility = facilityList.get(facilityList.size() - 1);
//        assertThat(testFacility.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
//        assertThat(testFacility.getArea()).isEqualTo(DEFAULT_AREA);
//        assertThat(testFacility.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
//        assertThat(testFacility.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
//        assertThat(testFacility.getModificationDate()).isEqualTo(DEFAULT_MODIFICATION_DATE);
//    }
//
//    @Test
//    @Transactional
//    public void createFacilityWithExistingId() throws Exception {
//        int databaseSizeBeforeCreate = facilityRepository.findAll().size();
//
//        // Create the Facility with an existing ID
//        facility.setId(1L);
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restFacilityMockMvc.perform(post("/api/facilities").with(csrf())
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(TestUtil.convertObjectToJsonBytes(facility)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Facility in the database
//        List<Facility> facilityList = facilityRepository.findAll();
//        assertThat(facilityList).hasSize(databaseSizeBeforeCreate);
//    }
//
//
//    @Test
//    @Transactional
//    public void checkDescriptionIsRequired() throws Exception {
//        int databaseSizeBeforeTest = facilityRepository.findAll().size();
//        // set the field null
//        facility.setDescription(null);
//
//        // Create the Facility, which fails.
//
//
//        restFacilityMockMvc.perform(post("/api/facilities").with(csrf())
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(TestUtil.convertObjectToJsonBytes(facility)))
//            .andExpect(status().isBadRequest());
//
//        List<Facility> facilityList = facilityRepository.findAll();
//        assertThat(facilityList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void getAllFacilities() throws Exception {
//        // Initialize the database
//        facilityRepository.saveAndFlush(facility);
//
//        // Get all the facilityList
//        restFacilityMockMvc.perform(get("/api/facilities?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(facility.getId().intValue())))
//            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
//            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA.doubleValue())))
//            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
//            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
//            .andExpect(jsonPath("$.[*].modificationDate").value(hasItem(DEFAULT_MODIFICATION_DATE.toString())));
//    }
//
//    @Test
//    @Transactional
//    public void getFacility() throws Exception {
//        // Initialize the database
//        facilityRepository.saveAndFlush(facility);
//
//        // Get the facility
//        restFacilityMockMvc.perform(get("/api/facilities/{id}", facility.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(facility.getId().intValue()))
//            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
//            .andExpect(jsonPath("$.area").value(DEFAULT_AREA.doubleValue()))
//            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
//            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()))
//            .andExpect(jsonPath("$.modificationDate").value(DEFAULT_MODIFICATION_DATE.toString()));
//    }
//    @Test
//    @Transactional
//    public void getNonExistingFacility() throws Exception {
//        // Get the facility
//        restFacilityMockMvc.perform(get("/api/facilities/{id}", Long.MAX_VALUE))
//            .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    public void updateFacility() throws Exception {
//        // Initialize the database
//        facilityRepository.saveAndFlush(facility);
//
//        int databaseSizeBeforeUpdate = facilityRepository.findAll().size();
//
//        // Update the facility
//        Facility updatedFacility = facilityRepository.findById(facility.getId()).get();
//        // Disconnect from session so that the updates on updatedFacility are not directly saved in db
//        em.detach(updatedFacility);
//        updatedFacility
//            .description(UPDATED_DESCRIPTION)
//            .area(UPDATED_AREA)
//            .quantity(UPDATED_QUANTITY)
//            .creationDate(UPDATED_CREATION_DATE)
//            .modificationDate(UPDATED_MODIFICATION_DATE);
//
//        restFacilityMockMvc.perform(put("/api/facilities").with(csrf())
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(TestUtil.convertObjectToJsonBytes(updatedFacility)))
//            .andExpect(status().isOk());
//
//        // Validate the Facility in the database
//        List<Facility> facilityList = facilityRepository.findAll();
//        assertThat(facilityList).hasSize(databaseSizeBeforeUpdate);
//        Facility testFacility = facilityList.get(facilityList.size() - 1);
//        assertThat(testFacility.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
//        assertThat(testFacility.getArea()).isEqualTo(UPDATED_AREA);
//        assertThat(testFacility.getQuantity()).isEqualTo(UPDATED_QUANTITY);
//        assertThat(testFacility.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
//        assertThat(testFacility.getModificationDate()).isEqualTo(UPDATED_MODIFICATION_DATE);
//    }
//
//    @Test
//    @Transactional
//    public void updateNonExistingFacility() throws Exception {
//        int databaseSizeBeforeUpdate = facilityRepository.findAll().size();
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restFacilityMockMvc.perform(put("/api/facilities").with(csrf())
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(TestUtil.convertObjectToJsonBytes(facility)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Facility in the database
//        List<Facility> facilityList = facilityRepository.findAll();
//        assertThat(facilityList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    public void deleteFacility() throws Exception {
//        // Initialize the database
//        facilityRepository.saveAndFlush(facility);
//
//        int databaseSizeBeforeDelete = facilityRepository.findAll().size();
//
//        // Delete the facility
//        restFacilityMockMvc.perform(delete("/api/facilities/{id}", facility.getId()).with(csrf())
//            .accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isNoContent());
//
//        // Validate the database contains one less item
//        List<Facility> facilityList = facilityRepository.findAll();
//        assertThat(facilityList).hasSize(databaseSizeBeforeDelete - 1);
//    }
}
