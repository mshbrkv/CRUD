package com.maria.crudapp_participants.repository;

import com.maria.crudapp_participants.selections.Selection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SelectionRepository extends JpaRepository<Selection, UUID> {


}
