package com.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class RecordAdmin {
    int userId;
    int optionType;
    int optionId;
    BigDecimal option;
    Timestamp time;
}
