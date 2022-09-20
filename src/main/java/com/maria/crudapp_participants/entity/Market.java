package com.maria.crudapp_participants.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maria.crudapp_participants.selections.Selection;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "market")
    @Type(type = "json")
    @Column(name = "selections_id", columnDefinition = "jsonb")
    private List<Selection> selections; //json with selections, every selection has name and price

    public Market(UUID fromString) {
    }
}
