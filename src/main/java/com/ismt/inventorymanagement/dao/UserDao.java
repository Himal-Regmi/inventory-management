package com.ismt.inventorymanagement.dao;

import com.ismt.inventorymanagement.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUser();

    User getUserById(int id);

    void save(User user);

    User findUserByEmail(String email);

    void delete(int id);
}
