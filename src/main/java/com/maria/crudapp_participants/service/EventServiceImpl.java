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
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EventServiceImpl implements EventService {

    final Function<Event, List<BigDecimal>> priceExtractingFunction = e -> e.getMarkets()
            .stream()
            .flatMap(y -> y.getSelections().stream())
            .map(Selection::getPrice)
            .collect(Collectors.toList());
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


        final List<Event> events = eventRepository.findAll()
                .stream()
                .collect(Collectors.toMap(x -> x, priceExtractingFunction))
                .entrySet()
                .stream()
                .filter(x -> x.getValue()
                        .stream()
                        .anyMatch(price -> price.compareTo(priceFirst) >= 0 && price.compareTo(priceSecond) <= 0))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                .keySet()
                .stream()
                .toList();

        return new PageImpl<>(events, pageable, events.size());
    }

    @Override
    public List<BigDecimal> priceOfEvent(UUID eventId) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        return eventOptional
                .stream()
                .flatMap(event -> event.getMarkets().stream())
                .flatMap(market -> market.getSelections().stream())
                .map(Selection::getPrice)
                .toList();
    }

    @Override
    public Page<Event> getSortedDescendingMarketsByPrice(Pageable pageable) {
        List<Event> events = eventRepository.findAll()
                .stream()
                .map(event -> {
                    final List<Market> markets = event.getMarkets()
                            .stream()
                            .map(market -> new Container(market, market.getSelections()
                                    .stream()
                                    .sorted(Comparator.comparing(Selection::getPrice))
                                    .toList()))
                            .sorted(Comparator.comparing(x -> x.selections.stream()
                                    .findFirst()
                                    .map(Selection::getPrice)
                                    .orElseThrow(), Comparator.reverseOrder()))
                            .map(x -> {
                                Market market = x.market;
                                market.setSelections(x.selections);
                                return market;
                            }).toList();

                    event.setMarkets(markets);

                    return event;
                }).toList();

        return new PageImpl<>(events, pageable, events.size());
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
    public Double getAveragePricesForPreMatchMarkets() {

        return eventRepository.findAll()
                .stream()
                .filter(Event::isPrematch)
                .flatMap(x -> x.getMarkets().stream())
                .flatMap(x -> x.getSelections().stream())
                .mapToDouble(x -> x.getPrice().doubleValue())
                .average()
                .orElseThrow();

        //not working
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
//        List<Event> eventToFilter = allEvents.stream()
//                .map(event -> {
//
//
//                })


        List<String> name = allEvents.stream().map(event -> event.getParticipants().stream().map(Participant::getName).toList()).toList().stream().flatMap(List::stream).toList();
        List<String> duplicated = name.stream().filter(i -> Collections.frequency(name, i) > 1).toList();
        List<Event> events = new ArrayList<>();

        for (int i = 0; i < duplicated.size(); i++) {
            int finalI = i;
            events.addAll(allEvents.stream().filter(event -> event.getParticipants().stream().map(Participant::getName).equals(Stream.of(duplicated.get(finalI)))).toList());
        }
        return events;

        //not working
    }

    @Override
    public BigDecimal maxPayoutPerEvent(UUID eventId) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        List<BigDecimal> allPrices = eventOptional.stream().toList().stream()
                .flatMap(event -> event.getMarkets().stream())
                .flatMap(market -> market.getSelections().stream())
                .map(Selection::getPrice)
                .toList();

        return Collections.max(allPrices);
    }

    record Container(Market market, List<Selection> selections) {
    }
}
