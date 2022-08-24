package com.maria.crudapp_participants.dto;


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
public class MarketsDTO {
    private UUID id;
    private String name;
    private String marketTemplateName;
    private UUID eventId;
    private List<Selection> selections;
}
