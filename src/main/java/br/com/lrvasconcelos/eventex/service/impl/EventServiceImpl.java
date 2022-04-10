package br.com.lrvasconcelos.eventex.service.impl;

import br.com.lrvasconcelos.eventex.api.exceptions.ApiNotFoundException;
import br.com.lrvasconcelos.eventex.api.exceptions.NotParsableContentException;
import br.com.lrvasconcelos.eventex.domain.entity.Event;
import br.com.lrvasconcelos.eventex.domain.repositoy.EventsRepository;
import br.com.lrvasconcelos.eventex.service.EventService;
import br.com.lrvasconcelos.eventex.utils.EventexApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventsRepository repository;

    @Override
    public Event createEvent(Event event) {

        if(!EventexApiUtil.isEventEndDateGreaterThanStartDate(event))
            throw new NotParsableContentException("The Event's start date is greater than event's end date.");

        return repository.save(event);
    }

    @Override
    public List<Event> findAllByFilters(LocalDate startDate, LocalDate endDate) {
        return repository.findAllByStartDateGreaterThanEqualAndStartDateLessThanEqual(startDate, endDate);
    }

    @Override
    public Event updateEvent(Event event, Long id) {
        return repository.findById(id)
                .map(eventExists -> {
                    event.setId(eventExists.getId());
                    repository.save(event);
                    return event;
                }).orElseThrow(() -> new ApiNotFoundException("Event not found!"));
    }

    @Override
    public void deleteEvent(Long id) {
        repository.findById(id).map(event -> {
            repository.deleteById(event.getId());
            return Void.TYPE;
        }).orElseThrow(() -> new ApiNotFoundException("Event not found!"));
    }

    @Override
    public Optional<Event> findById(Long id) {
        return repository.findById(id);
    }


}
