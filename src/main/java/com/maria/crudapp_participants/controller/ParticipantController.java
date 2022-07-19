package com.maria.crudapp_participants.controller;

import com.maria.crudapp_participants.dto.ParticipantDTO;
import com.maria.crudapp_participants.entity.Participant;
import com.maria.crudapp_participants.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/participants")
public class ParticipantController {
    private final ParticipantService participantService;

    @GetMapping
    public String fetchParticipantList(Model model) {
        List<Participant> allParticipants=participantService.fetchParticipantList();
//        List<Participant> allParticipants = List.of(new Participant(1L, "name", "sport", "country", 122));
        model.addAttribute("allParticipants", allParticipants);
        return "main_page";
    }

    @GetMapping("/new_participant")
    public String newParticipantPage(Model model){
        Participant participant = new Participant();
        model.addAttribute("participant", participant);
        return "new_participant";
    }



    @PostMapping("/save")
    public String saveParticipant(@Valid @RequestBody @ModelAttribute("participant") ParticipantDTO participantDTO) {
        Participant participant = new Participant();
        participant.setExternalId(participantDTO.getExternalId());
        participant.setName(participantDTO.getName());
        participant.setCountry(participantDTO.getCountry());
        participant.setSport(participantDTO.getSport());
         participantService.saveParticipant(participant);
         return "redirect:/";
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
    public List<Participant> searchFlexible(@RequestParam("query") String query) {
        return participantService.searchFlexible(query);
    }

    @DeleteMapping("/{id}")
    public String deleteParticipantById(@PathVariable("id") Long participantId) {
        participantService.deleteParticipantById(participantId);
        return "Deleted successfully";
    }
}