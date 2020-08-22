package com.featureGating.customer;

import java.util.Map;

public class User {
    Map<String, Object> raw;
    String name;
    Integer age;
    UserGender gender;
    UserAddress address;

    public User(Map<String, Object> data, String name, Integer age, String gender,
                Map<String, Object> address) {
        this.raw = data;
        this.name = name;
        this.age = age;
        this.gender = UserGender.valueOf(gender.toUpperCase());
        this.address = new UserAddress((String)address.get("line1"), (String)address.get("city"),
                (String)address.get("state"), (String)address.get("pinCode"));
    }

    public String getName() {
        return this.name;
    }

    public UserGender getGender() {
        return this.gender;
    }

    public Integer getAge() {
        return this.age;
    }

    public UserAddress getAddress() {
        return this.address;
    }

    public Integer pastOrderAmount() {
        return (Integer) this.raw.get("past_order_amount");
    }
}
