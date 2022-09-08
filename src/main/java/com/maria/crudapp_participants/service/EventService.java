package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.UUID;

public interface EventService {

    Page<Event> getAllEventList(Pageable pageable);

    void deleteEventById(UUID EventId);

    Page<Event> getEventInPlay(Pageable pageable);

    Page<Event> getEventsByMarketName(Pageable pageable, String marketName);

    Page<Event> getEventsByParticipantsName(Pageable pageable, String participantName);

    Event saveEvent(Event Event);

    Event updateEvent(Event newEvent, UUID EventId);

    Event findEventById(final UUID EventId);

    Page<Event> findEventsByPriceRange(BigDecimal priceFirst, BigDecimal priceSecond, Pageable pageable);
}
