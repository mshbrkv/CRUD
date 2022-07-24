package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.entity.Participant;
import org.springframework.data.domain.Page;
import java.util.List;

public interface ParticipantService {
    Participant saveParticipant(Participant participant);

    List<Participant> fetchParticipantList();

    Participant updateParticipant(Participant participant, Long participantId);

    List <Participant> searchFlexible (String searchString);

    void deleteParticipantById(Long participantId);

    Participant getParticipantById(Long participantId);

    Page <Participant> findPage(int pageNumber);

}
