package com.bms.payment;

import com.bms.payment.exception.PaymentFailedException;
import com.bms.user.User;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PaymentGateway implements Payable {
    private volatile static PaymentGateway instance;

    private PaymentGateway() {

    }

    public static PaymentGateway getInstance() {
        if(instance == null) {
            synchronized (PaymentGateway.class) {
                if(instance == null) {
                    instance = new PaymentGateway();
                }
            }
        }
        return instance;
    }

    @Override
    public Payment makePayment(User user, Integer amount) throws PaymentFailedException {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        Random rand = new Random();
        int paymentProbability = rand.nextInt(100);
        if(paymentProbability >= 25) {
            System.out.println("User " + user.getId() + ", made successful payment of Rs. " + amount);
            return new Payment(user, amount, rand.nextInt(1000), PaymentStatus.SUCCESS);
        }

        System.out.println("User " + user.getId() + ", payment of Rs. " + amount + "failed");
        throw new PaymentFailedException();
    }

    @Override
    public void refundPayment(User user, Integer amount) {
        //call method to refund payment
    }
}
