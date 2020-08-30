package com.showbooking.bookings;

import com.showbooking.events.Show;
import com.showbooking.payments.Payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Booking {
    Show show;
    Payment payment;

    Long id;
    Long userId;
    Boolean isActive;
    List<Long> seatIds;

    Booking(Long userId, Show show, Payment payment, List<Long> bookedSeatIds) {
        Random rand = new Random();

        this.id = rand.nextLong();
        this.isActive = true;
        this.userId = userId;
        this.show = show;
        this.seatIds = new ArrayList<>(bookedSeatIds);
        this.payment = payment;
    }

    public Long getId(){
        return this.id;
    }

    public Boolean getIsActive(){
        return this.isActive;
    }

    public Show getShow() {
        return this.show;
    }

    public Payment getPayment() {
        return this.payment;
    }

    public List<Long> getSeatIds() {
        return this.seatIds;
    }

    public void setIsActiveFalse() {
        this.isActive = false;
    }
}
