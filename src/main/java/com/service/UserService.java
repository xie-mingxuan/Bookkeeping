package com.service;

import com.entity.RecordUser;
import com.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {

    /**
     * 用户名登录方法
     *
     * @param username 用户名
     * @param password 密码
     * @return 数据库变动行数
     */
    User login(String username, String password);

    /**
     * 通过userId查找用户
     *
     * @param userId userId
     * @return 用户
     */
    User findById(int userId);

    /**
     * 存取钱方法
     *
     * @param userId  用户 id
     * @param decimal 存取钱数目
     * @param text    备注文档
     * @return 数据库变动行数
     */
    int manageMoney(int userId, BigDecimal decimal, String text);

    /**
     * 删除账户方法
     *
     * @param userId 账户 id
     * @return 数据库变动行数
     */
    int deleteAccount(int userId);

    /**
     * 注册账户方法
     *
     * @param username 用户输入的用户名
     * @param money    用户初始钱币
     * @return 数据库变动行数
     */
    int register(String username, BigDecimal money);

    /**
     * 修改密码方法
     *
     * @param userId   用户 id
     * @param password 新密码
     * @return 数据库变动行数
     */
    int changePassword(int userId, String password);

    /**
     * 查询记录
     *
     * @param userId 用户 id
     * @return 记录列表
     */
    List<RecordUser> queryRecords(int userId);

    /**
     * 检查 username 是否已经被使用
     *
     * @param username 用户名
     * @return 使用返回 false
     */
    boolean checkUsername(String username);
}
