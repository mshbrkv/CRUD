package com.maria.crudapp_participants.facade;

import com.maria.crudapp_participants.dto.EventDTO;
import com.maria.crudapp_participants.entity.Event;
import com.maria.crudapp_participants.service.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventFacadeImplTest {

    private final EventService eventService;
    private final Converter<Event, EventDTO> eventToEventDTOConverter;
    private final Converter<EventDTO, Event> eventDTOEventConverter;
    private final EventFacadeImpl eventFacade;

    public EventFacadeImplTest() {
        this.eventService = mock(EventService.class);
        this.eventToEventDTOConverter = mock(Converter.class);
        this.eventDTOEventConverter = mock(Converter.class);
        this.eventFacade = new EventFacadeImpl(eventService, eventToEventDTOConverter, eventDTOEventConverter);
    }

    @Test
    void getEventList() {
        Pageable pageable = PageRequest.of(0, 3);
        Page<Event> events = mock(Page.class);
        List<EventDTO> eventDTO = new ArrayList<>();

        when(eventService.getAllEventList(pageable)).thenReturn(events);
        when(events.getContent().stream().map(eventToEventDTOConverter::convert).toList()).thenReturn(eventDTO);
        when(events.getPageable()).thenReturn(pageable);
        when(events.getTotalElements()).thenReturn(3L);
        Page<EventDTO> expected = new PageImpl<>(eventDTO, pageable, 3L);
        Page<EventDTO> actual = eventFacade.getEventList(pageable);
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void getEventById() {
        EventDTO eventDTO = mock(EventDTO.class);
        Event event = mock(Event.class);
        when(eventService.findEventById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"))).thenReturn(event);
        when(eventToEventDTOConverter.convert(event)).thenReturn(eventDTO);

        EventDTO actual = eventFacade.getEventById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
        assertThat(actual).isEqualTo(eventDTO);

    }

    @Test
    void getEventInPlay() {
        Pageable pageable = PageRequest.of(0, 3);
        Page<Event> events = mock(Page.class);
        List<EventDTO> eventDTO = new ArrayList<>();

        when(eventService.getEventInPlay(pageable)).thenReturn(events);
        when(events.getContent().stream().map(eventToEventDTOConverter::convert).toList()).thenReturn(eventDTO);
        when(events.getPageable()).thenReturn(pageable);
        when(events.getTotalElements()).thenReturn(3L);
        Page<EventDTO> expected = new PageImpl<>(eventDTO, pageable, 3L);
        Page<EventDTO> actual = eventFacade.getEventInPlay(pageable);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void saveEvent() {
        Event event = mock(Event.class);
        EventDTO eventDTO = mock(EventDTO.class);

        when(eventDTOEventConverter.convert(eventDTO)).thenReturn(event);
        when(eventService.saveEvent(event)).thenReturn(event);
        when(eventToEventDTOConverter.convert(event)).thenReturn(eventDTO);
        EventDTO actual = eventFacade.saveEvent(eventDTO);
        assertThat(actual).isEqualTo(eventDTO);

    }

    @Test
    void deleteEventById() {
        eventFacade.deleteEventById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
        verify(eventService, times(1)).deleteEventById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
    }

    @Test
    void updateEvent() {
        Event event = mock(Event.class);
        EventDTO eventDTO = mock(EventDTO.class);

        when(eventDTOEventConverter.convert(eventDTO)).thenReturn(event);
        when(eventService.updateEvent(event, event.getId())).thenReturn(event);
        when(eventToEventDTOConverter.convert(event)).thenReturn(eventDTO);
        EventDTO actual = eventFacade.updateEvent(eventDTO, eventDTO.getId());
        assertThat(actual).isEqualTo(eventDTO);

    }

    @Test
    void getEventsByMarketName() {
        Pageable pageable = PageRequest.of(0, 3);
        Page<Event> events = mock(Page.class);
        List<EventDTO> eventDTO = new ArrayList<>();

        when(eventService.getEventsByMarketName(pageable, "name")).thenReturn(events);
        when(events.getContent().stream().map(eventToEventDTOConverter::convert).toList()).thenReturn(eventDTO);
        when(events.getPageable()).thenReturn(pageable);
        when(events.getTotalElements()).thenReturn(3L);
        Page<EventDTO> expected = new PageImpl<>(eventDTO, pageable, 3L);
        Page<EventDTO> actual = eventFacade.getEventsByMarketName(pageable, "name");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getEventsByParticipantsName() {
        Pageable pageable = PageRequest.of(0, 3);
        Page<Event> events = mock(Page.class);
        List<EventDTO> eventDTO = new ArrayList<>();

        when(eventService.getEventsByParticipantsName(pageable, "name")).thenReturn(events);
        when(events.getContent().stream().map(eventToEventDTOConverter::convert).toList()).thenReturn(eventDTO);
        when(events.getPageable()).thenReturn(pageable);
        when(events.getTotalElements()).thenReturn(3L);
        Page<EventDTO> expected = new PageImpl<>(eventDTO, pageable, 3L);
        Page<EventDTO> actual = eventFacade.getEventsByParticipantsName(pageable, "name");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findEventsByPriceRange() {
        Pageable pageable = PageRequest.of(0, 3);
        Page<Event> events = mock(Page.class);
        List<EventDTO> eventDTO = new ArrayList<>();

        when(eventService.findEventsByPriceRange(BigDecimal.valueOf(1), BigDecimal.valueOf(2), pageable)).thenReturn(events);
        when(events.getContent().stream().map(eventToEventDTOConverter::convert).toList()).thenReturn(eventDTO);
        when(events.getPageable()).thenReturn(pageable);
        when(events.getTotalElements()).thenReturn(3L);
        Page<EventDTO> expected = new PageImpl<>(eventDTO, pageable, 3L);
        Page<EventDTO> actual = eventFacade.findEventsByPriceRange(BigDecimal.valueOf(1), BigDecimal.valueOf(2), pageable);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getSortedDescendingMarketsByPrice() {
        Pageable pageable = PageRequest.of(0, 3);
        Page<Event> events = mock(Page.class);
        List<EventDTO> eventDTO = new ArrayList<>();

        when(eventService.getSortedDescendingMarketsByPrice(pageable)).thenReturn(events);
        when(events.getContent().stream().map(eventToEventDTOConverter::convert).toList()).thenReturn(eventDTO);
        when(events.getPageable()).thenReturn(pageable);
        when(events.getTotalElements()).thenReturn(3L);
        Page<EventDTO> expected = new PageImpl<>(eventDTO, pageable, 3L);
        Page<EventDTO> actual = eventFacade.getSortedDescendingMarketsByPrice(pageable);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getEventsWithNMarketsAndNotSport() {
        Pageable pageable = PageRequest.of(0, 3);
        Page<Event> events = mock(Page.class);
        List<EventDTO> eventDTO = new ArrayList<>();

        when(eventService.getEventsWithNMarketsAndNotSport(pageable, "sport", 3)).thenReturn(events);
        when(events.getContent().stream().map(eventToEventDTOConverter::convert).toList()).thenReturn(eventDTO);
        when(events.getPageable()).thenReturn(pageable);
        when(events.getTotalElements()).thenReturn(3L);
        Page<EventDTO> expected = new PageImpl<>(eventDTO, pageable, 3L);
        Page<EventDTO> actual = eventFacade.getEventsWithNMarketsAndNotSport(pageable, "sport", 3);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void priceOfEvent() {
        eventFacade.priceOfEvent(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
        verify(eventService, times(1)).priceOfEvent(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
    }

    @Test
    void getAveragePricesForPreMatchMarkets() {
        eventFacade.getAveragePricesForPreMatchMarkets();
        verify(eventService, times(1)).getAveragePricesForPreMatchMarkets();
    }

    @Test
    void maxPayoutPerEvent() {
        eventFacade.maxPayoutPerEvent(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
        verify(eventService, times(1)).maxPayoutPerEvent(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
    }

    @Test
    void getEventsWithDuplicatedParticipantAndDifferentYears() {

        Pageable pageable = PageRequest.of(0, 3);
        Page<Event> events = mock(Page.class);
        List<EventDTO> eventDTO = new ArrayList<>();

        when(eventService.getEventsWithDuplicatedParticipantAndDifferentYears(pageable)).thenReturn(events);
        when(events.getContent().stream().map(eventToEventDTOConverter::convert).toList()).thenReturn(eventDTO);
        when(events.getPageable()).thenReturn(pageable);
        when(events.getTotalElements()).thenReturn(3L);
        Page<EventDTO> expected = new PageImpl<>(eventDTO, pageable, 3L);
        Page<EventDTO> actual = eventFacade.getEventsWithDuplicatedParticipantAndDifferentYears(pageable);
        assertThat(actual).isEqualTo(expected);
    }
}