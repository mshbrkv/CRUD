package com.maria.crudapp_participants.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@TypeDefs({@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class),
        @TypeDef(name = "json", typeClass = JsonType.class)})

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Events")
public class Event {
    private static final String MY_TIME_ZONE = "Europe/Istanbul";

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "id", nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "event_name")
    private String name;

    @Column(name = "start_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = MY_TIME_ZONE, pattern = "yyyy-MM-dd, HH:mm")
    private Date startTime;

    @Column(name = "in_play")
    private boolean inPlay;


    @Type(type = "json")
    @Column(name = "participants", columnDefinition = "jsonb")
    private List<Participant> participants;//json with two participants objects


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "event")
    @Type(type = "json")
    @Column(name = "market_id", columnDefinition = "jsonb")
    private List<Market> markets;


    public boolean isPreMatch() {

        return startTime.getTime() > new Date().getTime();
    }
}

