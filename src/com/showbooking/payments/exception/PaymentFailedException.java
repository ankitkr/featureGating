package com.showbooking.payments.exception;

public class PaymentFailedException extends Exception {
    public PaymentFailedException(){
        super("Your payment could not be processed.");
    };
}
