package com.bms.repository;

import com.bms.user.Gender;
import com.bms.user.User;
import com.bms.user.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private volatile static UserRepository instance;
    private final List<User> registeredUsers;
    private UserRepository() {
        this.registeredUsers = new ArrayList<User>(){
            {
                add(new User(1, "SomeRandomName1", Gender.MALE, "randomName1@mailinator.com"));
                add(new User(2, "SomeRandomName2", Gender.FEMALE, "randomName2@mailinator.com"));
                add(new User(3, "SomeRandomName3", Gender.FEMALE, "randomName3@mailinator.com"));
                add(new User(4, "SomeRandomName4", Gender.OTHER, "randomName4@mailinator.com"));
                add(new User(5, "SomeRandomName5", Gender.MALE, "randomName5@mailinator.com"));
            }
        };
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }


    public User getUser(Integer userId) throws UserNotFoundException {
        for(User user: registeredUsers) {
            if(userId.equals(user.getId())){
                return user;
            }
        }

        throw new UserNotFoundException();
    }
}
