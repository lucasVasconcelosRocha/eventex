package br.com.lrvasconcelos.eventex.api.controller.v1.activity;

import br.com.lrvasconcelos.eventex.api.dto.activity.ActivityDTO;
import br.com.lrvasconcelos.eventex.api.dto.activity.ActivityResponseDTO;
import br.com.lrvasconcelos.eventex.api.dto.event.EventDTO;
import br.com.lrvasconcelos.eventex.domain.entity.Event;
import br.com.lrvasconcelos.eventex.service.ActivityService;
import br.com.lrvasconcelos.eventex.service.EventService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eventex/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<Void> createActivity(@RequestBody @Valid @NotNull ActivityDTO dto) {
        activityService.create(dto.convertDTOtoEntity());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponseDTO>> findAllActivities() {
       List<ActivityResponseDTO> activities = activityService.findAllActivities()
               .stream()
               .map(activity -> {
                   ActivityResponseDTO activityResponse = ActivityResponseDTO.convertToActivityToResponseDTO(activity);
                   activityResponse.setEvent(eventService.findById(activity.getEvent().getId())
                           .map(Event::convertEntityToDto).orElse(new EventDTO()));
                   return activityResponse;
               }).collect(Collectors.toList());

       return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateActivity(@RequestBody @Valid @NotNull ActivityDTO dto,
                                               @PathVariable("id") Long id) {
        activityService.update(dto.convertDTOtoEntity(), id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable("id") @NotNull Long id) {
        activityService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
