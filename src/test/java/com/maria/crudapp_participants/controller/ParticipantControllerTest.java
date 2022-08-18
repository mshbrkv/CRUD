package com.maria.crudapp_participants.controller;

import com.maria.crudapp_participants.dto.ParticipantDTO;
import com.maria.crudapp_participants.facade.ParticipantFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParticipantControllerTest {

    @InjectMocks
    ParticipantController participantController;
    @Mock
    private final ParticipantFacade participantFacade = mock(ParticipantFacade.class);

    @Test
    public void GetParticipantsWhenQueryIsNull() {
        Pageable pageable = PageRequest.of(0, 3);
        ParticipantDTO participant1 = new ParticipantDTO(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"), "Sferiff", "football", "Moldova", 342);
        ParticipantDTO participant2 = new ParticipantDTO(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"), "Sferiff", "football", "Moldova", 342);
        List<ParticipantDTO> participantList = Arrays.asList(participant1, participant2);
        Page<ParticipantDTO> participantPage = new PageImpl<>(participantList);
        when(participantFacade.fetchParticipantsList("", pageable)).thenReturn(participantPage);
        Page<ParticipantDTO> res = participantController.getParticipantList("", pageable);
        Assertions.assertEquals(2, res.getTotalElements());
    }

    @Test
    public void GetParticipantsWhenQueryNotNull() {
        Pageable pageable = PageRequest.of(0, 3);
        ParticipantDTO participant1 = new ParticipantDTO(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"), "Sferiff", "football", "Moldova", 342);
        ParticipantDTO participant2 = new ParticipantDTO(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"), "Sferiff", "football", "Moldova", 342);
        List<ParticipantDTO> participantList = Arrays.asList(participant1, participant2);
        Page<ParticipantDTO> participantPage = new PageImpl<>(participantList);
        when(participantFacade.fetchParticipantsList("Sh", pageable)).thenReturn(participantPage);
        Page<ParticipantDTO> res = participantController.getParticipantList("Sh", pageable);
        Assertions.assertEquals(2, res.getTotalElements());
    }

    @Test
    void createParticipant() {
        ParticipantDTO participant = new ParticipantDTO(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"), "Sferiff", "football", "Moldova", 342);
        when(participantFacade.saveParticipant(participant)).thenReturn(participant);
        ParticipantDTO res = participantController.createParticipant(participant);
        Assertions.assertEquals(participant, res);
    }

    @Test
    void updateParticipant() {
        ParticipantDTO participant = new ParticipantDTO(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"), "GGGGG", "kkkkkk", "Moldova", 342);
        when(participantFacade.updateParticipant(participant, UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"))).thenReturn(participant);
        ParticipantDTO res = participantController.updateParticipant(participant, UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
        Assertions.assertEquals(participant, res);
    }

    @Test
    void deleteParticipantById() {
        participantController.deleteParticipantById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
        verify(participantFacade).deleteParticipantById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
    }

}