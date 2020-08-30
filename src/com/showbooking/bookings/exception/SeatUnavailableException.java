package com.showbooking.bookings.exception;

public class SeatUnavailableException extends Exception {
    public SeatUnavailableException() {
        super("Selected seat not available!");
    }
}
