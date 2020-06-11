package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Room;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Room entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("select room from Room room where room.user.login = ?#{principal.username}")
    List<Room> findByUserIsCurrentUser();

    @Query("select room from Room room where room.user.login = ?#{principal.username}")
    Page<Room> findByUserIsCurrentUser(Pageable pageable);
}
