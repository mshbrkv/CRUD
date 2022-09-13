package com.maria.crudapp_participants.repository;

import com.maria.crudapp_participants.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    Page<Event> findByInPlayTrue(Pageable pageable);

    Page<Event> findByMarketsName(Pageable pageable, String marketName);

    @Query(value = "select * from events e where e.participants @> cast(:participantName as jsonb)", nativeQuery = true)
    Page<Event> findByParticipantsName(Pageable pageable, String participantName);

    //    @Query(value = "select * from events e where e.participants @> cast(:participantName as jsonb)", nativeQuery = true)
//@Query("select e from Event e inner join e.participants participants where participants.name = ?1 and e.name = ?2")

    @Query(value = "select * from events e inner join e.participants participants group by e.start_time,participants.name  having count(participants.name)>1 and count(e.startTime)=1 order by e.name", nativeQuery = true)
    List<Event> getEventsWithDuplicatedParticipantAndDifferentYears ();
}
