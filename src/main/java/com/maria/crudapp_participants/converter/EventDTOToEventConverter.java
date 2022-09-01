package com.maria.crudapp_participants.converter;

import com.maria.crudapp_participants.dto.EventDTO;
import com.maria.crudapp_participants.entity.Event;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EventDTOToEventConverter implements Converter<EventDTO, Event> {
    @Override
    public Event convert(EventDTO source) {
        return Event.builder()
                .id(source.getId())
                .name(source.getName())
                .startTime(source.getStartTime())
                .inPlay(source.isInPlay())
                .participants(source.getParticipants())
                .markets(source.getMarkets())
                .build();
    }
}
