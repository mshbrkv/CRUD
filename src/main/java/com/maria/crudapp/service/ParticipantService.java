package com.maria.crudapp.service;

import com.maria.crudapp.entity.Participant;
import java.util.List;

public interface ParticipantService {
    Participant saveParticipant(Participant participant);

    List<Participant> fetchParticipantList();

    Participant updateParticipant(Participant participant, Long participantId);

    void deleteParticipantById(Long participantId);
}
