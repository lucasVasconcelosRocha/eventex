package br.com.lrvasconcelos.eventex.domain.repositoy;

import br.com.lrvasconcelos.eventex.domain.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivitiesRepository extends JpaRepository<Activity, Long> {
}
