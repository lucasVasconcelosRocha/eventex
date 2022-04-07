package br.com.lrvasconcelos.eventex.api.exceptions;

public class NotParsableContentException extends RuntimeException{
    public NotParsableContentException(String message) {
        super(message);
    }
}
