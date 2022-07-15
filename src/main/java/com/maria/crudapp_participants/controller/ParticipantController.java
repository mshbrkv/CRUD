package com.maria.crudapp_participants.controller;

import com.maria.crudapp_participants.dto.ParticipantDTO;
import com.maria.crudapp_participants.entity.Participant;
import com.maria.crudapp_participants.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/participants")
public class ParticipantController {
    private final ParticipantService participantService;

    @PostMapping
    public Participant saveParticipant(@Valid @RequestBody ParticipantDTO participantDTO) {
        Participant participant = new Participant();
        participant.setExternalId(participantDTO.getExternalId());
        participant.setName(participantDTO.getName());
        participant.setCountry(participantDTO.getCountry());
        participant.setSport(participantDTO.getSport());
        return participantService.saveParticipant(participant);
    }

    @GetMapping
    public List<Participant> fetchParticipantList() {
        return participantService.fetchParticipantList();
    }

    @PutMapping("/{id}")
    public Participant updateParticipant(@RequestBody ParticipantDTO participantDTO, @PathVariable("id") Long participantId) {
        Participant participant = new Participant();
        participant.setExternalId(participantDTO.getExternalId());
        participant.setName(participantDTO.getName());
        participant.setCountry(participantDTO.getCountry());
        participant.setSport(participantDTO.getSport());
        return participantService.updateParticipant(participant, participantId);
    }

@GetMapping("/search")
public List <Participant> searchFlexible (@RequestParam("query") String query){
        return participantService.searchFlexible(query);
}

    @DeleteMapping("/{id}")
    public String deleteParticipantById(@PathVariable("id") Long participantId) {
        participantService.deleteParticipantById(participantId);
        return "Deleted successfully";
    }
}