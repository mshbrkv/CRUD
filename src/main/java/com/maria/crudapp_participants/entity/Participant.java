package com.maria.crudapp_participants.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "participants")
public class Participant {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "id", nullable = false)
    private UUID id = UUID.randomUUID();
    private String name;
    private String sport;
    private String country;
    private int externalId;

}