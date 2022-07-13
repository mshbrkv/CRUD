package com.maria.crudapp.controller;

import com.maria.crudapp.entity.Participant;
import com.maria.crudapp.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class ParticipantController {
    @Autowired
    private ParticipantService participantService;

    @PostMapping("/participants")
    public Participant saveParticipant(@Valid @RequestBody Participant participant) {
        return participantService.saveParticipant(participant);
    }

    @GetMapping("/participants")
    public List<Participant> fetchParticipantList() {
        return participantService.fetchParticipantList();
    }

    @PutMapping("/participants/{id}")
    public Participant updateParticipant(@RequestBody Participant participant, @PathVariable("id") UUID participantId) {
        return participantService.updateParticipant(participant, participantId);
    }

    @DeleteMapping("/participants/{id}")
    public String deleteParticipantById(@PathVariable("id") UUID participantId) {
        participantService.deleteParticipantById(participantId);
        return "Deleted successfully";
    }

}
