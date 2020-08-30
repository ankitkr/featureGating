package com.showbooking.events;

public interface Event {
    void setTitle(String title);
    void setGenre(String genre);
    void setLanguage(String genre);

    Long getEventId();
    String getEventTitle();
    String getEventGenre();
    String getEventLanguage();
}
