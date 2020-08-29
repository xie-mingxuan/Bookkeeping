package com.service;

import com.entity.RecordAdmin;

import java.math.BigDecimal;
import java.util.List;

public interface AdminService {

    /**
     * 注册账户
     *
     * @param adminId  管理员ID
     * @param username 用户名
     * @param money    初始钱数
     * @return 数据库变动行数
     */
    int addUser(int adminId, String username, BigDecimal money);

    /**
     * 注册管理员
     *
     * @param adminId  管理员ID
     * @param username 用户名
     * @return 数据库变动行数
     */
    int addAdmin(int adminId, String username);

    /**
     * 删除账户
     *
     * @param adminId 管理员id
     * @return 数据库变动行数
     */
    int deleteAccount(int adminId);

    /**
     * 管理用户账户
     *
     * @param adminId 管理员id
     * @param userId  账户 id
     * @param decimal 变动的钱数
     * @return 数据库变动行数
     */
    int manageMoney(int adminId, int userId, BigDecimal decimal);

    /**
     * 修改密码
     *
     * @param adminId  管理员 id
     * @param password 新密码
     * @return 数据库变动行数
     */
    int changePassword(int adminId, String password);

    /**
     * 查询记录
     *
     * @param adminId 管理员 id
     * @return 记录列表
     */
    List<RecordAdmin> queryRecords(int adminId);
}
