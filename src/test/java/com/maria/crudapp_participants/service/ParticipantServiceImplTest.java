package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.entity.Participant;
import com.maria.crudapp_participants.repository.ParticipantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParticipantServiceImplTest {

    @Mock
    ParticipantRepository participantRepository;

    @Mock
    ParticipantServiceImpl self;

    @InjectMocks
    ParticipantServiceImpl participantService;

    private static Stream<Arguments> updateParticipantProvider() {
        Participant fromBD = new Participant(UUID.randomUUID(), "Sferiff", "football", "Moldova", 342);
        Participant edit = fromBD;
        edit.setName("Test");

        return Stream.of(
                Arguments.of(fromBD, edit),
                Arguments.of(null, edit)
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
                Arguments.of("test", Page.empty())
        );
    }

    private static Stream<Arguments> findParticipantByIdProvider() {
        Participant fromBD = new Participant(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"), "Sferiff", "football", "Moldova", 342);

        return Stream.of(
                Arguments.of(fromBD),
                Arguments.of((Object) null)
        );
    }

    @Test
    void saveParticipant() {
        Participant expected = new Participant(UUID.randomUUID(), "Sferiff", "football", "Moldova", 342);

        when(participantRepository.save(expected)).thenReturn(expected);

        Participant actual = participantService.saveParticipant(expected);

        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isNotNull();
    }

    @Test
    void getAllParticipantList() {
        Pageable pageable = PageRequest.of(0, 3);
        Participant participant1 = new Participant(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"), "Sferiff", "football", "Moldova", 342);
        Participant participant2 = new Participant(UUID.fromString("9d9260ac-1257-11ed-861d-0242ac120002"), "Sferiff", "football", "Moldova", 654);
        List<Participant> participantList = Arrays.asList(participant1, participant2);
        Page<Participant> participantPage = new PageImpl<>(participantList);

        when(participantRepository.findAll(pageable))
                .thenReturn(participantPage);

        Page<Participant> actual = participantService.getAllParticipantList(pageable);

        assertThat(actual).isEqualTo(participantPage);
    }

    @ParameterizedTest
    @MethodSource("updateParticipantProvider")
    void updateParticipant(Participant participant, Participant edit) {
        when(participantRepository.findById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"))).thenReturn(Optional.ofNullable(participant));

        Participant actual = participantService.updateParticipant(edit, UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));

        assertThat(actual).isNotEqualTo(participant);
    }

    @ParameterizedTest
    @MethodSource("searchFlexibleProvider")
    void searchFlexible(String searchString, Page<Participant> participants) {
        Pageable pageable = PageRequest.of(0, 3);

        when(participantRepository.searchByAllFields(searchString, PageRequest.of(1, 20))).thenReturn(participants);

        Page<Participant> result = participantService.searchFlexible(searchString, pageable);

        switch (searchString) {
            case "test" -> Assertions.assertEquals(result, Page.empty());
            default -> {
                assertThat(result).isNotNull();
                assertThat(result.toList().size()).isEqualTo(participants.getSize());
            }
        }
    }

    @Test
    void deleteParticipantById() {
        participantService.deleteParticipantById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
        verify(participantRepository).deleteById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
    }

    @Test
    void fetchParticipantsListSearchStringNull() {
        Pageable pageable = mock(Pageable.class);
        Page<Participant> participants = mock(Page.class);

        when(self.getAllParticipantList(pageable)).thenReturn(participants);

        Page<Participant> result = participantService.fetchParticipantsList(null, pageable);

        assertThat(result).isEqualTo(participants);
        verify(self, never()).searchFlexible(anyString(), any(Pageable.class));
    }

    @Test
    void fetchParticipantsListSearchStringNotNull() {
        Pageable pageable = mock(Pageable.class);
        Page<Participant> participants = mock(Page.class);
        String participant = "participant";

        when(self.searchFlexible(participant, pageable)).thenReturn(participants);

        Page<Participant> result = participantService.fetchParticipantsList(participant, pageable);

        assertThat(result).isEqualTo(participants);
        verify(self, never()).getAllParticipantList(any(Pageable.class));
    }

    @ParameterizedTest
    @MethodSource("findParticipantByIdProvider")
    void findParticipantById(Participant participant) {
        when(participantRepository.findById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"))).thenReturn(Optional.ofNullable(participant));

        Participant actual = participantService.findParticipantById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));

        assertThat(actual).isEqualTo(participant);
    }
}