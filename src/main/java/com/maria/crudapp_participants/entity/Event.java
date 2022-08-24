package com.maria.crudapp_participants.entity;


import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@TypeDef(name = "jsonb", typeClass = JsonStringType.class)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Events")
public class Event {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "id", nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "event_name")
    private String name;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "in_play")
    private boolean inPlay;

    @Type(type = "jsonb")
    @Column(name = "participants")
    private List<Participant> participants;//json with two participants objects

    @Type(type = "jsonb")
    @Column(name = "markets")
    private List<UUID> marketsId;//json with marketIds
}
