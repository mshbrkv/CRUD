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
    public void saveParticipant(Participant participant) {
        participantRepository.save(participant);
    }


    @Override
    public List<Participant> fetchParticipantList() {
        return participantRepository.findAll();
    }

    @Override
    public Participant getParticipantById(Long participantId) {
        Optional<Participant> optional = participantRepository.findById(participantId);
        Participant participant = null;
        if (optional.isPresent()) {
            participant = optional.get();
        }
        return participant;
    }

    @Override
    public void updateParticipant(Participant newParticipant, Long participantId) {
        Optional<Participant> participant = participantRepository.findById(participantId);
        if (participant.isPresent()) {
            Participant editParticipant = participant.get();
            editParticipant.setId(newParticipant.getId());
            editParticipant.setName(newParticipant.getName());
            editParticipant.setSport(newParticipant.getSport());
            editParticipant.setCountry(newParticipant.getCountry());
            editParticipant.setExternalId(newParticipant.getExternalId());
            participantRepository.save(editParticipant);
        }
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
