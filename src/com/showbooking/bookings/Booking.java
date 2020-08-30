package com.showbooking.bookings;

import com.showbooking.events.Show;
import com.showbooking.payments.Payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Booking {
    Show show;
    Payment payment;

    Long id;
    Long userId;
    Boolean isActive;
    List<Long> seatIds;
    private final Lock lock = new ReentrantLock();

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

    public Lock getLock() {
        return this.lock;
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
