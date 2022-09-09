package com.maria.crudapp_participants.facade;

import com.maria.crudapp_participants.dto.EventDTO;
import com.maria.crudapp_participants.entity.Event;
import com.maria.crudapp_participants.entity.Market;
import com.maria.crudapp_participants.selections.Selection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

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
    Page<EventDTO> getSortedEventsByPrice(Pageable pageable);

    Page<EventDTO> getEventsWithNMarketsAndNotSport(Pageable pageable,  String sport, Integer numMarkets);

    List<BigDecimal>priceOfEvent(UUID eventId);
    List<Selection>  getAveragePricesForPreMatchMarkets();
}
