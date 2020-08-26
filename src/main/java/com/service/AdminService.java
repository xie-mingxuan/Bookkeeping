package com.service;

import java.math.BigDecimal;

public interface AdminService {

    int addUser();

    int addAdmin();

    int deleteAccount(int userId);

    int manageMoney(int userId, BigDecimal decimal);
}
