package com.bms.inventory.exception;

public class BookingAlreadyCancelledException extends Exception{
    public BookingAlreadyCancelledException(){
        super("Booking Already Cancelled!!");
    };
}
