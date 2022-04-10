package br.com.lrvasconcelos.eventex.service;

import br.com.lrvasconcelos.eventex.domain.entity.Event;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventService {

    Event createEvent(Event event);

    List<Event> findAllByFilters(LocalDate startDate, LocalDate endDate);

    Event updateEvent(Event event, Long id);

    void deleteEvent(Long id);

    Optional<Event> findById(Long id);
}
