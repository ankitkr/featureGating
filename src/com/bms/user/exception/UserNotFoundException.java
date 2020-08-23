package com.bms.user.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("Specified user could not be found!");
    }
}
