package com.maria.crudapp.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class ParticipantDTO {
    private UUID id;
    private String name;
    private String sport;
    private String country;
    private int externalId;
}
