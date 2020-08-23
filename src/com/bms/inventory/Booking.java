package com.bms.inventory;

import com.bms.payment.Payment;
import com.bms.user.User;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Booking {
    User user;
    Show show;
    Payment payment;
    Boolean isValid;
    int[] bookedSeatIds;
    private final Lock lock = new ReentrantLock();

    Booking(User user, Show show, int[] bookedSeatIds, Payment payment) {
        this.user = user;
        this.show = show;
        this.bookedSeatIds = bookedSeatIds;
        this.payment = payment;
        this.isValid = true;
    }

    public Lock getLock() {
        return this.lock;
    }

    public Boolean getIsValid() {
        return this.isValid;
    }

    public void invalidateBooking() {
        this.isValid = false;
    }

    public User getUser() {
        return this.user;
    }

    public int[] getBookedSeatIds() {
        return this.bookedSeatIds;
    }

    public Show getShow() {
        return this.show;
    }

    public Payment getPayment() {
        return this.payment;
    }
}
