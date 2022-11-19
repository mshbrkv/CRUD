package com.maria.crudapp_participants.controller;

import com.maria.crudapp_participants.dto.EventDTO;
import com.maria.crudapp_participants.dto.ShortEvent;
import com.maria.crudapp_participants.facade.EventFacade;
import com.maria.crudapp_participants.selections.Selection;
import com.maria.crudapp_participants.service.SelectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/events")
public class EventController {

    private final SelectionService selectionService;
    private final EventFacade eventFacade;

    @GetMapping
    public Page<EventDTO> getEventList(Pageable pageable) {
        return eventFacade.getEventList(pageable);
    }

    @GetMapping("{id}")
    public EventDTO getEventById(@PathVariable("id") UUID eventId) {
        return eventFacade.getEventById(eventId);
    }

    @GetMapping("/in_play=true")
    public Page<EventDTO> getEventInPlay(Pageable pageable) {
        return eventFacade.getEventInPlay(pageable);
    }


    @PostMapping
    public EventDTO createEvent(@RequestBody EventDTO event) {
        return eventFacade.saveEvent(event);
    }

    @PutMapping("{id}")
    public EventDTO updateEvent(@RequestBody EventDTO newEvent, @PathVariable UUID id) {
        return eventFacade.updateEvent(newEvent, id);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") UUID eventId) {
        eventFacade.deleteEventById(eventId);
    }

    @GetMapping("search/by_market")
    public Page<EventDTO> getEventsByMarketName(Pageable pageable, @RequestParam String marketName) {
        return eventFacade.getEventsByMarketName(pageable, marketName);
    }

    @GetMapping("search/by_participant")
    public Page<EventDTO> getEventsByParticipantName(Pageable pageable, @RequestParam String participantName) {
        return eventFacade.getEventsByParticipantsName(pageable, participantName);
    }

    @GetMapping("search/by_price_range")
    public Page<EventDTO> findEventsByPriceRange(Pageable pageable, @RequestParam BigDecimal from, @RequestParam BigDecimal to) {
        return eventFacade.findEventsByPriceRange(from, to, pageable);
    }

    @GetMapping("sorted_markets_desc_by_price")
    public Page<EventDTO> getSortedDescendingMarketsByPrice(Pageable pageable) {
        return eventFacade.getSortedDescendingMarketsByPrice(pageable);
    }

    @GetMapping("search/without")
    public Page<EventDTO> getEventsWithNMarketsAndNotSport(Pageable pageable, @RequestParam String sport, @RequestParam Integer n) {
        return eventFacade.getEventsWithNMarketsAndNotSport(pageable, sport, n);
    }

    @GetMapping("prices/{id}")
    public List<BigDecimal> priceOfEvent(@PathVariable UUID id) {
        return eventFacade.priceOfEvent(id);
    }

    @GetMapping("pricesMarkets")
    public List<Double> getAveragePricesForPreMatchMarkets() {
        return eventFacade.getAveragePricesForPreMatchMarkets();
    }

    @GetMapping("shortEvents")
    public List<ShortEvent> getAllShortEvents() {
        return eventFacade.getAllShortEvents();
    }

    @GetMapping("duplicate")
    public Page<EventDTO> getEventsWithDuplicatedParticipantAndDifferentYears(Pageable pageable) {
        return eventFacade.getEventsWithDuplicatedParticipantAndDifferentYears(pageable);
    }

    @GetMapping("maxPayout/{id}")
    public BigDecimal maxPayoutPerEvent(@PathVariable UUID id) {
        return eventFacade.maxPayoutPerEvent(id);
    }

    @PostMapping("eventId/{eventId}/market/{marketId}/selection/{selectionId}")
    public Optional<Selection> addSelectionResult(@PathVariable UUID eventId, @PathVariable UUID marketId, @PathVariable UUID selectionId, @RequestBody String result) {
        return selectionService.updateSelectionWithResult(selectionId, result);
    }
}