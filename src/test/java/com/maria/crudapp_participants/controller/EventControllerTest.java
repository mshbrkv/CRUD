package com.maria.crudapp_participants.controller;

import com.maria.crudapp_participants.dto.EventDTO;
import com.maria.crudapp_participants.dto.ShortEvent;
import com.maria.crudapp_participants.entity.Market;
import com.maria.crudapp_participants.entity.Participant;
import com.maria.crudapp_participants.facade.EventFacade;
import com.maria.crudapp_participants.selections.Selection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EventControllerTest {

    @Mock
    private final EventFacade eventFacade = mock(EventFacade.class);

    @InjectMocks
    EventController eventController;

    static EventDTO generateEvent() {


        Participant participant1 = new Participant(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"), "Sferiff", "football", "Moldova", 342);
        Participant participant2 = new Participant(UUID.fromString("5af40425-8b62-4fa3-94be-4babccfe97ea"), "Arsenal", "football", "Moldova", 342);
        Selection selection1 = new Selection(UUID.fromString("0b13d2e4-edba-41a4-889b-2b11d24544a2"), "aaaaaa", BigDecimal.valueOf(10.5), new Market());
        Selection selection2 = new Selection(UUID.fromString("0b13d2e4-edba-41a4-889b-2b11d24544a2"), "aaaaaa", BigDecimal.valueOf(2.0), new Market());
        Market market1 = new Market(UUID.fromString("940ec9e2-cc4d-4c3f-a9cc-a804760272f8"), "Double Chance", null, null, Arrays.asList(selection1, selection2));
        return new EventDTO(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"), "null", Date.valueOf("2027-09-10"), true, Arrays.asList(participant1, participant2), List.of(market1));
    }


    @Test
    void getEventList() {
        Pageable pageable = PageRequest.of(0, 3);
        EventDTO eventDTO1 = generateEvent();
        EventDTO eventDTO2 = generateEvent();
        eventDTO2.setId(UUID.randomUUID());
        List<EventDTO> eventDTOList = Arrays.asList(eventDTO1, eventDTO2);
        Page<EventDTO> eventDTOPage = new PageImpl<>(eventDTOList, pageable, eventDTOList.size());
        when(eventFacade.getEventList(pageable)).thenReturn(eventDTOPage);
        Page<EventDTO> actual = eventController.getEventList(pageable);
        assertThat(actual).isEqualTo(eventDTOPage);

    }

    @Test
    void getEventById() {
        EventDTO eventDTO = generateEvent();
        when(eventFacade.getEventById(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"))).thenReturn(eventDTO);
        EventDTO actual = eventController.getEventById(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"));
        assertThat(actual).isEqualTo(eventDTO);
    }

    @Test
    void getEventInPlay() {
        Pageable pageable = PageRequest.of(0, 3);
        EventDTO eventDTO1 = generateEvent();

        List<EventDTO> eventDTOList = List.of(eventDTO1);
        Page<EventDTO> eventDTOPage = new PageImpl<>(eventDTOList, pageable, eventDTOList.size());
        when(eventFacade.getEventInPlay(pageable)).thenReturn(eventDTOPage);
        Page<EventDTO> actual = eventController.getEventInPlay(pageable);
        assertThat(actual).isEqualTo(eventDTOPage);
    }

    @Test
    void createEvent() {
        EventDTO eventDTO = generateEvent();
        when(eventFacade.saveEvent(eventDTO)).thenReturn(eventDTO);
        EventDTO actual = eventController.createEvent(eventDTO);
        assertThat(actual).isEqualTo(eventDTO);
    }

    @Test
    void updateEvent() {
        EventDTO expected = generateEvent();
        expected.setInPlay(false);
        when(eventFacade.updateEvent(expected, expected.getId())).thenReturn(expected);
        EventDTO actual = eventController.updateEvent(expected, expected.getId());
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void deleteById() {
        eventController.deleteById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
        verify(eventFacade).deleteEventById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));

    }

    @Test
    void getEventsByMarketName() {
        Pageable pageable = PageRequest.of(0, 3);
        EventDTO eventDTO1 = generateEvent();

        List<EventDTO> eventDTOList = List.of(eventDTO1);
        Page<EventDTO> eventDTOPage = new PageImpl<>(eventDTOList, pageable, eventDTOList.size());
        when(eventFacade.getEventsByMarketName(pageable, "Double Chance")).thenReturn(eventDTOPage);
        Page<EventDTO> actual = eventController.getEventsByMarketName(pageable, "Double Chance");
        assertThat(actual).isEqualTo(eventDTOPage);
    }

    @Test
    void getEventsByParticipantName() {
        Pageable pageable = PageRequest.of(0, 3);
        EventDTO eventDTO1 = generateEvent();

        List<EventDTO> eventDTOList = List.of(eventDTO1);
        Page<EventDTO> eventDTOPage = new PageImpl<>(eventDTOList, pageable, eventDTOList.size());
        when(eventFacade.getEventsByParticipantsName(pageable, "Sferiff")).thenReturn(eventDTOPage);
        Page<EventDTO> actual = eventController.getEventsByParticipantName(pageable, "Sferiff");
        assertThat(actual).isEqualTo(eventDTOPage);

    }

    @Test
    void findEventsByPriceRange() {
        Pageable pageable = PageRequest.of(0, 3);
        EventDTO eventDTO1 = generateEvent();

        List<EventDTO> eventDTOList = List.of(eventDTO1);
        Page<EventDTO> eventDTOPage = new PageImpl<>(eventDTOList, pageable, eventDTOList.size());
        when(eventFacade.findEventsByPriceRange(BigDecimal.valueOf(0.56), BigDecimal.valueOf(44.98), pageable)).thenReturn(eventDTOPage);
        Page<EventDTO> actual = eventController.findEventsByPriceRange(pageable, BigDecimal.valueOf(0.56), BigDecimal.valueOf(44.98));
        assertThat(actual).isEqualTo(eventDTOPage);
    }

    @Test
    void getSortedDescendingMarketsByPrice() {
        Pageable pageable = PageRequest.of(0, 3);

        Selection selection1ForEvent1 = new Selection(UUID.fromString("0b13d2e4-edba-41a4-889b-2b11d24544a2"), "aaaaaa", BigDecimal.valueOf(10.5), new Market());
        Selection selection2ForEvent1 = new Selection(UUID.fromString("0b13d2e4-edba-41a4-889b-2b11d24544a2"), "aaaaaa", BigDecimal.valueOf(2.0), new Market());
        Market market1ForEvent1 = new Market(UUID.fromString("940ec9e2-cc4d-4c3f-a9cc-a804760272f8"), "Double Chance", null, null, Arrays.asList(selection1ForEvent1, selection2ForEvent1));
        Market market2ForEvent1 = new Market(UUID.fromString("d69e0c62-1715-4764-92d9-f33200b17e2e"), "Double Chance", null, null, Arrays.asList(selection1ForEvent1, selection2ForEvent1));


        Selection selection1ForEvent2 = new Selection(UUID.fromString("a92e3ec6-c468-4be2-b0d2-7e1e55c558c5"), "aaaaaa", BigDecimal.valueOf(16534.5), new Market());
        Selection selection2ForEvent2 = new Selection(UUID.fromString("a001749a-6c39-4bae-a140-d730e88deb1d"), "aaaaaa", BigDecimal.valueOf(6.0), new Market());
        Market marketForEvent2 = new Market(UUID.fromString("fed7d852-aa11-41a5-9435-bdb82a03904e"), "Double Chance", null, null, Arrays.asList(selection2ForEvent2, selection1ForEvent2));


        EventDTO event1 = new EventDTO(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"), null, null, true, null, Arrays.asList(market1ForEvent1, market2ForEvent1));
        EventDTO event2 = new EventDTO(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"), null, null, true, null, List.of(marketForEvent2));


        market1ForEvent1.setSelections(Arrays.asList(selection2ForEvent1, selection1ForEvent1));
        market2ForEvent1.setSelections(Arrays.asList(selection2ForEvent1, selection1ForEvent1));


        event1.setMarkets(Arrays.asList(market1ForEvent1, market2ForEvent1));


        marketForEvent2.setSelections(Arrays.asList(selection2ForEvent2, selection1ForEvent2));

        when(eventFacade.getSortedDescendingMarketsByPrice(pageable)).thenReturn(new PageImpl<>(List.of(event2), pageable, List.of(event2).size()));

        Page<EventDTO> actual = eventController.getSortedDescendingMarketsByPrice(pageable);


        Page<EventDTO> expected = new PageImpl<>(List.of(event2), pageable, List.of(event2).size());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getEventsWithNMarketsAndNotSport() {

        Pageable pageable = PageRequest.of(0, 3);
        EventDTO event2 = generateEvent();
        Participant participant1 = new Participant(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"), "Sferiff", "basketball", "Moldova", 342);
        Participant participant2 = new Participant(UUID.fromString("9d9254ac-1257-11ed-861d-0242ac120002"), "Sferiff", "basketball", "Moldova", 342);
        event2.setParticipants(Arrays.asList(participant1, participant2));
        List<EventDTO> allEvents = Arrays.asList(event2);
        Page<EventDTO> expected = new PageImpl<>(allEvents);
        when(eventFacade.getEventsWithNMarketsAndNotSport(pageable, "sss", 2)).thenReturn(expected);
        Page<EventDTO> actual = eventController.getEventsWithNMarketsAndNotSport(pageable, "sss", 2);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void priceOfEvent() {
        when(eventFacade.priceOfEvent(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"))).thenReturn(Arrays.asList(BigDecimal.valueOf(10.5), BigDecimal.valueOf(2.0)));
        List<BigDecimal> prices = eventController.priceOfEvent(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"));
        assertThat(prices).isEqualTo(Arrays.asList(BigDecimal.valueOf(10.5), BigDecimal.valueOf(2.0)));
    }

    @Test
    void getAveragePricesForPreMatchMarkets() {
        when(eventFacade.getAveragePricesForPreMatchMarkets()).thenReturn(List.of(6.25));
        List<Double> prices = eventController.getAveragePricesForPreMatchMarkets();
        assertThat(prices).isEqualTo(List.of(6.25));
    }

    @Test
    void getAllShortEvents() {

        ShortEvent shortEvent = new ShortEvent(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"), null, Date.valueOf("2027-09-10"), "Sferiff-Arsenal", "football");

        when(eventFacade.getAllShortEvents()).thenReturn(List.of(shortEvent));
        List<ShortEvent> actual = eventController.getAllShortEvents();

        assertThat(actual).isEqualTo(List.of(shortEvent));

    }

    @Test
    void getEventsWithDuplicatedParticipantAndDifferentYears() {

        Pageable pageable = PageRequest.of(0, 3);
        EventDTO event1 = generateEvent();
        EventDTO event2 = generateEvent();
        event1.setId(UUID.randomUUID());
        event2.setId(UUID.randomUUID());
        event2.setStartTime(Date.valueOf("2023-09-10"));


        Page<EventDTO> eventDTOPage = new PageImpl<>(Arrays.asList(event1, event2));
        when(eventFacade.getEventsWithDuplicatedParticipantAndDifferentYears(pageable)).thenReturn(eventDTOPage);
        Page<EventDTO> actual = eventController.getEventsWithDuplicatedParticipantAndDifferentYears(pageable);

        assertThat(actual).isEqualTo(eventDTOPage);


    }

    @Test
    void maxPayoutPerEvent() {
        when(eventFacade.maxPayoutPerEvent(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"))).thenReturn(BigDecimal.valueOf(10.5));
        BigDecimal actual = eventController.maxPayoutPerEvent(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"));
        assertThat(actual).isEqualTo(BigDecimal.valueOf(10.5));
    }
}