package br.com.lrvasconcelos.eventex.domain.repositoy;

import br.com.lrvasconcelos.eventex.domain.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventsRepository extends JpaRepository<Event, Long> {

   List<Event> findAllByStartDateGreaterThanEqualAndStartDateLessThanEqual(LocalDate startDate, LocalDate endDate);
}
