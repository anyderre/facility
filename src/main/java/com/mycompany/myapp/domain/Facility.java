package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Facility.
 */
@Entity
@Table(name = "facility")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Facility implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "area")
    private Float area;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "creation_date")
    private Instant creationDate;

    @Column(name = "modification_date")
    private Instant modificationDate;

    @ManyToOne
    @JsonIgnoreProperties(value = "facilities", allowSetters = true)
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public Facility description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getArea() {
        return area;
    }

    public Facility area(Float area) {
        this.area = area;
        return this;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Facility quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public Facility creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getModificationDate() {
        return modificationDate;
    }

    public Facility modificationDate(Instant modificationDate) {
        this.modificationDate = modificationDate;
        return this;
    }

    public void setModificationDate(Instant modificationDate) {
        this.modificationDate = modificationDate;
    }

    public User getUser() {
        return user;
    }

    public Facility user(User user) {
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
        if (!(o instanceof Facility)) {
            return false;
        }
        return id != null && id.equals(((Facility) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Facility{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", area=" + getArea() +
            ", quantity=" + getQuantity() +
            ", creationDate='" + getCreationDate() + "'" +
            ", modificationDate='" + getModificationDate() + "'" +
            "}";
    }
}
