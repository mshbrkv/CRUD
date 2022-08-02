package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.entity.Participant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface ParticipantService {
    Participant saveParticipant(Participant participant);

    List<Participant> fetchParticipantList(int page, int perPage);

    Participant updateParticipant(Participant participant, Long participantId);

    List<Participant> searchFlexible(String searchString,int page, int perPage);

    void deleteParticipantById(Long participantId);

    Optional<Participant> getParticipantById(Long participantId);
}
