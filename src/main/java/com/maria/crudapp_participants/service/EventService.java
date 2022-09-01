package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EventService {

    Page<Event> getAllEventList(Pageable pageable);

    void deleteEventById(UUID EventId);

    Page<Event> getEventInPlay(Pageable pageable);


    Event saveEvent(Event Event);

    Event updateEvent(Event newEvent, UUID EventId);

//    Page<Event> searchFlexible(String searchString, Pageable pageable);

//    Page<Event> fetchEventsList(String searchString, Pageable pageable);

    Event findEventById(final UUID EventId);
}
