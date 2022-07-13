package com.maria.crudapp.repository;

import com.maria.crudapp.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface ParticipantRepository extends JpaRepository<Participant, UUID> {
}
