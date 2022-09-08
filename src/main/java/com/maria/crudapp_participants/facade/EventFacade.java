package com.maria.crudapp_participants.facade;

import com.maria.crudapp_participants.dto.EventDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.UUID;

public interface EventFacade {
    Page<EventDTO> getEventList(Pageable pageable);

    EventDTO getEventById(UUID eventId);

    Page<EventDTO> getEventInPlay(Pageable pageable);

    EventDTO saveEvent(EventDTO event);

    void deleteEventById(UUID eventId);

    EventDTO updateEvent(EventDTO newEvent, UUID eventId);

    Page<EventDTO> getEventsByMarketName(Pageable pageable, String marketName);

    Page<EventDTO> getEventsByParticipantsName(Pageable pageable, String participantName);

    Page<EventDTO> findEventsByPriceRange(BigDecimal priceFirst, BigDecimal priceSecond, Pageable pageable);
}
