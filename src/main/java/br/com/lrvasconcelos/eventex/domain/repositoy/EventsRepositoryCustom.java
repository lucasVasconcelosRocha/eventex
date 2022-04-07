package br.com.lrvasconcelos.eventex.domain.repositoy;

import br.com.lrvasconcelos.eventex.domain.entity.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventsRepositoryCustom {

    List<Event> findByFilters(LocalDate startDate, LocalDate endDate);
}
