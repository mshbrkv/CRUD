package com.maria.crudapp_participants.controller;

import com.maria.crudapp_participants.dto.PaginatedParticipantDTO;
import com.maria.crudapp_participants.entity.Participant;
import com.maria.crudapp_participants.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/participants")
public class ParticipantController {
    private final ParticipantService participantService;

    @GetMapping
    public Page<Participant> getParticipantList(@RequestParam(required = false) String query,
                                                @RequestParam(defaultValue = "1", required = false) Integer page) {
        int ITEMS_PER_PAGE = 3;
        return participantService.fetchParticipantsList(query, page, ITEMS_PER_PAGE);
    }


    @GetMapping("{id}")
    public Optional<Participant> getParticipant(@PathVariable UUID id){
        return participantService.getParticipantById(id);
    }

    @PostMapping
    public Participant createParticipant(@RequestBody Participant participant) {
        return participantService.saveParticipant(participant);

    }

    @PutMapping("{id}")
    public Participant updateParticipant(@RequestBody Participant newParticipant, @PathVariable UUID id) {
        return participantService.updateParticipant(newParticipant, id);
    }

    @DeleteMapping("{id}")
    public void deleteParticipantById(@PathVariable("id") UUID id) {
        participantService.deleteParticipantById(id);
    }
}
