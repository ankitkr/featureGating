package com.bms.event;


import java.util.List;
import java.util.stream.Collectors;

public class Movie implements Event {
    Integer id;
    String name;
    String imageUrl;
    List<Genre> genres;

    public Movie(Integer id, String name, String imageUrl, List<Genre> genres) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.genres = genres;
    }


    @Override
    public Integer getEventId() {
        return this.id;
    }

    @Override
    public String getEventName() {
        return this.name;
    }

    @Override
    public String getEventDetails() {
        return "Name: " + this.name + ", Genre: " + this.genres.stream().map(Enum::toString).collect(Collectors.joining(","));
    }

    @Override
    public String getEventImageUrl() {
        return this.imageUrl;
    }
}
