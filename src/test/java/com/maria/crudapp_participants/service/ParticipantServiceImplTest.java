package com.maria.crudapp_participants.service;

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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import static org.mockito.Mockito.when;

@SpringBootTest
class ParticipantServiceImplTest {

    @MockBean
    ParticipantRepository participantRepository;

    @Autowired
    ParticipantService participantService;

    private static Stream<Arguments> updateParticipantProvider() {
        Participant fromBD = new Participant(1L, "Sferiff", "football", "Moldova", 342);
        Participant edit = fromBD;
        edit.setName("Test");
        return Stream.of(
                Arguments.of(fromBD, edit),
                Arguments.of(null, edit)
        );
    }

    private static Stream<Arguments> getParticipantByIdProvider() {
        Participant fromBD = new Participant(1L, "Sferiff", "football", "Moldova", 342);

        return Stream.of(
                Arguments.of(fromBD),
                Arguments.of((Object) null)
        );
    }

    private static Stream<Arguments> searchFlexibleProvider() {
        Participant fromBD1 = new Participant(1L, "Sheriff", "football", "Moldova", 342);
        Participant fromBD2 = new Participant(2L, "Sheriff", "football", "Moldova", 342);
        List <Participant> participants1= Arrays.asList(fromBD1, fromBD2);
        Participant fromBD3 = new Participant(3L, "Colibri", "dance", "Moldova", 654);
        List <Participant> participants2= List.of(fromBD3);

        return Stream.of(
                Arguments.of("Sheriff",participants1),
                Arguments.of("dance", participants2),
                Arguments.of("test", null)
        );

    }

    @Test
    void saveParticipant() {
        Participant expected = new Participant(1L, "Sferiff", "football", "Moldova", 342);
        when(participantRepository.save(expected)).thenReturn(expected);
        Participant actual = participantService.saveParticipant(expected);
        Assertions.assertEquals(expected, actual, "Object doesn't save");
        Assertions.assertNotNull(actual, "saveParticipant shouldn't return null ");
    }

    @Test
    void fetchParticipantList() {
        Participant participant1 = new Participant(1L, "Sferiff", "football", "Moldova", 342);
        Participant participant2 = new Participant(2L, "Sferiff", "football", "Moldova", 654);
        when(participantRepository.findAll()).thenReturn(Arrays.asList(participant1, participant2));
        List<Participant> participants = participantService.fetchParticipantList();
        Assertions.assertEquals(2, participants.size(), "fetchParticipantList should return 2");
    }

    @ParameterizedTest
    @MethodSource ("getParticipantByIdProvider")
    void getParticipantById(Participant participant) {
//        Participant expected = new Participant(1L, "Sferiff", "football", "Moldova", 342);
        when(participantRepository.findById(1L)).thenReturn(Optional.ofNullable(participant));

        Participant actual = participantService.getParticipantById(1L);

//        Assertions.assertNotNull(actual, "Object with such id was not found");
        Assertions.assertEquals(participant, actual);

    }

    @ParameterizedTest
    @MethodSource("updateParticipantProvider")
    void updateParticipant(Participant participant, Participant edit) {
        when(participantRepository.findById(1L)).thenReturn(Optional.ofNullable(participant));
        Participant actual = participantService.updateParticipant(edit, 1L);
        Assertions.assertNotEquals(participant, actual);
    }

    @ParameterizedTest
    @MethodSource ("searchFlexibleProvider")
    void searchFlexible(String searchString,  List <Participant> participants) {
        when(participantRepository.searchByAllFields(searchString)).thenReturn(participants);
        List<Participant> actual=participantService.searchFlexible(searchString);
        Assertions.assertEquals(participants, actual);

    }

    @Test
    void deleteParticipantById() {
        Participant expected = new Participant(1L, "Sferiff", "football", "Moldova", 342);
        when(participantRepository.findById(1L)).thenReturn(Optional.of(expected));
        Participant actual = participantService.deleteParticipantById(1L);
        Assertions.assertNull(actual);
    }


}