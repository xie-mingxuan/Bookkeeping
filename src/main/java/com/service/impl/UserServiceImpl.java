package com.service.impl;

import com.dao.UserDao;
import com.entity.RecordUser;
import com.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int login(String username, String password) {
        return userDao.login(username, password);
    }

    @Override
    @Transactional
    public int manageMoney(int userId, BigDecimal decimal) {
        BigDecimal money = userDao.queryMoneyById(userId);
        BigDecimal res = money.add(decimal);
        if (res.compareTo(new BigDecimal("0.00")) < 0)
            return 0;
        userDao.addRecord(userId, decimal, new Timestamp(System.currentTimeMillis()));
        return userDao.updateMoney(userId, res);
    }

    @Override
    @Transactional
    public int deleteAccount(int userId) {
        return userDao.deleteAccount(userId);
    }

    @Override
    @Transactional
    public int register(String username, BigDecimal money) {
        if (userDao.findByUsername(username) == null)
            return userDao.register(username, money);
        return 0;
    }

    @Override
    @Transactional
    public int changePassword(int userId, String password) {
        return userDao.changePassword(userId, password);
    }

    @Override
    public List<RecordUser> findRecord(int userId) {
        return userDao.findRecord(userId);
    }
}
