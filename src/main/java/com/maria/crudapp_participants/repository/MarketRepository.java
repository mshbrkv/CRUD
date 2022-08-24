package com.maria.crudapp_participants.repository;

import com.maria.crudapp_participants.entity.Market;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface MarketRepository extends JpaRepository<Market, UUID> {
//
//    @Query("""
//        select m from Market m
//        where upper(m.name) like upper(concat('%', :searchString, '%')) or upper(m.marketTemplateName) like upper(concat('%', :searchString, '%')) or upper(m.eventId) like upper(concat('%', :searchString, '%')) or upper(m.selections) like upper(concat('%', :searchString, '%'))""")
//    Page<Market> searchByAllFields(@Param("searchString") String searchString, Pageable pageable);
}
