package com.sushil.main.services;

import java.util.List;

import com.sushil.main.entities.User;

public interface UserService {

    User saveUser(User user);

    User getUserById(int id);  

    User updateUser(User user);

    void deleteUser(User user);

    User isUserExistByEmail(String email);

    List<User> getAllUser();

}
