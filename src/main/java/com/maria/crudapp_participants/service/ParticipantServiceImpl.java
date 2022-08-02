package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.entity.Participant;
import com.maria.crudapp_participants.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<Participant> fetchParticipantList(int page, int perPage) {
        Pageable pageable = PageRequest.of(page - 1, perPage);
        return participantRepository.findAll(pageable).toList();
    }
    @Override
    public List<Participant> searchFlexible(String searchString,int page, int perPage) {
        Pageable pageable = PageRequest.of(page - 1, perPage);
        return participantRepository.searchByAllFields(searchString, pageable);
    }

    @Override
    public Optional<Participant> getParticipantById(Long participantId) {
        return participantRepository.findById(participantId);
    }

    @Override
    public Participant updateParticipant(Participant newParticipant, Long participantId) {
        Optional<Participant> participant = participantRepository.findById(participantId);
        if (participant.isPresent()) {
            Participant editParticipant = participant.get();
            editParticipant.setId(newParticipant.getId());
            editParticipant.setName(newParticipant.getName());
            editParticipant.setSport(newParticipant.getSport());
            editParticipant.setCountry(newParticipant.getCountry());
            editParticipant.setExternalId(newParticipant.getExternalId());
            return participantRepository.save(editParticipant);
        } else {
            return newParticipant;
        }

    }



    @Override
    public void deleteParticipantById(Long participantId) {
       participantRepository.deleteById(participantId);
    }



}
