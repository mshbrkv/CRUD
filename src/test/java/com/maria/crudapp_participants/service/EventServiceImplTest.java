package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.dto.ParticipantDTO;
import com.maria.crudapp_participants.dto.ShortEvent;
import com.maria.crudapp_participants.entity.Event;
import com.maria.crudapp_participants.entity.Market;
import com.maria.crudapp_participants.entity.Participant;
import com.maria.crudapp_participants.facade.ParticipantFacadeImpl;
import com.maria.crudapp_participants.repository.EventRepository;
import com.maria.crudapp_participants.selections.Selection;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {


    @Mock
    EventRepository eventRepository;

    @Mock
    EventServiceImpl self;

    @InjectMocks
    EventServiceImpl eventService;

    static Event generateEvent() {


        Participant participant1 = new Participant(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"), "Sferiff", "football", "Moldova", "342");
        Participant participant2 = new Participant(UUID.fromString("5af40425-8b62-4fa3-94be-4babccfe97ea"), "Arsenal", "football", "Moldova", "342");
        Selection selection1 = new Selection(UUID.fromString("0b13d2e4-edba-41a4-889b-2b11d24544a2"), "aaaaaa", BigDecimal.valueOf(10.5), new Market(), null);
        Selection selection2 = new Selection(UUID.fromString("0b13d2e4-edba-41a4-889b-2b11d24544a2"), "aaaaaa", BigDecimal.valueOf(2.0), new Market(), null);
        Market market1 = new Market(UUID.fromString("940ec9e2-cc4d-4c3f-a9cc-a804760272f8"), "Double Chance", null, null, Arrays.asList(selection1, selection2));
        return new Event(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"), "null", Date.valueOf("2027-09-10"), true, Arrays.asList(participant1, participant2), List.of(market1));
    }


    private static Stream<Arguments> findEventByIdProvider() {
        Event fromBD = generateEvent();
        return Stream.of(
                Arguments.of(fromBD),
                Arguments.of((Object) null)
        );
    }

    private static Stream<Arguments> getEventsByMarketNameProvider() {
        Event fromBD1 = generateEvent();
        Page<Event> eventPage1 = new PageImpl<>(List.of(fromBD1));
        Event fromBD2 = generateEvent();
        Market market = new Market();
        market.setName("test");
        fromBD2.setMarkets(List.of(market));

        Page<Event> eventPage2 = new PageImpl<>(List.of(fromBD2));

        return Stream.of(
                Arguments.of(eventPage1, "Double Chance"),
                Arguments.of(eventPage2, "test"),
                Arguments.of(Page.empty(), "sssss")
        );
    }


    private static Stream<Arguments> getEventsByParticipantsNameProvider() {
        Event event = generateEvent();
        Page<Event> events = new PageImpl<>(List.of(event));
        return Stream.of(
                Arguments.of(events, "Sheriff"),
                Arguments.of(events, "Arsenal"),
                Arguments.of(Page.empty(), "test")
        );
    }

    private static Stream<Arguments> updateEventProvider() {
        Event event = generateEvent();
        Event editEvent = event;
        editEvent.setName("test");
        return Stream.of(
                Arguments.of(event, editEvent),
                Arguments.of(null, editEvent)
        );

    }

    private static Stream<Arguments> getEventsWithNMarketsAndNotSportProvider() {
        Pageable pageable = PageRequest.of(0, 3);
        Event event = generateEvent();
        Page<Event> expected = new PageImpl<>(List.of(event), pageable, List.of(event).size());
        return Stream.of(
                Arguments.of("basketball", 2, expected, event),
                Arguments.of("basketball", 1, Page.empty(), event)
        );
    }


    @Test
    void getAllEventList() {
        Pageable pageable = PageRequest.of(0, 3);
        Event event1 = new Event();
        Event event2 = new Event();
        List<Event> eventList = Arrays.asList(event1, event2);
        Page<Event> expected = new PageImpl<>(eventList);
        when(eventRepository.findAll(pageable)).thenReturn(expected);

        Page<Event> actual = eventService.getAllEventList(pageable);
        assertThat(actual).isEqualTo(actual);

    }

    @ParameterizedTest
    @MethodSource("findEventByIdProvider")
    void findEventById(Event event) {
        when(eventRepository.findById(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"))).thenReturn(Optional.ofNullable(event));
        Event actual = eventService.findEventById(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"));
        assertThat(actual).isEqualTo(event);
    }

    @Test
    void getEventInPlay() {
        Pageable pageable = PageRequest.of(0, 3);
        Event eventInPlay = generateEvent();
        Page<Event> expected = new PageImpl<>(List.of(eventInPlay));
        when(eventRepository.findByInPlayTrue(pageable)).thenReturn(expected);
        Page<Event> actual = eventService.getEventInPlay(pageable);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("getEventsByMarketNameProvider")
    void getEventsByMarketName(Page<Event> events, String marketName) {
        Pageable pageable = PageRequest.of(0, 3);
        when(eventRepository.findByMarketsName(pageable, marketName)).thenReturn(events);
        Page<Event> actual = eventService.getEventsByMarketName(pageable, marketName);
        assertThat(actual).isEqualTo(events);
    }

    @ParameterizedTest
    @MethodSource("getEventsByParticipantsNameProvider")
    void getEventsByParticipantsName(Page<Event> events, String participantName) {

        Pageable pageable = PageRequest.of(0, 3);
        when(eventRepository.findByParticipantsName(pageable, "[{\"name\":\"".concat(participantName).concat("\"}]"))).thenReturn(events);
        Page<Event> actual = eventService.getEventsByParticipantsName(pageable, participantName);
        assertThat(actual).isEqualTo(events);
    }

    @Test
    void deleteEventById() {
        eventService.deleteEventById(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"));
        verify(eventRepository).deleteById(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"));
    }

    @Test
    void saveEvent() {
        Event expected = generateEvent();
        when(eventRepository.save(expected)).thenReturn(expected);
        Event actual = eventService.saveEvent(expected);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("updateEventProvider")
    void updateEvent(Event event, Event editEvent) {
        when(eventRepository.findById(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"))).thenReturn(Optional.ofNullable(event));
        Event actual = eventService.updateEvent(editEvent, UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"));
        assertThat(actual).isNotEqualTo(event);
    }

    @Test
    void findEventsByPriceRange() {
        Pageable pageable = PageRequest.of(0, 3);
        Event event = generateEvent();
        Page<Event> events = new PageImpl<>(List.of(event), pageable, List.of(event).size());
        when(eventRepository.findAll()).thenReturn(List.of(event));
        Page<Event> actual = eventService.findEventsByPriceRange(BigDecimal.valueOf(0.56), BigDecimal.valueOf(44.98), pageable);
        assertThat(actual).isEqualTo(events);
    }

    @Test
    void priceOfEvent() {
        Event event = generateEvent();
        when(eventRepository.findById(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"))).thenReturn(Optional.of(event));
        List<BigDecimal> prices = eventService.priceOfEvent(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"));
        assertThat(prices).isEqualTo(Arrays.asList(BigDecimal.valueOf(10.5), BigDecimal.valueOf(2.0)));
    }

    @Test
    void getSortedDescendingMarketsByPrice() {
        Pageable pageable = PageRequest.of(0, 3);

        Selection selection1ForEvent1 = new Selection(UUID.fromString("0b13d2e4-edba-41a4-889b-2b11d24544a2"), "aaaaaa", BigDecimal.valueOf(10.5), new Market(), null);
        Selection selection2ForEvent1 = new Selection(UUID.fromString("0b13d2e4-edba-41a4-889b-2b11d24544a2"), "aaaaaa", BigDecimal.valueOf(2.0), new Market(), null);
        Market market1ForEvent1 = new Market(UUID.fromString("940ec9e2-cc4d-4c3f-a9cc-a804760272f8"), "Double Chance", null, null, Arrays.asList(selection1ForEvent1, selection2ForEvent1));
        Market market2ForEvent1 = new Market(UUID.fromString("d69e0c62-1715-4764-92d9-f33200b17e2e"), "Double Chance", null, null, Arrays.asList(selection1ForEvent1, selection2ForEvent1));


        Selection selection1ForEvent2 = new Selection(UUID.fromString("a92e3ec6-c468-4be2-b0d2-7e1e55c558c5"), "aaaaaa", BigDecimal.valueOf(16534.5), new Market(), null);
        Selection selection2ForEvent2 = new Selection(UUID.fromString("a001749a-6c39-4bae-a140-d730e88deb1d"), "aaaaaa", BigDecimal.valueOf(6.0), new Market(), null);
        Market marketForEvent2 = new Market(UUID.fromString("fed7d852-aa11-41a5-9435-bdb82a03904e"), "Double Chance", null, null, Arrays.asList(selection2ForEvent2, selection1ForEvent2));


        Event event1 = new Event(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"), null, null, true, null, Arrays.asList(market1ForEvent1, market2ForEvent1));
        Event event2 = new Event(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"), null, null, true, null, List.of(marketForEvent2));


        when(eventRepository.findAll()).thenReturn(List.of(event2));

        market1ForEvent1.setSelections(Arrays.asList(selection2ForEvent1, selection1ForEvent1));
        market2ForEvent1.setSelections(Arrays.asList(selection2ForEvent1, selection1ForEvent1));


        event1.setMarkets(Arrays.asList(market1ForEvent1, market2ForEvent1));


        marketForEvent2.setSelections(Arrays.asList(selection2ForEvent2, selection1ForEvent2));


        Page<Event> actual = eventService.getSortedDescendingMarketsByPrice(pageable);


        Page<Event> expected = new PageImpl<>(List.of(event2), pageable, List.of(event2).size());
        assertThat(actual).isEqualTo(expected);

    }

    @ParameterizedTest
    @MethodSource("getEventsWithNMarketsAndNotSportProvider")
    void getEventsWithNMarketsAndNotSport(String sport, int numMarket, Page<Event> expected, Event event) {
        Pageable pageable = PageRequest.of(0, 3);
        Event event2 = generateEvent();
        Participant participant1 = new Participant(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"), "Sferiff", "basketball", "Moldova", "342");
        Participant participant2 = new Participant(UUID.fromString("9d9254ac-1257-11ed-861d-0242ac120002"), "Sferiff", "basketball", "Moldova", "342");
        event2.setParticipants(Arrays.asList(participant1, participant2));
        List<Event> allEvents = Arrays.asList(event, event2);
        when(eventRepository.findAll()).thenReturn(allEvents);
        Page<Event> actual = eventService.getEventsWithNMarketsAndNotSport(pageable, sport, numMarket);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getAveragePricesForPreMatchMarkets() {
        Event event = generateEvent();
        when(eventRepository.findAll()).thenReturn(List.of(event));
        List<Double> prices = eventService.getAveragePricesForPreMatchMarkets();
        assertThat(prices).isEqualTo(List.of(6.25));


    }

    @Test
    void converterToShortEvent() {
        Event event = generateEvent();
        ShortEvent shortEvent = new ShortEvent(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"), "null", Date.valueOf("2027-09-10"), "Sferiff-Arsenal", "football");
        ShortEvent actual = eventService.converterToShortEvent(event);
        assertThat(actual).isEqualTo(shortEvent);
    }

    @Test
    void allShortEvent() {
        Event event = generateEvent();
        ShortEvent shortEvent = new ShortEvent(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"), null, Date.valueOf("2027-09-10"), "Sferiff-Arsenal", "football");
        List<Event> events = List.of(event);
        when(eventRepository.findAll()).thenReturn(events);
        when(self.converterToShortEvent(event)).thenReturn(shortEvent);
        List<ShortEvent> actual = eventService.allShortEvent();
        assertThat(actual).isEqualTo(List.of(shortEvent));
    }

    @Test
    void getEventsWithDuplicatedParticipantAndDifferentYears() {
        Pageable pageable = PageRequest.of(0, 3);
        Event event1 = generateEvent();
        Event event2 = generateEvent();
        Event event3 = generateEvent();
        Participant participant1ForEvent3 = new Participant(UUID.fromString("a10217a6-89f9-4a1c-b0ae-27b050e3170a"), "ddddd", "football", "Moldova", "342");
        Participant participant2ForEvent3 = new Participant(UUID.fromString("fcf2ee35-53a6-43df-9410-133506ddb6f8"), "ffff", "football", "Moldova", "342");

        event1.setId(UUID.randomUUID());
        event2.setId(UUID.randomUUID());
        event3.setId(UUID.randomUUID());
        event2.setStartTime(Date.valueOf("2023-09-10"));
        event3.setParticipants(Arrays.asList(participant1ForEvent3, participant2ForEvent3));

        when(eventRepository.findAll()).thenReturn(Arrays.asList(event1, event2, event3));

        Page<Event> actual = eventService.getEventsWithDuplicatedParticipantAndDifferentYears(pageable);

        Page<Event> expected = new PageImpl<>(Arrays.asList(event1, event2), pageable, Arrays.asList(event1, event2).size());
        assertThat(actual).isEqualTo(expected);


    }

    @Test
    void maxPayoutPerEvent() {
        when(eventRepository.findById(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"))).thenReturn(Optional.of(generateEvent()));
        BigDecimal actual = eventService.maxPayoutPerEvent(UUID.fromString("f15ef034-4f9a-4be1-a0c0-dd09e154e48d"));
        assertThat(actual).isEqualTo(BigDecimal.valueOf(10.5));
    }

    @ExtendWith(MockitoExtension.class)
    static
    class ParticipantFacadeImplTest {

        private final ParticipantService participantService;
        private final Converter<Participant, ParticipantDTO> participantToParticipantDTOConverter;
        private final Converter<ParticipantDTO, Participant> participantDTOToParticipantConverter;
        private final ParticipantFacadeImpl participantFacade;

        public ParticipantFacadeImplTest() {
            this.participantService = mock(ParticipantService.class);
            this.participantToParticipantDTOConverter = mock(Converter.class);
            this.participantDTOToParticipantConverter = mock(Converter.class);
            this.participantFacade = new ParticipantFacadeImpl(participantService, participantToParticipantDTOConverter, participantDTOToParticipantConverter);
        }

        @Test
        void saveParticipant() {
            ParticipantDTO participantDto = mock(ParticipantDTO.class);
            Participant participant = mock(Participant.class);

            when(participantDTOToParticipantConverter.convert(participantDto)).thenReturn(participant);
            when(participantService.saveParticipant(participant)).thenReturn(participant);
            when(participantToParticipantDTOConverter.convert(participant)).thenReturn(participantDto);

            ParticipantDTO participantDTO = participantFacade.saveParticipant(participantDto);

            assertThat(participantDTO).isEqualTo(participantDto);
            verify(participantService, times(1)).saveParticipant(participant);
        }

        @Test
        void fetchParticipantsList() {
            Pageable pageable = PageRequest.of(0, 3);
            String searchString = "Sh";
            Page<Participant> participants = mock(Page.class);
            List<ParticipantDTO> participantDto = new ArrayList<>();


            when(participantService.fetchParticipantsList(searchString, pageable)).thenReturn(participants);
            when(participants.getContent().stream().map(participantToParticipantDTOConverter::convert).toList()).thenReturn(participantDto);
            when(participants.getPageable()).thenReturn(pageable);
            when(participants.getTotalElements()).thenReturn(3L);

            Page<ParticipantDTO> expected = new PageImpl<>(participantDto, pageable, 3L);
            Page<ParticipantDTO> actual = participantFacade.fetchParticipantsList(searchString, pageable);

            Assertions.assertThat(actual).isEqualTo(expected);
            verify(participantService, times(1)).fetchParticipantsList(searchString, pageable);
        }

        @Test
        void updateParticipant() {
            ParticipantDTO participantDto = mock(ParticipantDTO.class);
            Participant participant = mock(Participant.class);

            when(participantDTOToParticipantConverter.convert(participantDto)).thenReturn(participant);
            when(participantService.updateParticipant(participant, participant.getId())).thenReturn(participant);
            when(participantToParticipantDTOConverter.convert(participant)).thenReturn(participantDto);

            ParticipantDTO participantDTO = participantFacade.updateParticipant(participantDto, participantDto.getId());

            assertThat(participantDTO).isEqualTo(participantDto);
            verify(participantService, times(1)).updateParticipant(participant, participant.getId());
        }

        @Test
        void deleteParticipantById() {
            participantFacade.deleteParticipantById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
            verify(participantService, times(1)).deleteParticipantById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
        }

        @Test
        void findParticipantById() {
            ParticipantDTO participantDto = mock(ParticipantDTO.class);
            Participant participant = mock(Participant.class);

            when(participantService.findParticipantById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"))).thenReturn(participant);
            when(participantToParticipantDTOConverter.convert(participant)).thenReturn(participantDto);

            ParticipantDTO participantDTO = participantFacade.findParticipantById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));

            assertThat(participantDTO).isEqualTo(participantDto);
            verify(participantService, times(1)).findParticipantById(UUID.fromString("9d9239ac-1257-11ed-861d-0242ac120002"));
        }
    }
}