package com.maria.crudapp.controller;

import com.maria.crudapp.dto.ParticipantDTO;
import com.maria.crudapp.entity.Participant;
import com.maria.crudapp.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParticipantController {
    private final ParticipantService participantService;

    @PostMapping("/participants")
    public Participant saveParticipant(@Valid @RequestBody ParticipantDTO participantDTO) {
        Participant participant = new Participant();
        participant.setExternalId(participantDTO.getExternalId());
        participant.setName(participantDTO.getName());
        participant.setCountry(participantDTO.getCountry());
        participant.setSport(participantDTO.getSport());
        return participantService.saveParticipant(participant);
    }

    @GetMapping("/participants")
    public List<Participant> fetchParticipantList() {
        return participantService.fetchParticipantList();
    }

    @PutMapping("/participants/{id}")
    public Participant updateParticipant(@RequestBody ParticipantDTO participantDTO, @PathVariable("id") Long participantId) {
        Participant participant = new Participant();
        participant.setExternalId(participantDTO.getExternalId());
        participant.setName(participantDTO.getName());
        participant.setCountry(participantDTO.getCountry());
        participant.setSport(participantDTO.getSport());
        return participantService.updateParticipant(participant, participantId);
    }

    @DeleteMapping("/participants/{id}")
    public String deleteParticipantById(@PathVariable("id") Long participantId) {
        participantService.deleteParticipantById(participantId);
        return "Deleted successfully";
    }
}
