package com.maria.crudapp_participants.repository;

import com.maria.crudapp_participants.entity.Participant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ParticipantRepository extends JpaRepository<Participant, UUID> {
    @Query("""
            select p from Participant p
            where upper(p.name) like upper(concat('%', ?1, '%')) or upper(p.sport) like upper(concat('%', ?2, '%')) or upper(p.country) like upper(concat('%', ?3, '%'))""")
    Page<Participant> searchFlexible(String first, String second, String third, Pageable pageable);
}