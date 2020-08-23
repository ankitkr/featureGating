package com.bms.payment;

import com.bms.payment.exception.PaymentFailedException;
import com.bms.user.User;

public interface Payable {
    Payment makePayment(User user, Integer amount) throws PaymentFailedException;
    void refundPayment(User user, Integer amount);
}
