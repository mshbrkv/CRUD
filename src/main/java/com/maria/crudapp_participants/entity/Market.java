package com.maria.crudapp_participants.entity;


import com.maria.crudapp_participants.selections.Selection;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@TypeDef(name = "jsonb", typeClass = JsonStringType.class)
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

    @Column(name = "event_id")
    private UUID eventId;

    @Type(type = "jsonb")
    @Column(name = "selections")
    private List<Selection> selections; //json with selections, every selection has name and price
}
