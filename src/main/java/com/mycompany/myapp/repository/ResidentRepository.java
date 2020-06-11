package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Resident;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Resident entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResidentRepository extends JpaRepository<Resident, Long> {

    @Query("select resident from Resident resident where resident.user.login = ?#{principal.username}")
    List<Resident> findByUserIsCurrentUser();

    @Query("select resident from Resident resident where resident.user.login = ?#{principal.username}")
    Page<Resident> findByUserIsCurrentUser(Pageable pageable);
}
