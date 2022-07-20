package com.maria.crudapp_participants.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Participants")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String sport;
    private String country;
    private int externalId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}