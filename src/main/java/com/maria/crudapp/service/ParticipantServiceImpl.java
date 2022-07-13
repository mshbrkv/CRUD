package com.maria.crudapp.service;

import com.maria.crudapp.entity.Participant;
import com.maria.crudapp.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;


@Service
public class ParticipantServiceImpl implements ParticipantService{

    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public Participant saveParticipant(Participant participant) {
        return null;
    }

    @Override
    public List<Participant> fetchParticipantList() {
        return null;
    }

    @Override
    public Participant updateParticipant(Participant participant, UUID participantId) {
        return null;
    }

    @Override
    public void deleteParticipantById(UUID participantId) {

    }
}
