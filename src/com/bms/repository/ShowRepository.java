package com.bms.repository;

import com.bms.event.Event;
import com.bms.inventory.Show;

import java.util.ArrayList;
import java.util.List;

public class ShowRepository {
    private volatile static ShowRepository instance;
    private ShowRepository() {
    }

    public static ShowRepository getInstance() {
        if (instance == null) {
            synchronized (ShowRepository.class) {
                if (instance == null) {
                    instance = new ShowRepository();
                }
            }
        }
        return instance;
    }

    public List<Show> getShows(Event movie) {
        List<Show> availableShows = new ArrayList<>();
        if(movie == null) {
            return availableShows;
        }

        //ideally should be done during initialization
        availableShows.add(new Show(movie, 350, 1, 10, 1310, 1450));
        availableShows.add(new Show(movie, 400,2, 30, 1410, 1550));
        availableShows.add(new Show(movie, 250, 1, 10, 910, 1050));
        return availableShows;
    }
}
