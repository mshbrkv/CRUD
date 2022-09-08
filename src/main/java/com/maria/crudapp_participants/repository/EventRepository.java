package com.maria.crudapp_participants.repository;

import com.maria.crudapp_participants.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    Page<Event> findByInPlayTrue(Pageable pageable);

    Page<Event> findByMarketsName(Pageable pageable, String marketName);

    @Query(value = "select * from events e where e.participants @> cast(:participantName as jsonb)", nativeQuery = true)
    Page<Event> findByParticipantsName(Pageable pageable, String participantName);
}
