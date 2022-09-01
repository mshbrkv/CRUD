package com.maria.crudapp_participants.selections;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Selection {
    private String name;
    private BigDecimal price;
}
