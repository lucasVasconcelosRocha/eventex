package br.com.lrvasconcelos.eventex.service;

import br.com.lrvasconcelos.eventex.domain.entity.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityService {

    void create(Activity activity);

    void update(Activity activity, Long id);

    List<Activity> findAllActivities();

    void delete(Long id);

    Optional<Activity> findById(Long id);
}
