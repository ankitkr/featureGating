package com.showbooking.events;

import com.showbooking.events.exception.InvalidShowTimingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Show {
    Long id;
    Long eventId;
    Long screenId;
    Integer timing;
    Integer price;
    List<Long> bookedSeats;

    Show(Event event, Screen screen, Integer timing, Integer price) throws InvalidShowTimingException {
        if(timing < 0 ||timing > 23) {
            throw new InvalidShowTimingException();
        }

        Random rand = new Random();
        this.bookedSeats = new ArrayList<>();
        this.id = rand.nextLong();
        this.eventId = event.getEventId();
        this.screenId = screen.getId();
        this.timing = timing;
        this.price = price;
    }

    public Long getId() {
        return this.id;
    }

    public Long getEventId() {
        return this.eventId;
    }

    public Long getScreenId() {
        return this.screenId;
    }

    public Integer getTiming() {
        return this.timing;
    }

    public Integer getPrice() {
        return this.price;
    }

    public Boolean[][] getSeatingLayout(Screen screen) {
        int seatCount = 0;
        Boolean[][] seatingLayout = new Boolean[screen.getSeatingLayout().length][];
        for (int i = 0; i < seatingLayout.length; ++i) {
            int rowLength = screen.getSeatingLayout()[i].length;
            seatingLayout[i] = new Boolean[rowLength];
            System.arraycopy(screen.getSeatingLayout()[i], 0, seatingLayout[i], 0, seatingLayout[i].length);

            for(Long bookedSeatNo: this.bookedSeats){
                if(seatCount < bookedSeatNo && bookedSeatNo <= (seatCount + rowLength)) {
                    int j = (int)(bookedSeatNo - seatCount - 1);
                    seatingLayout[i][j] = false;
                }
            }
            seatCount += rowLength;
        }

        return seatingLayout;
    }

    public synchronized Boolean bookSeats(List<Long> seatIds) {
        for(Long seatId: seatIds) {
            if(this.bookedSeats.contains(seatId))
                return false;
        }

        this.bookedSeats.addAll(seatIds);
        return true;
    }

    public void unBookSeats(List<Long> seatIds) {
        this.bookedSeats.removeAll(seatIds);
    }
}