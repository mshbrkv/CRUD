package com.maria.crudapp_participants.converter;

import com.maria.crudapp_participants.dto.ParticipantDTO;
import com.maria.crudapp_participants.entity.Participant;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ParticipantDTOToParticipantConverter implements Converter<ParticipantDTO, Participant> {
    @Override
    public Participant convert(ParticipantDTO source) {
        return Participant.builder()
                .id(source.getId())
                .name(source.getName())
                .sport(source.getSport())
                .country(source.getCountry())
                .externalId(source.getExternalId())
                .build();
    }
}
