package com.bms.inventory;


import com.bms.event.Event;
import com.bms.inventory.exception.SeatUnavailable;

import java.util.Arrays;

public class Show {
    Event event;
    Integer theaterId;
    int[] seats;
    long startTime, endTime;
    final Integer totalSeatCount;
    final Integer price;

    public Show(Event event, Integer price, Integer theaterId, Integer totalSeatCount, long startTime, long endTime) {
        this.event = event;
        this.price = price;
        this.theaterId = theaterId;
        this.totalSeatCount = totalSeatCount;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seats = new int[totalSeatCount];
        Arrays.fill(seats, 0);
    }

    public String getDetails() {
        return "Show Details -> Name: " + this.event.getEventName() + ", Price: " + this.price + ", Start Time: " +
                this.startTime + ", End Time" + this.endTime + ", Seating Capacity: " + this.totalSeatCount;
    }

    public String fetchSeatingDetails() {
        return Arrays.toString(this.seats);
    }

    public Integer getPrice() {
        return this.price;
    }

    public void bookSeats(int[] seatIds) {
        for(int seatId: seatIds) {
            seats[seatId] = 1;
        }
    }

    public void unbookSeats(int[] seatIds) {
        for(int seatId: seatIds) {
            seats[seatId] = 0;
        }
    }

    public boolean seatsAvailable(int[] seatIds) throws SeatUnavailable {
        for(int seatId: seatIds) {
            if (seatId >= seats.length) {
                throw new SeatUnavailable();
            }

            if(seats[seatId] != 0)
                return false;
        }
        return true;
    }
}
