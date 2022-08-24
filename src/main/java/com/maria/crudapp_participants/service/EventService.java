package com.maria.crudapp_participants.service;

import com.maria.crudapp_participants.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EventService {
    Event saveEvent(Event Event);

    Page<Event> getAllEventList(Pageable pageable);

    Event updateEvent(Event newEvent, UUID EventId);

//    Page<Event> searchFlexible(String searchString, Pageable pageable);

    void deleteEventById(UUID EventId);

//    Page<Event> fetchEventsList(String searchString, Pageable pageable);

    Event findEventById(final UUID EventId);
}
