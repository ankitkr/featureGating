package com.showbooking.repository;

import com.showbooking.events.Event;
import com.showbooking.events.Screen;
import com.showbooking.events.Show;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventRepository {
    private volatile static EventRepository instance;
    private final Map<Long, Event> movieMap;
    private final Map<Long, Screen> screenMap;
    private Map<Long, List<Show>> showStore;

    private EventRepository() {
        this.movieMap = new HashMap<>();
        this.screenMap = new HashMap<>();
        this.showStore = new HashMap<>();
    }

    public static EventRepository getInstance() {
        if (instance == null) {
            synchronized (EventRepository.class) {
                if (instance == null) {
                    instance = new EventRepository();
                }
            }
        }
        return instance;
    }

    public List<Event> fetchAllMovies() {
        List<Event> allMovies = new ArrayList<Event>(movieMap.values());
        return allMovies;
    }

    public Event fetchMovie(Long id) {
        return movieMap.get(id);
    }

    public Event addMovie(Event event) {
        movieMap.put(event.getEventId(), event);
        return event;
    }

    public void updateMovie(Event event) {
        movieMap.put(event.getEventId(), event);
    }

    public Screen fetchScreen(Long id) {
            return screenMap.get(id);
    }

    public Screen addScreen(Screen screen) {
        screenMap.put(screen.getId(), screen);
        return screen;
    }

    public void updateScreen(Screen screen) {
        screenMap.put(screen.getId(), screen);
    }

    public List<Show> fetchAllShows(Long eventId) {
        return showStore.getOrDefault(eventId, new ArrayList<Show>());
    }

    public Show addShow(Show show) {
        List<Show> eventShows = showStore.get(show.getEventId());
        if(eventShows == null) {
            eventShows = new ArrayList<>();
        }

        eventShows.add(show);
        showStore.put(show.getEventId(), eventShows);
        return show;
    }

    public void removeShows(Event event, Screen screen) {
        List<Show> eventShows = showStore.get(event.getEventId());
        eventShows.removeIf(show -> show.getScreenId().equals(screen.getId()));
    }
}
