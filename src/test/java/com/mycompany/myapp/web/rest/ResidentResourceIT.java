package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.FacilityApp;
import com.mycompany.myapp.domain.Resident;
import com.mycompany.myapp.repository.ResidentRepository;

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
 * Integration tests for the {@link ResidentResource} REST controller.
 */
@SpringBootTest(classes = FacilityApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ResidentResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFICATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFICATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restResidentMockMvc;

    private Resident resident;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
//    public static Resident createEntity(EntityManager em) {
//        Resident resident = new Resident()
//            .firstName(DEFAULT_FIRST_NAME)
//            .lastName(DEFAULT_LAST_NAME)
//            .phoneNumber(DEFAULT_PHONE_NUMBER)
//            .email(DEFAULT_EMAIL)
//            .creationDate(DEFAULT_CREATION_DATE)
//            .modificationDate(DEFAULT_MODIFICATION_DATE);
//        return resident;
//    }
//    /**
//     * Create an updated entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static Resident createUpdatedEntity(EntityManager em) {
//        Resident resident = new Resident()
//            .firstName(UPDATED_FIRST_NAME)
//            .lastName(UPDATED_LAST_NAME)
//            .phoneNumber(UPDATED_PHONE_NUMBER)
//            .email(UPDATED_EMAIL)
//            .creationDate(UPDATED_CREATION_DATE)
//            .modificationDate(UPDATED_MODIFICATION_DATE);
//        return resident;
//    }
//
//    @BeforeEach
//    public void initTest() {
//        resident = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    public void createResident() throws Exception {
//        int databaseSizeBeforeCreate = residentRepository.findAll().size();
//        // Create the Resident
//        restResidentMockMvc.perform(post("/api/residents").with(csrf())
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(TestUtil.convertObjectToJsonBytes(resident)))
//            .andExpect(status().isCreated());
//
//        // Validate the Resident in the database
//        List<Resident> residentList = residentRepository.findAll();
//        assertThat(residentList).hasSize(databaseSizeBeforeCreate + 1);
//        Resident testResident = residentList.get(residentList.size() - 1);
//        assertThat(testResident.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
//        assertThat(testResident.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
//        assertThat(testResident.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
//        assertThat(testResident.getEmail()).isEqualTo(DEFAULT_EMAIL);
//        assertThat(testResident.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
//        assertThat(testResident.getModificationDate()).isEqualTo(DEFAULT_MODIFICATION_DATE);
//    }
//
//    @Test
//    @Transactional
//    public void createResidentWithExistingId() throws Exception {
//        int databaseSizeBeforeCreate = residentRepository.findAll().size();
//
//        // Create the Resident with an existing ID
//        resident.setId(1L);
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restResidentMockMvc.perform(post("/api/residents").with(csrf())
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(TestUtil.convertObjectToJsonBytes(resident)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Resident in the database
//        List<Resident> residentList = residentRepository.findAll();
//        assertThat(residentList).hasSize(databaseSizeBeforeCreate);
//    }
//
//
//    @Test
//    @Transactional
//    public void checkFirstNameIsRequired() throws Exception {
//        int databaseSizeBeforeTest = residentRepository.findAll().size();
//        // set the field null
//        resident.setFirstName(null);
//
//        // Create the Resident, which fails.
//
//
//        restResidentMockMvc.perform(post("/api/residents").with(csrf())
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(TestUtil.convertObjectToJsonBytes(resident)))
//            .andExpect(status().isBadRequest());
//
//        List<Resident> residentList = residentRepository.findAll();
//        assertThat(residentList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void checkLastNameIsRequired() throws Exception {
//        int databaseSizeBeforeTest = residentRepository.findAll().size();
//        // set the field null
//        resident.setLastName(null);
//
//        // Create the Resident, which fails.
//
//
//        restResidentMockMvc.perform(post("/api/residents").with(csrf())
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(TestUtil.convertObjectToJsonBytes(resident)))
//            .andExpect(status().isBadRequest());
//
//        List<Resident> residentList = residentRepository.findAll();
//        assertThat(residentList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void getAllResidents() throws Exception {
//        // Initialize the database
//        residentRepository.saveAndFlush(resident);
//
//        // Get all the residentList
//        restResidentMockMvc.perform(get("/api/residents?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(resident.getId().intValue())))
//            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
//            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
//            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
//            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
//            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
//            .andExpect(jsonPath("$.[*].modificationDate").value(hasItem(DEFAULT_MODIFICATION_DATE.toString())));
//    }
//
//    @Test
//    @Transactional
//    public void getResident() throws Exception {
//        // Initialize the database
//        residentRepository.saveAndFlush(resident);
//
//        // Get the resident
//        restResidentMockMvc.perform(get("/api/residents/{id}", resident.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(resident.getId().intValue()))
//            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
//            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
//            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
//            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
//            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()))
//            .andExpect(jsonPath("$.modificationDate").value(DEFAULT_MODIFICATION_DATE.toString()));
//    }
//    @Test
//    @Transactional
//    public void getNonExistingResident() throws Exception {
//        // Get the resident
//        restResidentMockMvc.perform(get("/api/residents/{id}", Long.MAX_VALUE))
//            .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    public void updateResident() throws Exception {
//        // Initialize the database
//        residentRepository.saveAndFlush(resident);
//
//        int databaseSizeBeforeUpdate = residentRepository.findAll().size();
//
//        // Update the resident
//        Resident updatedResident = residentRepository.findById(resident.getId()).get();
//        // Disconnect from session so that the updates on updatedResident are not directly saved in db
//        em.detach(updatedResident);
//        updatedResident
//            .firstName(UPDATED_FIRST_NAME)
//            .lastName(UPDATED_LAST_NAME)
//            .phoneNumber(UPDATED_PHONE_NUMBER)
//            .email(UPDATED_EMAIL)
//            .creationDate(UPDATED_CREATION_DATE)
//            .modificationDate(UPDATED_MODIFICATION_DATE);
//
//        restResidentMockMvc.perform(put("/api/residents").with(csrf())
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(TestUtil.convertObjectToJsonBytes(updatedResident)))
//            .andExpect(status().isOk());
//
//        // Validate the Resident in the database
//        List<Resident> residentList = residentRepository.findAll();
//        assertThat(residentList).hasSize(databaseSizeBeforeUpdate);
//        Resident testResident = residentList.get(residentList.size() - 1);
//        assertThat(testResident.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
//        assertThat(testResident.getLastName()).isEqualTo(UPDATED_LAST_NAME);
//        assertThat(testResident.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
//        assertThat(testResident.getEmail()).isEqualTo(UPDATED_EMAIL);
//        assertThat(testResident.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
//        assertThat(testResident.getModificationDate()).isEqualTo(UPDATED_MODIFICATION_DATE);
//    }
//
//    @Test
//    @Transactional
//    public void updateNonExistingResident() throws Exception {
//        int databaseSizeBeforeUpdate = residentRepository.findAll().size();
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restResidentMockMvc.perform(put("/api/residents").with(csrf())
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(TestUtil.convertObjectToJsonBytes(resident)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Resident in the database
//        List<Resident> residentList = residentRepository.findAll();
//        assertThat(residentList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    public void deleteResident() throws Exception {
//        // Initialize the database
//        residentRepository.saveAndFlush(resident);
//
//        int databaseSizeBeforeDelete = residentRepository.findAll().size();
//
//        // Delete the resident
//        restResidentMockMvc.perform(delete("/api/residents/{id}", resident.getId()).with(csrf())
//            .accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isNoContent());
//
//        // Validate the database contains one less item
//        List<Resident> residentList = residentRepository.findAll();
//        assertThat(residentList).hasSize(databaseSizeBeforeDelete - 1);
//    }
}
