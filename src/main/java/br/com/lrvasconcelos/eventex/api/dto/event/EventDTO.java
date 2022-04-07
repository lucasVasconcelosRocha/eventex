package br.com.lrvasconcelos.eventex.api.dto.event;

import br.com.lrvasconcelos.eventex.domain.entity.Event;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

    @NotBlank(message = "Name cannot be empty.")
    @Length(max = 60, message = "Name must contain a maximum of 60 characters.")
    private String name;

    @NotNull(message = "Start date cannot be null.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "End date cannot be null.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private String description;

    private String localName;

    private String address;

    private String phone;

    private boolean isCharged;

    private BigDecimal price;

    private boolean allowSubscription;

    public Event convertDTOToEntity() {
        return new ModelMapper().map(this, Event.class);
    }

}
