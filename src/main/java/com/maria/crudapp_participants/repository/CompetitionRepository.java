package com.maria.crudapp_participants.repository;

import com.maria.crudapp_participants.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompetitionRepository extends JpaRepository<Competition, UUID> {
}
