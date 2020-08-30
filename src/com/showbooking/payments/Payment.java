package com.showbooking.payments;


public class Payment {
    final Long id;
    final Long userId;
    final Integer amount;
    final PaymentStatus status;


    Payment(Long id, Long userId, Integer amount, PaymentStatus status) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.status = status;
    }

    public PaymentStatus getStatus() {
        return this.status;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public Long getUserId(){
        return this.userId;
    }
}
