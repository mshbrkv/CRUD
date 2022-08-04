package com.maria.crudapp_participants.controller;

import com.maria.crudapp_participants.entity.Participant;
import com.maria.crudapp_participants.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Controller
@RequiredArgsConstructor
@RequestMapping("/participants")
public class ParticipantController {
    private final ParticipantService participantService;
    private static final String REDIRECT_MAIN_PAGE = "redirect:/participants";

    @GetMapping
    public String fetchParticipantList(@RequestParam(required = false) String query,
                                       @RequestParam(defaultValue = "1") Integer page, Model model) {
        int ITEMS_PER_PAGE = 3;
        if (query == null) {
            var allParticipants = participantService.fetchParticipantList(page, ITEMS_PER_PAGE);
            model.addAttribute("allParticipants", allParticipants.getParticipants());
            model.addAttribute("availablePages", allParticipants.getAvailablePages());
            return "main_page";
        } else {
            return
                    participantService.searchFlexible(query, page, ITEMS_PER_PAGE)
                            .map(paginatedParticipantDTO -> {
                                model.addAttribute("allParticipants", paginatedParticipantDTO.getParticipants());
                                model.addAttribute("query", query);
                                model.addAttribute("availablePages", paginatedParticipantDTO.getAvailablePages());
                                return "main_page";
                            })
                            .orElse("not_found");
        }
    }


    @GetMapping("/new")
    public String newParticipantPage(Model model) {
        Participant participant = new Participant();
        model.addAttribute("participant", participant);
        return "new_participant";
    }

    @GetMapping("{id}")
    public String updateParticipantPage(@PathVariable("id") UUID participantId, Model model) {
        participantService.getParticipantById(participantId)
                .map(participant -> model.addAttribute("participant", participant));
        return "update_participant";
    }

    @PostMapping("save")
    public String saveParticipant(@RequestBody @ModelAttribute("participant") Participant participant) {
        participantService.saveParticipant(participant);
        return REDIRECT_MAIN_PAGE;
    }

    @PostMapping("update")
    public String updateParticipant(@RequestBody @ModelAttribute("participant") Participant newParticipant) {
        participantService.updateParticipant(newParticipant, newParticipant.getId());
        return REDIRECT_MAIN_PAGE;
    }

    @PostMapping("/delete/{id}")
    public String deleteParticipantById(@PathVariable("id") UUID participantId) {
        participantService.deleteParticipantById(participantId);
        return REDIRECT_MAIN_PAGE;
    }


}
