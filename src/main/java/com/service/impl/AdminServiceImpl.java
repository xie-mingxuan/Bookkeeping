package com.service.impl;

import com.dao.AdminDao;
import com.entity.RecordAdmin;
import com.entity.RecordUser;
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
            return 0;
        adminDao.addUser(username, money);
        int userId = adminDao.findByUsername(username).getUserId();
        return adminDao.addRecord(adminId, ADD_USER, userId, money, new Timestamp(System.currentTimeMillis()));
    }

    @Override
    @Transactional
    public int addAdmin(int adminId, String username) {
        if (adminDao.findByUsername(username) != null)
            return 0;
        adminDao.addAdmin(username);
        int userId = adminDao.findByUsername(username).getUserId();
        return adminDao.addRecord(adminId, ADD_ADMIN, userId, new BigDecimal("0.00"),
                new Timestamp(System.currentTimeMillis()));
    }

    @Override
    @Transactional
    public int deleteAccount(int adminId) {
        return adminDao.deleteAccount(adminId);
    }

    @Override
    @Transactional
    public int manageMoney(int adminId, int userId, BigDecimal decimal) {
        BigDecimal money = adminDao.queryMoneyById(userId);
        BigDecimal res = money.add(decimal);
        if (res.compareTo(new BigDecimal("0.00")) < 0)
            return 0;
        adminDao.updateMoney(userId, res);
        return adminDao.addRecord(adminId, CHANGE_MONEY, userId, decimal, new Timestamp(System.currentTimeMillis()));
    }

    @Override
    @Transactional
    public int changePassword(int adminId, String password) {
         return adminDao.changePassword(adminId, password);
    }

    @Override
    public List<RecordAdmin> queryRecord(int adminId) {
        return adminDao.findRecord(adminId);
    }
}
