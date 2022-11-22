package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.entity.Competition;
import com.maria.crudapp_participants.repository.CompetitionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;

    @Override
    public Competition saveCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

    @Override
    public List<Competition> getAllCompetition() {
        return competitionRepository.findAll();
    }

    @Override
    public Competition getCompetitionById(UUID id) {
        Optional<Competition> competitionOption = competitionRepository.findById(id);
        Competition competition = null;
        if (competitionOption.isPresent()) {
            competition = competitionOption.get();
        }
        return competition;
    }
}
