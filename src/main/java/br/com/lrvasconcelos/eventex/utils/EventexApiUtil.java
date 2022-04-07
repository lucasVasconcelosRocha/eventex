package br.com.lrvasconcelos.eventex.utils;

import br.com.lrvasconcelos.eventex.domain.entity.Event;

public class EventexApiUtil {

    public static boolean isEventEndDateGreaterThanStartDate(Event event) {
        return event.getEndDate().isAfter(event.getStartDate());
    }
}
