package com.maria.crudapp_participants.service;
import com.maria.crudapp_participants.entity.Participant;
import com.maria.crudapp_participants.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {
    private final ParticipantRepository participantRepository;

    @Override
    public Participant saveParticipant(Participant participant) {
        return participantRepository.save(participant);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<Participant> getAllParticipantList(int page, int perPage) {
        Pageable pageable = PageRequest.of(page , perPage);

        return participantRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Participant> searchFlexible(String searchString, int page, int perPage) {
        Pageable pageable = PageRequest.of(page, perPage);
        return participantRepository.searchByAllFields(searchString, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Participant> getParticipantById(UUID participantId) {
        return participantRepository.findById(participantId);
    }

    @Override
    @Transactional
    public Participant updateParticipant(Participant newParticipant, UUID participantId) {
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
    @Transactional
    public void deleteParticipantById(UUID participantId) {
        participantRepository.deleteById(participantId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Participant> fetchParticipantsList(String searchString, int page, int perPage) {
        if (searchString == null) {
            return this.getAllParticipantList(page, perPage);

        } else {
            return this.searchFlexible(searchString, page, perPage);
        }
    }
}
