package com.service.impl;

import com.dao.UserDao;
import com.entity.RecordUser;
import com.entity.User;
import com.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import static com.ComputeMD5.encryptPassword;

@Service
public class UserServiceImpl implements UserService {

    UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user == null) return null;
        if (encryptPassword(password).equals(user.getPassword()) && user.getDeleted() != 1) return user;
        return null;
    }

    @Override
    public User findById(int userId) {
        return userDao.findByUserId(userId);
    }

    @Override
    @Transactional
    public int manageMoney(int userId, BigDecimal decimal, String explanatory) {
        BigDecimal money = userDao.queryMoneyById(userId);
        BigDecimal res = money.add(decimal);
        if (res.compareTo(new BigDecimal("0.00")) < 0)
            return 0;
        userDao.addRecord(userId, decimal, new Timestamp(System.currentTimeMillis()), explanatory);
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
        if (userDao.findByUsername(username) == null) {
            int res = userDao.register(username, money);
            changePassword(userDao.findIdByUsername(username), "123456");
            return res;
        }
        return 0;
    }

    @Override
    @Transactional
    public int changePassword(int userId, String password) {
        String encryptedPassword = encryptPassword(password);
        return userDao.changePassword(userId, encryptedPassword);
    }

    @Override
    public List<RecordUser> queryRecords(int userId) {
        return userDao.findRecord(userId);
    }

    @Override
    public boolean checkUsername(String username) {
        return userDao.findByUsername(username) == null;
    }
}
