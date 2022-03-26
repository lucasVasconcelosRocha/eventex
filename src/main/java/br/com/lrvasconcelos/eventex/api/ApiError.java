package br.com.lrvasconcelos.eventex.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    @Getter
    private List<String> errors;
    @Getter
    private LocalDateTime timestamp;

    public ApiError(String message) {
        this.errors = List.of(message);
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(List<String> errors) {
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
    }

}
