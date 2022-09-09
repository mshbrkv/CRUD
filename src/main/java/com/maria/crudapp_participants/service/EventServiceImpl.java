package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.dto.ShortEvent;
import com.maria.crudapp_participants.entity.Event;
import com.maria.crudapp_participants.entity.Market;
import com.maria.crudapp_participants.entity.Participant;
import com.maria.crudapp_participants.repository.EventRepository;
import com.maria.crudapp_participants.selections.Selection;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final EventServiceImpl self;

    @Override
    @Transactional(readOnly = true)
    public Page<Event> getAllEventList(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Event findEventById(UUID eventId) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        Event event = null;
        if (eventOptional.isPresent()) {
            event = eventOptional.get();
        }
        return event;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Event> getEventInPlay(Pageable pageable) {
        return eventRepository.findByInPlayTrue(pageable);
    }

    @Override
    public Page<Event> getEventsByMarketName(Pageable pageable, String marketName) {
        return eventRepository.findByMarketsName(pageable, marketName);
    }

    @Override
    public Page<Event> getEventsByParticipantsName(Pageable pageable, String participantName) {
        return eventRepository.findByParticipantsName(pageable, "[{\"name\":\"".concat(participantName).concat("\"}]"));
    }

    @Override
    @Transactional
    public void deleteEventById(UUID eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    @Transactional
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public Event updateEvent(Event newEvent, UUID eventId) {
        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isPresent()) {
            Event editEvent = event.get();
            editEvent.setId(newEvent.getId());
            editEvent.setName(newEvent.getName());
            editEvent.setStartTime(newEvent.getStartTime());
            editEvent.setInPlay(newEvent.isInPlay());
            editEvent.setParticipants(newEvent.getParticipants());
            editEvent.setMarkets(newEvent.getMarkets());
            return eventRepository.save(editEvent);
        } else {
            return newEvent;
        }
    }

    @Override
    public Page<Event> findEventsByPriceRange(BigDecimal priceFirst, BigDecimal priceSecond, Pageable pageable) {
        List<Event> events = eventRepository.findAll();
        List<Event> collect = events.stream()
                .filter(event -> event.getMarkets().stream().
                        map(Market::getSelections).
                        flatMap(Collection::stream).
                        anyMatch(selection -> selection.getPrice().compareTo(priceFirst) >= 0 && selection.getPrice().compareTo(priceSecond) <= 0))
                .toList();
        return new PageImpl<>(collect, pageable, collect.size());
    }

    @Override
    public List<BigDecimal> priceOfEvent(UUID eventId) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        List<Event> events = eventOptional.stream().toList();
        return events.stream()
                .map(event -> event.getMarkets())
                .flatMap(List::stream)
                .toList()
                .stream().map(market -> market.getSelections())
                .flatMap(List::stream)
                .toList()
                .stream().map(selection -> selection.getPrice())
                .toList();
    }

    @Override
    public Page<Event> getSortedEventsByPrice(Pageable pageable) {
        List<Event> events = eventRepository.findAll();
        List<Event> sortedEvents = events.stream()
//                .filter(event -> event.getMarkets().stream().
//                        map(Market::getSelections).
//                        flatMap(Collection::stream).
//                        allMatch(selection -> selection.getPrice()))
                .toList();
        return new PageImpl<>(sortedEvents, pageable, sortedEvents.size());

        //not work
    }

    @Override
    public Page<Event> getEventsWithNMarketsAndNotSport(Pageable pageable, String sport, Integer numMarkets) {
        List<Event> allEvents = eventRepository.findAll();

        List<Event> eventsWithoutSomeSport = allEvents.stream()
                .filter(event -> event.getParticipants().stream()
                        .noneMatch(participant -> Objects.equals(sport, participant.getSport()))).toList();

        List<Event> events = eventsWithoutSomeSport.stream().filter(event -> event.getMarkets().size() < numMarkets).toList();
        return new PageImpl<>(events, pageable, events.size());
    }


    @Override
    public List<Selection> getAveragePricesForPreMatchMarkets() {
        long data = new Date().getTime();

        List<Event> allEvents = eventRepository.findAll();
        List<Event> preMatchEvents = allEvents.stream()
                .filter(event -> event.getStartTime().getTime() < data).toList();
//        List<BigDecimal> prices = preMatchEvents.stream().map(event -> event.getMarkets().stream().toList().stream().map(market -> market.getSelections().stream().toList().stream().map(selection -> selection.getPrice())));

        preMatchEvents.stream().
                filter(event -> Stream.of(event.getId())
                        .equals(event.getMarkets().stream().map(market -> {
                            market.getEvent().getId();
                            Stream.of(market.getId()).equals(market.getSelections().stream().map(selection -> selection.getId()));
                            return null;
                        })));
        List<Selection> markets = preMatchEvents.stream().map(event -> event.getMarkets()).toList().stream().flatMap(List::stream).toList().stream().map(market -> market.getSelections()).toList().stream().flatMap(List::stream).toList();


//        List<Market> markets = preMatchEvents.stream().flatMap(event -> event.getMarkets());
//        System.out.println("fffffffff"+prices);
//       return prices;
        return markets;

        //not work
    }

    @Override
    public ShortEvent converterToShortEvent(Event event) {
        ShortEvent shortEvent = new ShortEvent();
        shortEvent.setId(event.getId());
        shortEvent.setEventName(event.getName());
        shortEvent.setStartDate(event.getStartTime());
        List<String> participantNamesList = event.getParticipants().stream().map(Participant::getName).toList();
        String participantNames = String.join("-", participantNamesList);
        shortEvent.setParticipantNames(participantNames);
        shortEvent.setSport(event.getParticipants().stream().map(Participant::getSport).toList().get(0));
        return shortEvent;
    }

    @Override
    public List<ShortEvent> allShortEvent() {
        List<Event> allEvents = eventRepository.findAll();
        return allEvents.stream().map(self::converterToShortEvent).toList();
    }

    @Override
    public List<Event> getEventsWithDuplicatedParticipantAndDifferentYears() {
        List<Event> allEvents = eventRepository.findAll();
        List<String> name = allEvents.stream().map(event -> event.getParticipants().stream().map(Participant::getName).toList()).toList().stream().flatMap(List::stream).toList();
        List<String> duplicated = name.stream().filter(i -> Collections.frequency(name, i) > 1).toList();
        List<Event> events = new ArrayList<>();

        for (int i = 0; i < duplicated.size(); i++) {
            int finalI = i;
            events.addAll(allEvents.stream().filter(event -> event.getParticipants().stream().map(Participant::getName).equals(Stream.of(duplicated.get(finalI)))).toList());
        }
        return events;
    }

    //not work
}
