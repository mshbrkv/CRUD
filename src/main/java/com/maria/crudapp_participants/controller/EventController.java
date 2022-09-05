package com.maria.crudapp_participants.controller;

import com.maria.crudapp_participants.dto.EventDTO;
import com.maria.crudapp_participants.facade.EventFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/events")
public class EventController {

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

    @GetMapping("filter_by_market-name")
    public Page<EventDTO> getEventsByMarketName(Pageable pageable, @RequestParam String marketName) {
        return eventFacade.getEventsByMarketName(pageable, marketName);
    }

    @GetMapping("filter_by_participant-name")
    public Page<EventDTO> getEventsByParticipantName(Pageable pageable, @RequestParam String participantName) {
        return eventFacade.getEventsByParticipantsName(pageable, participantName);
    }
}
