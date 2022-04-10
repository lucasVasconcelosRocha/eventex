package br.com.lrvasconcelos.eventex.api.exceptions;

import java.util.function.Supplier;

public class ApiNotFoundException extends RuntimeException {

    public ApiNotFoundException(String message) {
        super(message);
    }
}
