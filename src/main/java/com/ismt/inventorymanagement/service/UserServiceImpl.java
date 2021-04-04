package com.ismt.inventorymanagement.service;

import com.ismt.inventorymanagement.dao.UserDao;
import com.ismt.inventorymanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUser() {
        return null;
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public void save(User user) {
        //encode the password being saved by user
        User securedUser=new User();
        securedUser.setName(user.getName());
        securedUser.setEmail(user.getEmail());
        securedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        securedUser.setPhoneNumber(user.getPhoneNumber());
        //save the user after encoding password
        userDao.save(securedUser);
    }

    @Override
    @Transactional
    public User findUserByEmail(String email) {
        //check the database for existing user with email.
        return userDao.findUserByEmail(email);
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=findUserByEmail(s);
        if(user == null){
            throw new UsernameNotFoundException("Invalid Email or Password");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),
        getAuthorities());
    }
    @Override
    @Transactional
    public void delete(int id) {
        userDao.delete(id);
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        list.add(new SimpleGrantedAuthority("User"));

        return list;
    }
}
