package com.maria.crudapp.dto;

import lombok.Data;
@Data
public class ParticipantDTO {
    private Long id;
    private String name;
    private String sport;
    private String country;
    private int externalId;
}
