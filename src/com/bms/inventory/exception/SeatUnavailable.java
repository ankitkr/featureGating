package com.bms.inventory.exception;

public class SeatUnavailable extends Exception {
    public SeatUnavailable(){
        super("Selected seat is no longer available!");
    };
}
