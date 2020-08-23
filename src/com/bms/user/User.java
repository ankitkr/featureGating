package com.bms.user;

public class User {
    Integer id;
    String  name;
    Gender gender;
    String emailId;
    public User(Integer id, String name, Gender gender, String emailID) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.emailId = emailID;
    }

    public Integer getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
}
