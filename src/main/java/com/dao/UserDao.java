package com.dao;

import java.math.BigDecimal;

public interface UserDao {

    int manageMoney(int userId, BigDecimal decimal);

    int deleteAccount(int userId);
}
