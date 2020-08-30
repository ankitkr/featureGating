package com.showbooking.events;

import com.showbooking.repository.EventRepository;

import java.util.*;

public class EventService {
    public static Boolean addOrUpdateEvent(EventRepository repository, Map<String, Object> data) {
        try {
            Event event = repository.fetchMovie((Long) data.get("eventId"));
            Screen screen = repository.fetchScreen((Long) data.get("screenId"));
            if (screen == null) {
                screen = repository.addScreen(new Screen((Long) data.get("screenId")));
            }

            if (event == null) {
                event = repository.addMovie(new Movie((Long) data.get("eventId"), (String) data.get("title"),
                        (String) data.get("genre"), (String) data.get("language")));
            } else {
                event.setTitle((String) data.get("title"));
                event.setGenre((String) data.get("genre"));
                event.setLanguage((String) data.get("language"));
                repository.updateMovie(event);
            }

            repository.removeShows(event, screen);
            List<Object> showDetails = (List<Object>) data.get("showTimePriceMap");
            for (Object showDetail : showDetails) {
                Map<String, Object> detail = (Map<String, Object>) showDetail;
                repository.addShow(new Show(event, screen, (Integer) detail.get("time"), (Integer) detail.get("price")));
            }
        } catch (Exception exc) {
            System.out.println("Failed adding/updating event, due to: " + exc.getMessage());
            return false;
        }
        return true;
    }

    public static List<Show> fetchShows(EventRepository repository, Map<String, String> filter) {
        List<Event> allEvents = repository.fetchAllMovies();

        Filter filterObj = new Filter(filter.getOrDefault("title", null),
                filter.getOrDefault("genre", null),
                filter.getOrDefault("language", null),
                filter.getOrDefault("price", null));
        List<Event> filteredEvents = FilterService.filterEvents(allEvents, filterObj);

        List<Show> eventShows = new ArrayList<Show>();
        for(Event event: filteredEvents) {
            eventShows.addAll(repository.fetchAllShows(event.getEventId()));
        }

        List<Show> filteredShows = FilterService.filterShows(eventShows, filterObj);
        return filteredShows;
    }

    public static Boolean addScreenLayout(EventRepository repository, Long screenId, Boolean[][] layout) {
        try {
            Screen screen = repository.fetchScreen(screenId);
            screen.addLayout(layout);
            repository.updateScreen(screen);
        } catch(Exception exc) {
            System.out.println("Unable to add screen layout");
            return false;
        }

        return true;
    }

    public static Boolean[][] fetchEventSeatLayout(EventRepository repository, Long eventId) {
        Event event = repository.fetchMovie(eventId);
        List<Show> shows = repository.fetchAllShows(event.getEventId());
        if(shows.isEmpty())
            return null;

        Show selectedShow = Collections.min(shows, Comparator.comparing(Show::getId));
        Screen screen = repository.fetchScreen(selectedShow.getScreenId());
        return selectedShow.getSeatingLayout(screen);
    }
}
