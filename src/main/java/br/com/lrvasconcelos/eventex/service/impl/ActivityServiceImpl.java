package br.com.lrvasconcelos.eventex.service.impl;

import br.com.lrvasconcelos.eventex.api.exceptions.ApiNotFoundException;
import br.com.lrvasconcelos.eventex.domain.entity.Activity;
import br.com.lrvasconcelos.eventex.domain.repositoy.ActivitiesRepository;
import br.com.lrvasconcelos.eventex.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivitiesRepository repository;

    @Override
    public void create(Activity activity) {
        repository.save(activity);
    }

    @Override
    public void update(Activity activity, Long id) {
        repository.findById(id)
                .map(activityExists -> {
                    activity.setId(activityExists.getId());
                    repository.save(activity);
                    return Void.TYPE;
                }).orElseThrow(() -> new ApiNotFoundException("Activity doesn't exists."));
    }

    @Override
    public List<Activity> findAllActivities() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.findById(id).map(activity -> {
            repository.delete(activity);
            return Void.TYPE;
        }).orElseThrow(() -> new ApiNotFoundException("Activity doesn't exists."));
    }

    @Override
    public Optional<Activity> findById(Long id) {
        return repository.findById(id);
    }

}
