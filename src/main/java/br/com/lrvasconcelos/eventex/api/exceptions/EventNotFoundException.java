package br.com.lrvasconcelos.eventex.api.exceptions;

public class EventNotFoundException extends RuntimeException{

    public EventNotFoundException(String message) {
        super(message);
    }

}
