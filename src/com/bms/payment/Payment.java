package com.bms.payment;

import com.bms.user.User;

public class Payment {
    final Integer id;
    final Integer amount;
    final PaymentStatus status;
    final User user;

    Payment(User user, Integer id, Integer amount, PaymentStatus status) {
        this.id = id;
        this.user = user;
        this.amount = amount;
        this.status = status;
    }

    public PaymentStatus getStatus() {
        return this.status;
    }

    public Integer getAmount() {
        return this.amount;
    }
}
