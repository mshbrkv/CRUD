package com.maria.crudapp_participants.dto;

import com.maria.crudapp_participants.entity.Participant;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginatedParticipantDTO {

    private List<Participant> participants;
    private int availablePages;

}
