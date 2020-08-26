package com.dao;

import java.math.BigDecimal;

public interface AdminDao {

    int addUser();

    int addAdmin();

    int deleteAccount(int userId);

    int manageMoney(int userId, BigDecimal decimal);
}
