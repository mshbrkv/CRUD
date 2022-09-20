package com.maria.crudapp_participants.dto;

import com.maria.crudapp_participants.entity.Market;
import com.maria.crudapp_participants.entity.Participant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    private UUID id;
    private String name;
    private Date startTime;
    private boolean inPlay;
    private List<Participant> participants;
    private List<Market> markets;
}
