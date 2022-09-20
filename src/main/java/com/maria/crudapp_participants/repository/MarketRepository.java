package com.maria.crudapp_participants.repository;

import com.maria.crudapp_participants.entity.Market;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yaml.snakeyaml.error.Mark;

import java.util.UUID;

public interface MarketRepository extends JpaRepository<Market, UUID> {

}
