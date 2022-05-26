package com.example.autho.service;

import com.example.autho.annotation.MyLog;
import com.example.autho.dao.UserDao;
import com.example.autho.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;
//
//    public UserService(UserDao userDao) {
//        this.userDao = userDao;
//    }

//    @Autowired
//    UserDao userDao;
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @MyLog(requestUrl = "tst")
    public User findById(Integer id) {
        return userDao.findById(id);
    }
}
