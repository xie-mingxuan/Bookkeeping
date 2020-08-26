package com.service;

import java.math.BigDecimal;

public interface UserService {

    int manageMoney(int userId, BigDecimal decimal);

    int deleteAccount(int userId);
}
