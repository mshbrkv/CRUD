package com.maria.crudapp_participants.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maria.crudapp_participants.selections.Selection;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

//@TypeDefs({@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class),
//        @TypeDef(name = "json", typeClass = JsonType.class)})
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Markets")
public class Market {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "id", nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "market_name")
    private String name;

    @Column(name = "market_template_name")
    private String marketTemplateName;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonIgnore
    private Event event;

    @Type(type = "json")
    @Column(name = "selections", columnDefinition = "jsonb")
    private List<Selection> selections; //json with selections, every selection has name and price
}
