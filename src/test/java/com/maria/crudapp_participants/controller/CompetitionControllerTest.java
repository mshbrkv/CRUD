package com.maria.crudapp_participants.controller;

import com.maria.crudapp_participants.dto.ShortEvent;
import com.maria.crudapp_participants.entity.Competition;
import com.maria.crudapp_participants.service.CompetitionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompetitionControllerTest {
    @Mock
    CompetitionService competitionService;

    @InjectMocks
    CompetitionController competitionController;

    @Test
    void createCompetition() {
        Competition expected = new Competition(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"), "aaa", Date.valueOf("2027-09-10"), Date.valueOf("2027-09-10"), List.of(new ShortEvent(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"), "Dance Championship", Date.valueOf("2027-09-10"), "Bravo Jazz Team-Encore Jazz Team", "dance")));
        when(competitionService.saveCompetition(expected)).thenReturn(expected);
        Competition actual = competitionController.createCompetition(expected);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getCompetitionList() {
        Competition competition1 = new Competition();
        Competition competition2 = new Competition();
        List<Competition> expected = List.of(competition1, competition2);
        when(competitionService.getAllCompetition()).thenReturn(expected);
        List<Competition> actual = competitionController.getCompetitionList();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getCompetitionById() {
        Competition expected = new Competition(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"), "aaa", Date.valueOf("2027-09-10"), Date.valueOf("2027-09-10"), List.of(new ShortEvent(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"), "Dance Championship", Date.valueOf("2027-09-10"), "Bravo Jazz Team-Encore Jazz Team", "dance")));
        when(competitionService.getCompetitionById(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"))).thenReturn(expected);
        Competition actual = competitionController.getCompetitionById(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"));
        assertThat(actual).isEqualTo(expected);
    }
}