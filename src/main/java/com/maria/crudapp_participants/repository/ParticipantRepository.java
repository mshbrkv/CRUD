package com.maria.crudapp_participants.repository;

import com.maria.crudapp_participants.entity.Participant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ParticipantRepository extends JpaRepository<Participant, UUID> {
    @Query(value = "SELECT * from participants p where upper(p.name) like upper(concat('%', :searchString, '%')) or upper(p.sport) like upper(concat('%', :searchString, '%')) or upper(p.country) like upper(concat('%', :searchString, '%')) or upper(p.external_Id) like upper(concat('%', :searchString, '%'))",
            countQuery = "SELECT count(*) FROM participants p where upper(p.name) like upper(concat('%', :searchString, '%')) or upper(p.sport) like upper(concat('%', :searchString, '%')) or upper(p.country) like upper(concat('%', :searchString, '%')) or upper(p.external_Id) like upper(concat('%', :searchString, '%'))",
            nativeQuery = true)
    Page<Participant> searchByAllFields(@Param("searchString") String searchString, Pageable pageable);
}