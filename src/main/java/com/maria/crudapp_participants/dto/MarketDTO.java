package com.maria.crudapp_participants.dto;

import com.maria.crudapp_participants.entity.Event;
import com.maria.crudapp_participants.selections.Selection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarketDTO {
    private UUID id;
    private String name;
    private String marketTemplateName;
    private Event event;
    private List<Selection> selections;
}
