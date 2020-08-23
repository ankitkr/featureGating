package com.bms.repository;

import com.bms.event.Event;
import com.bms.event.Genre;
import com.bms.event.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
    private volatile static MovieRepository instance;
    private final List<Event> movieList;

    private MovieRepository() {
        this.movieList = new ArrayList<Event>() {
            {
                add(new Movie(1, "Gunjan Saxena: The Kargil Girl", "http://www.random.in/tkg_1.jpg",
                                new ArrayList<Genre>() {
                                    {
                                        add(Genre.DRAMA);
                                    }
                                }
                        )
                );
                add(new Movie(2, "Raat Akeli Hai", "http://www.random.in/rah_1.jpg",
                                new ArrayList<Genre>() {
                                    {
                                        add(Genre.DRAMA);
                                        add(Genre.CRIME);
                                        add(Genre.MYSTERY);
                                    }
                                }
                        )
                );
                add(new Movie(3, "Dil Bechara", "http://www.random.in/db_1.jpg",
                                new ArrayList<Genre>() {
                                    {
                                        add(Genre.DRAMA);
                                        add(Genre.ROMANCE);
                                    }
                                }
                        )
                );
                add(new Movie(4, "Project Power", "http://www.random.in/pp_1.jpg",
                                new ArrayList<Genre>() {
                                    {
                                        add(Genre.ACTION);
                                        add(Genre.CRIME);
                                        add(Genre.SCI_FI);
                                    }
                                }
                        )
                );
                add(new Movie(5, "Tanhaji", "http://www.random.in/t_1.jpg",
                                new ArrayList<Genre>() {
                                    {
                                        add(Genre.ACTION);
                                    }
                                }
                        )
                );
            }
        };
    }

    public static MovieRepository getInstance() {
        if (instance == null) {
            synchronized (MovieRepository.class) {
                if (instance == null) {
                    instance = new MovieRepository();
                }
            }
        }
        return instance;
    }

    public List<Event> fetchAllMovies() {
        return this.movieList;
    }

    public List<Event> searchMovie(String movieName) {
        List<Event> searchList = new ArrayList<>();
        if(movieName == null || movieName.isEmpty()) {
            return searchList;
        }

        for(Event movie: this.movieList) {
            if(movieName.equalsIgnoreCase(movie.getEventName())) {
                searchList.add(movie);
            }
        }

        return searchList;
    }
}
