package com.showbooking.payments;

import com.showbooking.payments.exception.PaymentFailedException;

public interface Payable {
    Payment makePayment(Long userId, Integer amount) throws PaymentFailedException;
    void refundPayment(Payment payment);
}
