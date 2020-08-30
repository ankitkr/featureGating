package com.showbooking.payments;

import com.showbooking.payments.exception.PaymentFailedException;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PaymentService implements Payable {

    @Override
    public Payment makePayment(Long userId, Integer amount) throws PaymentFailedException {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        Random rand = new Random();
        int paymentProbability = rand.nextInt(100);
        if(paymentProbability >= 25) {
            System.out.println("User " + userId + ", made successful payment of Rs. " + amount);
            return new Payment(rand.nextLong(), userId, amount, PaymentStatus.SUCCESS);
        }

        System.out.println("User " + userId + ", payment of Rs. " + amount + "failed");
        throw new PaymentFailedException();
    }

    @Override
    public void refundPayment(Payment payment) {
        System.out.println("Refund of Rs. " + payment.getAmount() + ", successful for user " + payment.getUserId());
    }
}
