package com.maria.crudapp_participants.selections;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maria.crudapp_participants.entity.Market;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "Selections")
public class Selection {
    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(name = "id", nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "selection_name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "market_id")
    @JsonIgnore
    private Market market;

    @Column(name="result")
    private String result;
}
