package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.entity.Event;
import com.maria.crudapp_participants.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;


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
        return eventRepository.findByParticipantsName(pageable, participantName);
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
}
