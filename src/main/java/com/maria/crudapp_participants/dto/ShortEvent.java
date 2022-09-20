package com.maria.crudapp_participants.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortEvent {
    UUID id;
    String eventName;
    Date startDate;
    String participantNames;
    String sport;
}
