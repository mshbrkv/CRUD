package com.maria.crudapp_participants.repository;

import com.maria.crudapp_participants.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    Page<Event> findByInPlayTrue(Pageable pageable);

    @Query("select e from Event e inner join e.markets markets where markets.name = ?1")
    Page<Event> findByMarketsName(Pageable pageable, String marketName);

    Page<Event> findByParticipantsName(Pageable pageable, String participantName);
}
