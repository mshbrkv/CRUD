package com.maria.crudapp_participants.repository;

import com.maria.crudapp_participants.entity.Participant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    @Query("""
            select p from Participant p
            where upper(p.name) like upper(concat('%', :searchString, '%'))
            or upper(p.sport) like upper(concat('%', :searchString, '%'))
            or upper(p.country) like upper(concat('%', :searchString, '%'))
            or upper(p.externalId) like upper(concat('%', :searchString, '%'))""")
    List<Participant> searchByAllFields(@Param("searchString") String searchString, Pageable pageable);
}