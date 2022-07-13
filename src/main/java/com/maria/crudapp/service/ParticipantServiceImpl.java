package com.maria.crudapp.service;

import com.maria.crudapp.entity.Participant;
import com.maria.crudapp.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService{

    private final ParticipantRepository participantRepository;

    @Override
    public Participant saveParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    @Override
    public List<Participant> fetchParticipantList() {
        return participantRepository.findAll();
    }

    @Override
    public Participant updateParticipant(Participant newParticipant, UUID participantId) {
        return participantRepository.findById(participantId).map(participant -> {
            participant.setName(newParticipant.getName());
            participant.setSport(newParticipant.getSport());
            participant.setCountry(newParticipant.getCountry());
            participant.setExternalId(newParticipant.getExternalId());
            return participantRepository.save(newParticipant);

        }).orElseGet(()-> participantRepository.save(newParticipant));

    }

    @Override
    public void deleteParticipantById(UUID participantId) {
participantRepository.deleteById(participantId);
    }
}
