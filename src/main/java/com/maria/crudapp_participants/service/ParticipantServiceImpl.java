package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.dto.PaginatedParticipantDTO;
import com.maria.crudapp_participants.entity.Participant;
import com.maria.crudapp_participants.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Objects;
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
    public PaginatedParticipantDTO fetchParticipantList(int page, int perPage) {
        Pageable pageable = PageRequest.of(page - 1, perPage);

        Page<Participant> participants = participantRepository.findAll(pageable);

        return PaginatedParticipantDTO.builder()
                .participants(participants.toList())
                .availablePages(participants.getTotalPages())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PaginatedParticipantDTO> searchFlexible(String searchString, int page, int perPage) {
        Pageable pageable = PageRequest.of(page - 1, perPage);
        Page<Participant> participants = participantRepository.searchByAllFields(searchString, pageable);
        if (participants.getNumberOfElements() != 0) {
            return Optional.of(PaginatedParticipantDTO.builder()
                    .participants(participants.toList())
                    .availablePages(participants.getTotalPages())
                    .build());
        } else {
            return Optional.empty();
        }

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


}
