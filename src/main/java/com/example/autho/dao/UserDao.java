package com.example.autho.dao;

import com.example.autho.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    public User findById(Integer id) {
        if (id > 10) {
            return null;
        }
        return new User(id,"用户名—"+id);
    }
}
