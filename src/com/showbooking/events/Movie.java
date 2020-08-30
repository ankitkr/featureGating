package com.showbooking.events;

public class Movie implements Event {
    Long id;
    String title;
    String genre;
    String language;

    Movie(Long id, String title, String genre, String language) {
        this.id = id;
        this.title = title;
        this.genre = genre.toUpperCase();
        this.language = language.toUpperCase();
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setGenre(String genre) {
        this.genre = genre.toUpperCase();
    }

    @Override
    public void setLanguage(String genre) {
        this.language = language.toUpperCase();
    }

    @Override
    public Long getEventId() {
        return this.id;
    }

    @Override
    public String getEventTitle() {
        return this.title;
    }

    @Override
    public String getEventGenre() {
        return this.genre;
    }

    @Override
    public String getEventLanguage() {
        return this.language;
    }

    @Override
    public String toString() {
        return "Id: " + id + ", Title: " + title + ", Genre: " + genre +", Language: " + language;
    }
}
