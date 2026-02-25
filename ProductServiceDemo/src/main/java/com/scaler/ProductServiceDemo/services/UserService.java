package com.scaler.ProductServiceDemo.services;

import com.scaler.ProductServiceDemo.models.User;

import java.util.List;

public interface UserService {
    User getSingleUser(Long userId);

    List<User> getAllUsers();
}
