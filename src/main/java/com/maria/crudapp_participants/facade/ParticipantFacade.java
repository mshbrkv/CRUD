package com.maria.crudapp_participants.facade;

import com.maria.crudapp_participants.dto.ParticipantDTO;
import com.maria.crudapp_participants.entity.Participant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ParticipantFacade {
    ParticipantDTO saveParticipant(ParticipantDTO participant);

    Page<ParticipantDTO> fetchParticipantsList(String searchString, Pageable pageable);

    ParticipantDTO updateParticipant(ParticipantDTO newParticipant, UUID participantId);

    void deleteParticipantById(UUID participantId);

    ParticipantDTO findParticipantById(final UUID participantId);


}
