package com.showbooking.events;

import java.util.ArrayList;
import java.util.List;

public class FilterService {
    public static List<Event> filterEvents(List<Event> events, Filter filter) {
        List<Event> result = new ArrayList<Event>(events);

        if(filter.getTitle() != null) {
            result.removeIf(event -> event.getEventTitle().equalsIgnoreCase(filter.getTitle()));
        }
        if(filter.getGenre() != null) {
            result.removeIf(event -> event.getEventGenre().equalsIgnoreCase(filter.getGenre()));
        }
        if(filter.getLanguage() != null) {
            result.removeIf(event -> event.getEventLanguage().equalsIgnoreCase(filter.getLanguage()));
        }

        return result;
    }

    public static List<Show> filterShows(List<Show> shows, Filter filter) {
        List<Show> result = new ArrayList<Show>(shows);

        if(filter.getMinPrice() != null) {
            result.removeIf(show -> show.getPrice() < filter.getMinPrice());
        }
        if(filter.getMaxPrice() != null) {
            result.removeIf(show -> show.getPrice() > filter.getMaxPrice());
        }

        return result;
    }
}
