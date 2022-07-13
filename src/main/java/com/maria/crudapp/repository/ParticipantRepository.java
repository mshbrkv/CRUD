package com.maria.crudapp.repository;

import com.maria.crudapp.entity.Participant;
import org.springframework.data.repository.CrudRepository;

public interface ParticipantRepository extends CrudRepository<Participant, Long> {
}
