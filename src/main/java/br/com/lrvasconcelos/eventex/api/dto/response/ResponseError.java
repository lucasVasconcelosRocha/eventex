package br.com.lrvasconcelos.eventex.api.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
public class ResponseError {

    private LocalDateTime timestamp;
    private String details;
}
