package com.maria.crudapp_participants.facade;

import com.maria.crudapp_participants.dto.EventDTO;
import com.maria.crudapp_participants.entity.Event;
import com.maria.crudapp_participants.selections.Selection;
import com.maria.crudapp_participants.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.OptionalDouble;
import java.util.UUID;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class EventFacadeImpl implements EventFacade {

    private final EventService eventService;
    private final Converter<Event, EventDTO> eventToEventDTOConverter;
    private final Converter<EventDTO, Event> eventDTOToEventConverter;

    @Override
    public Page<EventDTO> getEventList(Pageable pageable) {
        Page<Event> allEvent = eventService.getAllEventList(pageable);
        List<EventDTO> eventDTO = allEvent.getContent().stream().map(eventToEventDTOConverter::convert).toList();
        return new PageImpl<>(eventDTO, allEvent.getPageable(), allEvent.getTotalElements());
    }

    @Override
    public EventDTO getEventById(UUID eventId) {
        Event event = eventService.findEventById(eventId);
        return eventToEventDTOConverter.convert(event);
    }

    @Override
    public Page<EventDTO> getEventInPlay(Pageable pageable) {
        Page<Event> event = eventService.getEventInPlay(pageable);
        List<EventDTO> eventDTO = event.getContent().stream().map(eventToEventDTOConverter::convert).toList();
        return new PageImpl<>(eventDTO, event.getPageable(), event.getTotalElements());
    }

    @Override
    public EventDTO saveEvent(EventDTO event) {
        Event convert = eventDTOToEventConverter.convert(event);
        Event event1 = eventService.saveEvent(convert);
        return eventToEventDTOConverter.convert(event1);
    }

    @Override
    public void deleteEventById(UUID eventId) {
        eventService.deleteEventById(eventId);
    }

    @Override
    public EventDTO updateEvent(EventDTO newEvent, UUID eventId) {
        Event convert = eventDTOToEventConverter.convert(newEvent);
        Event event = eventService.updateEvent(convert, newEvent.getId());
        return eventToEventDTOConverter.convert(event);
    }

    @Override
    public Page<EventDTO> getEventsByMarketName(Pageable pageable, String marketName) {
        Page<Event> events = eventService.getEventsByMarketName(pageable, marketName);
        List<EventDTO> eventDTO = events.getContent().stream().map(eventToEventDTOConverter::convert).toList();
        return new PageImpl<>(eventDTO, events.getPageable(), events.getTotalElements());
    }

    @Override
    public Page<EventDTO> getEventsByParticipantsName(Pageable pageable, String participantName) {
        Page<Event> events = eventService.getEventsByParticipantsName(pageable, participantName);
        List<EventDTO> eventDTO = events.getContent().stream().map(eventToEventDTOConverter::convert).toList();
        return new PageImpl<>(eventDTO, events.getPageable(), events.getTotalElements());
    }

    @Override
    public Page<EventDTO> findEventsByPriceRange(BigDecimal priceFirst, BigDecimal priceSecond, Pageable pageable) {
        Page<Event> events = eventService.findEventsByPriceRange(priceFirst, priceSecond, pageable);
        List<EventDTO> eventsDTO = events.getContent().stream().map(eventToEventDTOConverter::convert).toList();
        return new PageImpl<>(eventsDTO, events.getPageable(), events.getTotalElements());
    }

    @Override
    public Page<EventDTO> getSortedDescendingMarketsByPrice(Pageable pageable) {
        Page<Event> events = eventService.getSortedDescendingMarketsByPrice(pageable);
        List<EventDTO> eventsDTO = events.getContent().stream().map(eventToEventDTOConverter::convert).toList();
        return new PageImpl<>(eventsDTO, events.getPageable(), events.getTotalElements());
    }

    @Override
    public Page<EventDTO> getEventsWithNMarketsAndNotSport(Pageable pageable, String sport, Integer numMarkets) {
        Page<Event> events = eventService.getEventsWithNMarketsAndNotSport(pageable, sport, numMarkets);
        List<EventDTO> eventDTO = events.getContent().stream().map(eventToEventDTOConverter::convert).toList();
        return new PageImpl<>(eventDTO, events.getPageable(), events.getTotalElements());
    }

    @Override
    public List<BigDecimal> priceOfEvent(UUID eventId) {
        return eventService.priceOfEvent(eventId);
    }

    @Override
    public List<Double> getAveragePricesForPreMatchMarkets() {
        return eventService.getAveragePricesForPreMatchMarkets();
    }

    @Override
    public BigDecimal maxPayoutPerEvent(UUID eventId) {
        return eventService.maxPayoutPerEvent(eventId);
    }

    @Override
    public List<Event> getEventsWithDuplicatedParticipantAndDifferentYears() {
        return eventService.getEventsWithDuplicatedParticipantAndDifferentYears();
    }
}