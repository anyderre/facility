package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Facility;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Facility entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {

    @Query("select facility from Facility facility where facility.user.login = ?#{principal.username}")
    List<Facility> findByUserIsCurrentUser();

    @Query("select facility from Facility facility where facility.user.login = ?#{principal.username}")
    Page<Facility> findByUserIsCurrentUser(Pageable pageable);
}
