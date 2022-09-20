package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.entity.Participant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ParticipantService {
    Participant saveParticipant(Participant participant);

    Page<Participant> getAllParticipantList(Pageable pageable);

    Participant updateParticipant(Participant newParticipant, UUID participantId);

    Page<Participant> searchFlexible(String searchString, Pageable pageable);

    void deleteParticipantById(UUID participantId);

    Page<Participant> fetchParticipantsList(String searchString, Pageable pageable);

    Participant findParticipantById(final UUID participantId);


}