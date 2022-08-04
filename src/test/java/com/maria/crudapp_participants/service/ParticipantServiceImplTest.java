package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.dto.PaginatedParticipantDTO;
import com.maria.crudapp_participants.entity.Participant;
import com.maria.crudapp_participants.repository.ParticipantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.stream.Stream;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ParticipantServiceImplTest {

    @MockBean
    ParticipantRepository participantRepository;

    @Autowired
    ParticipantService participantService;

    private static Stream<Arguments> updateParticipantProvider() {
        Participant fromBD = new Participant(UUID.randomUUID(), "Sferiff", "football", "Moldova", 342);
        Participant edit = fromBD;
        edit.setName("Test");

        return Stream.of(
                Arguments.of(fromBD, edit),
                Arguments.of(null, edit)
        );
    }

    private static Stream<Arguments> getParticipantByIdProvider() {
        Optional<Participant> fromBD = Optional.of(new Participant(UUID.randomUUID(), "Sferiff", "football", "Moldova", 342));

        return Stream.of(
                Arguments.of(fromBD),
                Arguments.of((Object) null)
        );
    }

    private static Stream<Arguments> searchFlexibleProvider() {
        Participant fromBD1 = new Participant(UUID.randomUUID(), "Sheriff", "football", "Moldova", 342);
        Participant fromBD2 = new Participant(UUID.randomUUID(), "Sheriff", "football", "Moldova", 342);
        Participant fromBD3 = new Participant(UUID.randomUUID(), "Colibri", "dance", "Moldova", 654);


        List<Participant> participants1 = Arrays.asList(fromBD1, fromBD2);
        Page<Participant> participants1Page = new PageImpl<>(participants1);
        List<Participant> participants2 = List.of(fromBD3);
        Page<Participant> participants2Page = new PageImpl<>(participants2);

        return Stream.of(
                Arguments.of("Sheriff", participants1Page),
                Arguments.of("dance", participants2Page),
                Arguments.of("test",Page.empty())
        );

    }

    @Test
    void saveParticipant() {
        Participant expected = new Participant(UUID.randomUUID(), "Sferiff", "football", "Moldova", 342);


        when(participantRepository.save(expected)).thenReturn(expected);
        Participant actual = participantService.saveParticipant(expected);

        Assertions.assertEquals(expected, actual, "Object doesn't save");
        Assertions.assertNotNull(actual, "saveParticipant shouldn't return null ");
    }

    @Test
    void fetchParticipantList() {
        Pageable pageable = PageRequest.of(0, 5);
        Participant participant1 = new Participant(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"), "Sferiff", "football", "Moldova", 342);
        Participant participant2 = new Participant(UUID.fromString("9d9260ac-1257-11ed-861d-0242ac120002"), "Sferiff", "football", "Moldova", 654);
        List <Participant> participantList= Arrays.asList(participant1,participant2);
        Page<Participant> participantPage= new PageImpl<>(participantList);
        when(participantRepository.findAll(pageable)).thenReturn(participantPage);
        PaginatedParticipantDTO participants =  participantService.fetchParticipantList(1, 5);
        Assertions.assertEquals(2, participants.getParticipants().size(), "fetchParticipantList should return 2");
    }

    @ParameterizedTest
    @MethodSource("getParticipantByIdProvider")
    void getParticipantById(Optional<Participant> participant) {
        when(participantRepository.findById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"))).thenReturn(participant);

        Optional<Participant> actual = participantService.getParticipantById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));

        Assertions.assertEquals(participant, actual);
    }

    @ParameterizedTest
    @MethodSource("updateParticipantProvider")
    void updateParticipant(Participant participant, Participant edit) {
        when(participantRepository.findById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"))).thenReturn(Optional.ofNullable(participant));

        Participant actual = participantService.updateParticipant(edit, UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));

        Assertions.assertNotEquals(participant, actual);
    }

    @ParameterizedTest
    @MethodSource("searchFlexibleProvider")
    void searchFlexible(String searchString, Page<Participant> participants) {
        Pageable pageable = PageRequest.of(0, 5);
        when(participantRepository.searchByAllFields(searchString, pageable)).thenReturn(participants);
        Optional<PaginatedParticipantDTO> actual = participantService.searchFlexible(searchString, 1, 5);
        if (actual.isEmpty()) {
            Assertions.assertEquals(participants.isEmpty(), actual.isEmpty());
        } else {
            Optional<PaginatedParticipantDTO> expected = Optional.of(PaginatedParticipantDTO.builder()
                    .participants(participants.toList())
                    .availablePages(participants.getTotalPages())
                    .build());
            Assertions.assertEquals(expected, actual);

        }
    }


    @Test
    void deleteParticipantById() {
        participantService.deleteParticipantById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
        verify(participantRepository).deleteById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));

    }


}