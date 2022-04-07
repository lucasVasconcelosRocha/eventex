package br.com.lrvasconcelos.eventex.api.dto.event;

import br.com.lrvasconcelos.eventex.domain.entity.Event;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseEventDTO {

    private Long id;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private String description;

    private String localName;

    private String address;

    private String phone;

    private boolean isCharged;

    private BigDecimal price;

    private boolean allowSubscription;

    public static ResponseEventDTO convertToEventResponseDTO(Event event) {
        return new ModelMapper().map(event, ResponseEventDTO.class);
    }
}
