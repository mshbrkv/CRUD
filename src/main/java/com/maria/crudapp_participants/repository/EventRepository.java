package com.maria.crudapp_participants.repository;

import com.maria.crudapp_participants.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository  extends JpaRepository<Event, UUID> {

    Page<Event> findByInPlayTrue(Pageable pageable);
//
//    @Query("""
//        select e from Event e
//        where upper(e.name) like upper(concat('%', :searchString, '%')) or upper(e.startTime) like upper(concat('%', :searchString, '%')) or upper(e.inPlay) like upper(concat('%', :searchString, '%')) or upper(e.participants) like upper(concat('%', :searchString, '%')) or upper(e.marketsId) like upper(concat('%', :searchString, '%'))""")
//    Page<Event> searchByAllFields(@Param("searchString") String searchString, Pageable pageable);
}
