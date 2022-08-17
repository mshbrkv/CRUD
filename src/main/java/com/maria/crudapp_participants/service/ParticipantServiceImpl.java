package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.entity.Participant;
import com.maria.crudapp_participants.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ParticipantServiceImpl implements ParticipantService {
    private final ParticipantRepository participantRepository;

    private final ParticipantServiceImpl self;

    @Override
    public Participant saveParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Participant> getAllParticipantList(final Pageable pageable) {
        return participantRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Participant> searchFlexible(final String searchString, final Pageable pageable) {
        return participantRepository.searchByAllFields(searchString, pageable);
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
    public Page<Participant> fetchParticipantsList(String searchString, Pageable pageable) {

        return Optional.ofNullable(searchString)
                .map(s -> self.searchFlexible(searchString, pageable))
                .orElseGet(() -> self.getAllParticipantList(pageable));
    }

    @Override
    @Transactional(readOnly = true)
    public Participant findParticipantById(UUID participantId) {
        Optional<Participant> optional = participantRepository.findById(participantId);
        Participant participant = null;
        if (optional.isPresent()) {
            participant = optional.get();
        }
        return participant;
    }
}