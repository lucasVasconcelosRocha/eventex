package br.com.lrvasconcelos.eventex.api.dto.activity;

import br.com.lrvasconcelos.eventex.domain.entity.Activity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDTO {

    @NotBlank(message = "Name cannot be empty.")
    private String name;

    @NotNull(message = "Date cannot be empty.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startHour;

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endHour;

    private String description;

    private String requirements;

    private Integer vacancies;

    @NotNull(message = "Event id cannot be null.")
    private Long eventId;

    public Activity convertDTOtoEntity() {
        return new ModelMapper().map(this, Activity.class);
    }
}
