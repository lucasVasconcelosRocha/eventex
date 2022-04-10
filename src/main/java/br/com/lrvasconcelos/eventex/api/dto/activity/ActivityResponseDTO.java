package br.com.lrvasconcelos.eventex.api.dto.activity;

import br.com.lrvasconcelos.eventex.api.dto.event.EventDTO;
import br.com.lrvasconcelos.eventex.domain.entity.Activity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActivityResponseDTO {

    private Long id;
    private String name;
    private LocalDate date;
    private LocalTime startHour;
    private LocalTime endHour;
    private String description;
    private String requirements;
    private Integer vacancies;
    private EventDTO event;

    public static ActivityResponseDTO convertToActivityToResponseDTO(Activity dto) {
        return new ModelMapper().map(dto, ActivityResponseDTO.class);
    }
}
