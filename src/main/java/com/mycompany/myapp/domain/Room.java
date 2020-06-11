package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Room.
 */
@Entity
@Table(name = "room")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "room_number", nullable = false)
    private Integer roomNumber;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "number_of_bed")
    private Integer numberOfBed;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "creation_date")
    private Instant creationDate;

    @Column(name = "modification_date")
    private Instant modificationDate;

    @ManyToOne
    @JsonIgnoreProperties(value = "rooms", allowSetters = true)
    private Facility facility;

    @ManyToOne
    @JsonIgnoreProperties(value = "rooms", allowSetters = true)
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public Room roomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getFloor() {
        return floor;
    }

    public Room floor(Integer floor) {
        this.floor = floor;
        return this;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getNumberOfBed() {
        return numberOfBed;
    }

    public Room numberOfBed(Integer numberOfBed) {
        this.numberOfBed = numberOfBed;
        return this;
    }

    public void setNumberOfBed(Integer numberOfBed) {
        this.numberOfBed = numberOfBed;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Room capacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public Room creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getModificationDate() {
        return modificationDate;
    }

    public Room modificationDate(Instant modificationDate) {
        this.modificationDate = modificationDate;
        return this;
    }

    public void setModificationDate(Instant modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Facility getFacility() {
        return facility;
    }

    public Room facility(Facility facility) {
        this.facility = facility;
        return this;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public User getUser() {
        return user;
    }

    public Room user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Room)) {
            return false;
        }
        return id != null && id.equals(((Room) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Room{" +
            "id=" + getId() +
            ", roomNumber=" + getRoomNumber() +
            ", floor=" + getFloor() +
            ", numberOfBed=" + getNumberOfBed() +
            ", capacity=" + getCapacity() +
            ", creationDate='" + getCreationDate() + "'" +
            ", modificationDate='" + getModificationDate() + "'" +
            "}";
    }
}
