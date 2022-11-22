package com.maria.crudapp_participants.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maria.crudapp_participants.dto.ShortEvent;
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
@Table(name = "Competition")
public class Competition {

    private static final String MY_TIME_ZONE = "Europe/Istanbul";

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "id", nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "start_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = MY_TIME_ZONE, pattern = "yyyy-MM-dd")
    private Date startTime;

    @Column(name = "end_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = MY_TIME_ZONE, pattern = "yyyy-MM-dd")
    private Date endTime;

    @Type(type = "json")
    @Column(name = "events", columnDefinition = "jsonb")
    private List<ShortEvent> events;
}
