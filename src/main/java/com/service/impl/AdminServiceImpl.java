package com.service.impl;

import com.ComputeMD5;
import com.dao.AdminDao;
import com.entity.RecordAdmin;
import com.entity.User;
import com.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import static com.constant.OperationType.*;

@Service
public class AdminServiceImpl implements AdminService {

    AdminDao adminDao;

    public AdminServiceImpl(AdminDao userDao) {
        this.adminDao = userDao;
    }

    @Override
    @Transactional
    public int addUser(int adminId, String username, BigDecimal money) {
        if (adminDao.findByUsername(username) != null)
            return -1;
        adminDao.addUser(username, money);
        int userId = adminDao.findByUsername(username).getUserId();
        return adminDao.addRecord(adminId, ADD_USER, userId, username, money,
                new Timestamp(System.currentTimeMillis()));
    }

    @Override
    @Transactional
    public int addAdmin(int adminId, String username) {
        if (adminDao.findByUsername(username) != null)
            return -1;
        adminDao.addAdmin(username);
        int userId = adminDao.findByUsername(username).getUserId();
        return adminDao.addRecord(adminId, ADD_ADMIN, userId, username, new BigDecimal("0.00"),
                new Timestamp(System.currentTimeMillis()));
    }

    @Override
    @Transactional
    public int deleteAccount(int adminId) {
        return adminDao.deleteAccount(adminId);
    }

    @Override
    @Transactional
    public int manageMoney(int adminId, String username, BigDecimal decimal) {
        User user = adminDao.findByUsername(username);
        if (user == null) return 0;
        int userId = user.getUserId();
        BigDecimal money = adminDao.queryMoneyById(userId);
        BigDecimal res = money.add(decimal);
        if (res.compareTo(new BigDecimal("0.00")) < 0)
            return 0;
        adminDao.updateMoney(userId, res);
        return adminDao.addRecord(adminId, CHANGE_MONEY, userId, username, decimal,
                new Timestamp(System.currentTimeMillis()));
    }

    @Override
    @Transactional
    public int changePassword(int adminId, String password) {
        String encryptedPassword = ComputeMD5.encryptPassword(password);
        return adminDao.changePassword(adminId, encryptedPassword);
    }

    @Override
    public List<RecordAdmin> queryRecords(int adminId) {
        return adminDao.findRecord(adminId);
    }
}
