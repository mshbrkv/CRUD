package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.entity.Participant;
import com.maria.crudapp_participants.repository.ParticipantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
class ParticipantServiceImplTest {

    @MockBean
    ParticipantRepository participantRepository;

    @Autowired
    ParticipantService participantService;

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

    @Test
    void getParticipantById() {
        Participant expected = new Participant(1L, "Sferiff", "football", "Moldova", 342);
        when(participantRepository.findById(1L)).thenReturn(Optional.of(expected));

        Participant actual = participantService.getParticipantById(1L);
        Assertions.assertNotNull(actual, "Object with such id was not found");
        Assertions.assertEquals(expected, actual, "The widget returned was not the same as the mock");

    }

    @Test
    void updateParticipant() {
        Participant expected = new Participant(1L, "Sferiff", "football", "Moldova", 342);
        Participant edit = expected;
        edit.setName("Test");
        when(participantRepository.findById(1L)).thenReturn(Optional.of(expected));

        Participant actual = participantService.updateParticipant(edit, 1L);

        Assertions.assertNotEquals(expected, actual);
    }

    @Test
    void searchFlexible() {
    }

    @Test
    void deleteParticipantById() {
    }

    @Test
    void findPage() {
    }
}