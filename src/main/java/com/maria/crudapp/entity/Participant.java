package com.maria.crudapp.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;


@Getter
@Setter

@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;
    private String name;
    private String sport;
    private String country;
    private int externalId;
}
