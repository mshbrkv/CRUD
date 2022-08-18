package com.maria.crudapp_participants.converter;

import com.maria.crudapp_participants.dto.ParticipantDTO;
import com.maria.crudapp_participants.entity.Participant;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ParticipantToParticipantDTOConverter implements Converter<Participant, ParticipantDTO> {
    @Override
    public ParticipantDTO convert(Participant source) {
        return ParticipantDTO.builder()
                .id(source.getId())
                .name(source.getName())
                .sport(source.getSport())
                .country(source.getCountry())
                .externalId(source.getExternalId())
                .build();
    }
}
