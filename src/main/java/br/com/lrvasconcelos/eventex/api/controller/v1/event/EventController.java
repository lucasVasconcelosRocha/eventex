package br.com.lrvasconcelos.eventex.api.controller.v1.event;

import br.com.lrvasconcelos.eventex.api.dto.event.EventDTO;
import br.com.lrvasconcelos.eventex.api.dto.event.ResponseEventDTO;
import br.com.lrvasconcelos.eventex.domain.entity.Event;
import br.com.lrvasconcelos.eventex.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("eventex/events")
public class EventController {

    @Autowired
    private EventService service;

    @PostMapping
    public ResponseEntity<ResponseEventDTO> create(@RequestBody @Valid EventDTO dto) {

        Event event = service.createEvent(dto.convertDTOToEntity());

        return new ResponseEntity<>(ResponseEventDTO.convertToEventResponseDTO(event), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ResponseEventDTO>> findEventsBetweenDates(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

       List<ResponseEventDTO> result = service.findAllByFilters(startDate, endDate).stream()
               .map(ResponseEventDTO::convertToEventResponseDTO)
               .sorted((o1, o2) -> o1.getStartDate().compareTo(o2.getStartDate()))
               .collect(Collectors.toList());

      return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseEventDTO> updateEvent(@RequestBody EventDTO dto, @PathVariable("id") Long id) {
        Event updatedEvent = service.updateEvent(dto.convertDTOToEntity(), id);
        return new ResponseEntity<>(ResponseEventDTO.convertToEventResponseDTO(updatedEvent), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("id") Long id) {
        service.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ResponseEventDTO>> findEventById(@PathVariable("id") Long id) {
        List<ResponseEventDTO> result = service.findById(id).stream()
                .map(ResponseEventDTO::convertToEventResponseDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
 }
