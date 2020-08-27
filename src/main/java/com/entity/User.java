package com.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private int userId;
    private String username;
    private BigDecimal money;
    private int user_type;
}
