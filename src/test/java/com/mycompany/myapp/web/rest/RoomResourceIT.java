package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.FacilityApp;
import com.mycompany.myapp.domain.Room;
import com.mycompany.myapp.repository.RoomRepository;

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
 * Integration tests for the {@link RoomResource} REST controller.
 */
@SpringBootTest(classes = FacilityApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RoomResourceIT {

    private static final Integer DEFAULT_ROOM_NUMBER = 1;
    private static final Integer UPDATED_ROOM_NUMBER = 2;

    private static final Integer DEFAULT_FLOOR = 1;
    private static final Integer UPDATED_FLOOR = 2;

    private static final Integer DEFAULT_NUMBER_OF_BED = 1;
    private static final Integer UPDATED_NUMBER_OF_BED = 2;

    private static final Integer DEFAULT_CAPACITY = 1;
    private static final Integer UPDATED_CAPACITY = 2;

    private static final Instant DEFAULT_CREATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFICATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFICATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRoomMockMvc;

    private Room room;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
//    public static Room createEntity(EntityManager em) {
//        Room room = new Room()
//            .roomNumber(DEFAULT_ROOM_NUMBER)
//            .floor(DEFAULT_FLOOR)
//            .numberOfBed(DEFAULT_NUMBER_OF_BED)
//            .capacity(DEFAULT_CAPACITY)
//            .creationDate(DEFAULT_CREATION_DATE)
//            .modificationDate(DEFAULT_MODIFICATION_DATE);
//        return room;
//    }
//    /**
//     * Create an updated entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static Room createUpdatedEntity(EntityManager em) {
//        Room room = new Room()
//            .roomNumber(UPDATED_ROOM_NUMBER)
//            .floor(UPDATED_FLOOR)
//            .numberOfBed(UPDATED_NUMBER_OF_BED)
//            .capacity(UPDATED_CAPACITY)
//            .creationDate(UPDATED_CREATION_DATE)
//            .modificationDate(UPDATED_MODIFICATION_DATE);
//        return room;
//    }
//
//    @BeforeEach
//    public void initTest() {
//        room = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    public void createRoom() throws Exception {
//        int databaseSizeBeforeCreate = roomRepository.findAll().size();
//        // Create the Room
//        restRoomMockMvc.perform(post("/api/rooms").with(csrf())
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(TestUtil.convertObjectToJsonBytes(room)))
//            .andExpect(status().isCreated());
//
//        // Validate the Room in the database
//        List<Room> roomList = roomRepository.findAll();
//        assertThat(roomList).hasSize(databaseSizeBeforeCreate + 1);
//        Room testRoom = roomList.get(roomList.size() - 1);
//        assertThat(testRoom.getRoomNumber()).isEqualTo(DEFAULT_ROOM_NUMBER);
//        assertThat(testRoom.getFloor()).isEqualTo(DEFAULT_FLOOR);
//        assertThat(testRoom.getNumberOfBed()).isEqualTo(DEFAULT_NUMBER_OF_BED);
//        assertThat(testRoom.getCapacity()).isEqualTo(DEFAULT_CAPACITY);
//        assertThat(testRoom.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
//        assertThat(testRoom.getModificationDate()).isEqualTo(DEFAULT_MODIFICATION_DATE);
//    }
//
//    @Test
//    @Transactional
//    public void createRoomWithExistingId() throws Exception {
//        int databaseSizeBeforeCreate = roomRepository.findAll().size();
//
//        // Create the Room with an existing ID
//        room.setId(1L);
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restRoomMockMvc.perform(post("/api/rooms").with(csrf())
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(TestUtil.convertObjectToJsonBytes(room)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Room in the database
//        List<Room> roomList = roomRepository.findAll();
//        assertThat(roomList).hasSize(databaseSizeBeforeCreate);
//    }
//
//
//    @Test
//    @Transactional
//    public void checkRoomNumberIsRequired() throws Exception {
//        int databaseSizeBeforeTest = roomRepository.findAll().size();
//        // set the field null
//        room.setRoomNumber(null);
//
//        // Create the Room, which fails.
//
//
//        restRoomMockMvc.perform(post("/api/rooms").with(csrf())
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(TestUtil.convertObjectToJsonBytes(room)))
//            .andExpect(status().isBadRequest());
//
//        List<Room> roomList = roomRepository.findAll();
//        assertThat(roomList).hasSize(databaseSizeBeforeTest);
//    }
//
//    @Test
//    @Transactional
//    public void getAllRooms() throws Exception {
//        // Initialize the database
//        roomRepository.saveAndFlush(room);
//
//        // Get all the roomList
//        restRoomMockMvc.perform(get("/api/rooms?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(room.getId().intValue())))
//            .andExpect(jsonPath("$.[*].roomNumber").value(hasItem(DEFAULT_ROOM_NUMBER)))
//            .andExpect(jsonPath("$.[*].floor").value(hasItem(DEFAULT_FLOOR)))
//            .andExpect(jsonPath("$.[*].numberOfBed").value(hasItem(DEFAULT_NUMBER_OF_BED)))
//            .andExpect(jsonPath("$.[*].capacity").value(hasItem(DEFAULT_CAPACITY)))
//            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
//            .andExpect(jsonPath("$.[*].modificationDate").value(hasItem(DEFAULT_MODIFICATION_DATE.toString())));
//    }
//
//    @Test
//    @Transactional
//    public void getRoom() throws Exception {
//        // Initialize the database
//        roomRepository.saveAndFlush(room);
//
//        // Get the room
//        restRoomMockMvc.perform(get("/api/rooms/{id}", room.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(room.getId().intValue()))
//            .andExpect(jsonPath("$.roomNumber").value(DEFAULT_ROOM_NUMBER))
//            .andExpect(jsonPath("$.floor").value(DEFAULT_FLOOR))
//            .andExpect(jsonPath("$.numberOfBed").value(DEFAULT_NUMBER_OF_BED))
//            .andExpect(jsonPath("$.capacity").value(DEFAULT_CAPACITY))
//            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()))
//            .andExpect(jsonPath("$.modificationDate").value(DEFAULT_MODIFICATION_DATE.toString()));
//    }
//    @Test
//    @Transactional
//    public void getNonExistingRoom() throws Exception {
//        // Get the room
//        restRoomMockMvc.perform(get("/api/rooms/{id}", Long.MAX_VALUE))
//            .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    public void updateRoom() throws Exception {
//        // Initialize the database
//        roomRepository.saveAndFlush(room);
//
//        int databaseSizeBeforeUpdate = roomRepository.findAll().size();
//
//        // Update the room
//        Room updatedRoom = roomRepository.findById(room.getId()).get();
//        // Disconnect from session so that the updates on updatedRoom are not directly saved in db
//        em.detach(updatedRoom);
//        updatedRoom
//            .roomNumber(UPDATED_ROOM_NUMBER)
//            .floor(UPDATED_FLOOR)
//            .numberOfBed(UPDATED_NUMBER_OF_BED)
//            .capacity(UPDATED_CAPACITY)
//            .creationDate(UPDATED_CREATION_DATE)
//            .modificationDate(UPDATED_MODIFICATION_DATE);
//
//        restRoomMockMvc.perform(put("/api/rooms").with(csrf())
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(TestUtil.convertObjectToJsonBytes(updatedRoom)))
//            .andExpect(status().isOk());
//
//        // Validate the Room in the database
//        List<Room> roomList = roomRepository.findAll();
//        assertThat(roomList).hasSize(databaseSizeBeforeUpdate);
//        Room testRoom = roomList.get(roomList.size() - 1);
//        assertThat(testRoom.getRoomNumber()).isEqualTo(UPDATED_ROOM_NUMBER);
//        assertThat(testRoom.getFloor()).isEqualTo(UPDATED_FLOOR);
//        assertThat(testRoom.getNumberOfBed()).isEqualTo(UPDATED_NUMBER_OF_BED);
//        assertThat(testRoom.getCapacity()).isEqualTo(UPDATED_CAPACITY);
//        assertThat(testRoom.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
//        assertThat(testRoom.getModificationDate()).isEqualTo(UPDATED_MODIFICATION_DATE);
//    }
//
//    @Test
//    @Transactional
//    public void updateNonExistingRoom() throws Exception {
//        int databaseSizeBeforeUpdate = roomRepository.findAll().size();
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restRoomMockMvc.perform(put("/api/rooms").with(csrf())
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(TestUtil.convertObjectToJsonBytes(room)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the Room in the database
//        List<Room> roomList = roomRepository.findAll();
//        assertThat(roomList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    public void deleteRoom() throws Exception {
//        // Initialize the database
//        roomRepository.saveAndFlush(room);
//
//        int databaseSizeBeforeDelete = roomRepository.findAll().size();
//
//        // Delete the room
//        restRoomMockMvc.perform(delete("/api/rooms/{id}", room.getId()).with(csrf())
//            .accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isNoContent());
//
//        // Validate the database contains one less item
//        List<Room> roomList = roomRepository.findAll();
//        assertThat(roomList).hasSize(databaseSizeBeforeDelete - 1);
//    }
}
