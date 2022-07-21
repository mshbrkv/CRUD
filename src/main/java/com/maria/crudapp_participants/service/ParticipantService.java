package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.entity.Participant;
import java.util.List;

public interface ParticipantService {
    void saveParticipant(Participant participant);

    List<Participant> fetchParticipantList();

    void updateParticipant(Participant participant, Long participantId);

    List <Participant> searchFlexible (String searchString);

    void deleteParticipantById(Long participantId);

    Participant getParticipantById(Long participantId);

}
