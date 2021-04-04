package com.ismt.inventorymanagement.service;

import com.ismt.inventorymanagement.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUser();

    User getUserById(int id);

    void save(User user);

    User findUserByEmail(String email);

    void delete(int id);
}
