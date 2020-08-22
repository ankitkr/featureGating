package com.featureGating.customer;

public class UserAddress {
    String line1;
    String city;
    String state;
    String pinCode;

    UserAddress(String line1, String city, String state, String pinCode) {
        this.line1 = line1;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
    }

    public String getLine1() {
        return this.line1;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public String getPinCode() {
        return this.pinCode;
    }
}
