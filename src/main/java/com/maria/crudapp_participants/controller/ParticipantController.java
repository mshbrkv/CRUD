package com.maria.crudapp_participants.controller;

import com.maria.crudapp_participants.dto.ParticipantDTO;
import com.maria.crudapp_participants.facade.ParticipantFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/participants")
public class ParticipantController {
    private final ParticipantFacade participantFacade;

    @GetMapping
    public Page<ParticipantDTO> getParticipantList(@RequestParam(required = false) String query,
                                                   Pageable pageable) {
        return participantFacade.fetchParticipantsList(query, pageable);
    }

    @GetMapping("{id}")
    public ParticipantDTO getParticipantById(@PathVariable("id") UUID participantId) {
        return participantFacade.findParticipantById(participantId);
    }

    @PostMapping
    public ParticipantDTO createParticipant(@RequestBody ParticipantDTO participant) {
        return participantFacade.saveParticipant(participant);
    }

    @PutMapping("{id}")
    public ParticipantDTO updateParticipant(@RequestBody ParticipantDTO newParticipant, @PathVariable UUID id) {
        return participantFacade.updateParticipant(newParticipant, id);
    }

    @DeleteMapping("{id}")
    public void deleteParticipantById(@PathVariable("id") UUID id) {
        participantFacade.deleteParticipantById(id);
    }
}