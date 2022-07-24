package com.maria.crudapp_participants.controller;

import com.maria.crudapp_participants.dto.ParticipantDTO;
import com.maria.crudapp_participants.entity.Participant;
import com.maria.crudapp_participants.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/participants")
public class ParticipantController {
    private final ParticipantService participantService;
    private static final String REDIRECT_MAIN_PAGE = "redirect:/participants";

    @GetMapping
    public String fetchParticipantList(@RequestParam(required = false) String query, Model model) {
        if (query == null) {
            List<Participant> allParticipants = participantService.fetchParticipantList();
            model.addAttribute("allParticipants", allParticipants);
        } else {
            List<Participant> allParticipants = participantService.searchFlexible(query);
            model.addAttribute("allParticipants", allParticipants);
            model.addAttribute("query", query);
        }

        return "main_page";
    }

    @GetMapping("/new")
    public String newParticipantPage(Model model) {
        Participant participant = new Participant();
        model.addAttribute("participant", participant);
        return "new_participant";
    }

    @GetMapping("{id}")
    public String updateParticipantPage(@PathVariable("id") Long participantId, Model model) {
        Participant participant = participantService.getParticipantById(participantId);
        model.addAttribute("participant", participant);
        return "update_participant";
    }

    @PostMapping("save")
    public String saveParticipant(@RequestBody @ModelAttribute("participant") ParticipantDTO participantDTO) {
        Participant participant = new Participant();
        participant.setExternalId(participantDTO.getExternalId());
        participant.setName(participantDTO.getName());
        participant.setCountry(participantDTO.getCountry());
        participant.setSport(participantDTO.getSport());
        participantService.saveParticipant(participant);
        return REDIRECT_MAIN_PAGE;
    }

    @PostMapping("update")
    public String updateParticipant(@RequestBody @ModelAttribute("participant") ParticipantDTO participantDTO) {
        Participant participant = new Participant();
        participant.setId(participantDTO.getId());
        participant.setExternalId(participantDTO.getExternalId());
        participant.setName(participantDTO.getName());
        participant.setCountry(participantDTO.getCountry());
        participant.setSport(participantDTO.getSport());
        participantService.updateParticipant(participant, participantDTO.getId());
        return REDIRECT_MAIN_PAGE;
    }

    @PostMapping("/delete/{id}")
    public String deleteParticipantById(@PathVariable("id") Long participantId) {
        participantService.deleteParticipantById(participantId);
        return REDIRECT_MAIN_PAGE;
    }
}
