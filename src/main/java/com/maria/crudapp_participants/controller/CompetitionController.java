package com.maria.crudapp_participants.controller;

import com.maria.crudapp_participants.entity.Competition;
import com.maria.crudapp_participants.service.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;


    @PostMapping("new_competition")
    public Competition createCompetition(@RequestBody Competition competition) {
        return competitionService.saveCompetition(competition);
    }


    @GetMapping("all_competitions")
    public List<Competition> getCompetitionList() {
        return competitionService.getAllCompetition();
    }



    @GetMapping("{id}")
    public Competition getCompetitionById(@PathVariable UUID id) {
        return competitionService.getCompetitionById(id);
    }
}
