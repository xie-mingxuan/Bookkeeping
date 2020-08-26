package com.service.impl;

import com.dao.UserDao;
import com.service.UserService;

import java.math.BigDecimal;

public class UserServiceImpl implements UserService {

    UserDao userDao;

    @Override
    public int manageMoney(int userId, BigDecimal decimal) {
        return userDao.manageMoney(userId, decimal);
    }

    @Override
    public int deleteAccount(int userId) {
        return userDao.deleteAccount(userId);
    }
}
