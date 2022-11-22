package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.entity.Competition;

import java.util.List;
import java.util.UUID;

public interface CompetitionService {

    Competition saveCompetition(Competition competition);

    List<Competition> getAllCompetition();

    Competition getCompetitionById(UUID id);
}
