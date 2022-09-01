package com.maria.crudapp_participants.repository;

import com.maria.crudapp_participants.entity.Participant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ParticipantRepository extends JpaRepository<Participant, UUID> {
//    @Query(value = "SELECT * from participants p where upper(p.name )  like upper('%${searchString}%') or upper(p.sport) like upper('%${searchString}%') or upper(p.country) like upper('%${searchString}%') or upper(p.external_Id) like upper('%${searchString}%')",
//            countQuery = "SELECT count(*) FROM participants p where upper(p.name) like upper('%${searchString}%') or upper(p.sport) like upper('%${searchString}%') or upper(p.country) like upper('%${searchString}%') or upper(p.external_Id) like upper('%${searchString}%')",
//            nativeQuery = true)

//    @Query(value = "SELECT * from participants p where upper(p.name) like upper(concat('%', :searchString, '%')) or upper(p.sport) like upper(concat('%', :searchString, '%')) or upper(p.country) like upper(concat('%', :searchString, '%')) or upper(p.external_Id) like upper(concat('%', :searchString, '%'))",
//            countQuery = "SELECT count(*) FROM participants p where upper(p.name) like upper(concat('%', :searchString, '%')) or upper(p.sport) like upper(concat('%', :searchString, '%')) or upper(p.country) like upper(concat('%', :searchString, '%')) or upper(p.external_Id) like upper(concat('%', :searchString, '%'))",
//            nativeQuery = true)

//    @Query("""
//            select p from Participant p
//            where upper(p.name) like upper(concat('%', :searchString, '%'))
//            or upper(p.sport) like upper(concat('%', :searchString, '%'))
//            or upper(p.country) like upper(concat('%', :searchString, '%'))
//            or upper(p.externalId) like upper(concat('%', :searchString, '%'))""")
//
//
////    @Query("select p from participants p where upper(p.name) like upper(concat('%', :searchStrong, '%') ) or upper(p.sport) like upper(concat('%', :searchString, '%')) or upper(p.country) like upper(concat('%', :searchString, '%')) or upper(p.external_Id) like upper(concat('%', :searchString, '%'))")
//@Query("select p from Participant p where upper(p.name) = upper(:searchString)")
//@Query("select p from Participant p where upper(p.name) like upper(:searchString)")
//@Query("select p from Participant p where upper(p.name) like upper(concat('%', :searchString, '%'))")
    //Page<Participant> findByNameContainingIgnoreCaseOrSportContainingIgnoreCaseOrCountryContainingIgnoreCaseOrExternalIdContainingIgnoreCase(@Param("searchString") String searchString, Pageable pageable);


    @Query("""
            select p from Participant p
            where upper(p.name) like upper(concat('%', ?1, '%')) or upper(p.sport) like upper(concat('%', ?2, '%')) or upper(p.country) like upper(concat('%', ?3, '%'))""")
    Page<Participant> searchFlexible(String first, String second, String third, Pageable pageable);
   // Page<Participant> searchByAllFields(@Param("searchString") String searchString, Pageable pageable);



}