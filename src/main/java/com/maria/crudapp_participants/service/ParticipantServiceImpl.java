package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.entity.Participant;
import com.maria.crudapp_participants.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {
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
    public Participant updateParticipant(Participant newParticipant, Long participantId) {
        Optional<Participant> participant=participantRepository.findById(participantId);
        if (participant.isPresent()){
            Participant editParticipant = participant.get();
            editParticipant.setName(newParticipant.getName());
            editParticipant.setSport(newParticipant.getSport());
            editParticipant.setCountry(newParticipant.getCountry());
            editParticipant.setExternalId(newParticipant.getExternalId());
            return participantRepository.save(editParticipant);
        }
        return newParticipant;
    }

    @Override
    public List<Participant> searchFlexible(String searchString) {
        return participantRepository.searchByAllFields(searchString);
    }

    @Override
    public void deleteParticipantById(Long participantId) {
        participantRepository.deleteById(participantId);
    }
}
