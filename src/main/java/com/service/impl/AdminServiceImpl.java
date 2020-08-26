package com.service.impl;

import com.dao.AdminDao;
import com.service.AdminService;

import java.math.BigDecimal;

public class AdminServiceImpl implements AdminService {

    AdminDao userDao;

    public AdminServiceImpl(AdminDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int addUser() {
        return userDao.addUser();
    }

    @Override
    public int addAdmin() {
        return userDao.addAdmin();
    }

    @Override
    public int deleteAccount(int userId) {
        return userDao.deleteAccount(userId);
    }

    @Override
    public int manageMoney(int userId, BigDecimal decimal) {
        return userDao.manageMoney(userId, decimal);
    }
}
