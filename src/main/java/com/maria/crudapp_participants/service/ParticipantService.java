package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.dto.PaginatedParticipantDTO;
import com.maria.crudapp_participants.entity.Participant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ParticipantService {
    Participant saveParticipant(Participant participant);

    PaginatedParticipantDTO fetchParticipantList(int page, int perPage);

    Participant updateParticipant(Participant participant,  UUID participantId);

    Optional<PaginatedParticipantDTO> searchFlexible(String searchString,int page, int perPage);

    void deleteParticipantById(UUID participantId);

    Optional<Participant> getParticipantById(UUID participantId);
}
