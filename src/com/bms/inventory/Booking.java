package com.bms.inventory;

import com.bms.payment.Payment;
import com.bms.user.User;

public class Booking {
    User user;
    Show show;
    Payment payment;
    int[] bookedSeatIds;

    Booking(User user, Show show, int[] bookedSeatIds, Payment payment) {
        this.user = user;
        this.show = show;
        this.bookedSeatIds = bookedSeatIds;
        this.payment = payment;
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
