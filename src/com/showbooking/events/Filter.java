package com.showbooking.events;

public class Filter {
    String title;
    String genre;
    String language;
    Integer minPrice;
    Integer maxPrice;

    Filter(String title, String genre, String language, String price) {
        if(title != null)
            this.title = title;
        if(genre != null)
            this.genre = genre;
        if(language != null)
            this.language = language;

        if(price != null) {
            String[] prices = price.split(":", 2);
            if(!prices[0].isEmpty())
                this.minPrice = Integer.valueOf(prices[0]);
            if(!prices[1].isEmpty())
                this.maxPrice = Integer.valueOf(prices[1]);
        }
    }

    public String getTitle() {
        return this.title;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getLanguage() {
        return this.language;
    }

    public Integer getMinPrice() {
        return this.minPrice;
    }

    public Integer getMaxPrice() {
        return this.maxPrice;
    }
}
