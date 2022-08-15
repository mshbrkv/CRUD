package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.dto.PaginatedParticipantDTO;
import com.maria.crudapp_participants.entity.Participant;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;


public interface ParticipantService {
    Participant saveParticipant(Participant participant);

    Page<Participant> getAllParticipantList(int page, int perPage);

    Participant updateParticipant(Participant participant, UUID participantId);

    Page<Participant> searchFlexible(String searchString, int page, int perPage);

    void deleteParticipantById(UUID participantId);

    Optional<Participant> getParticipantById(UUID participantId);

    Page <Participant> fetchParticipantsList(String searchString, int page, int perPage);


}
