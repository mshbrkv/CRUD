package com.maria.crudapp.service;

import com.maria.crudapp.entity.Participant;
import java.util.List;
import java.util.UUID;

public interface ParticipantService {
    Participant saveParticipant(Participant participant);

    List<Participant> fetchParticipantList();

    Participant updateParticipant(Participant participant, UUID participantId);

    void deleteParticipantById(UUID participantId);
}
