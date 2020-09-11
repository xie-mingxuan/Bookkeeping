package com.dao;

import com.entity.RecordUser;
import com.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface UserDao {

    @Update("UPDATE user SET money = #{option} WHERE user_id = #{user_id}")
    int updateMoney(@Param("user_id") int userId, @Param("option") BigDecimal option);

    @Insert("INSERT INTO record_user VALUES(#{user_id}, #{option}, #{time}, #{text})")
    int addRecord(@Param("user_id") int userId, @Param("option") BigDecimal option, @Param("time") Timestamp time,
                  @Param("text") String text);

    @Update("UPDATE user SET password = #{new_password} WHERE user_id = #{user_id}")
    int changePassword(@Param("user_id") int userId, @Param("new_password") String newPassword);

    @Update("UPDATE user SET deleted = 1 WHERE user_id = #{user_id}")
    int deleteAccount(@Param("user_id") int userId);

    @Insert("INSERT INTO user (username, money) VALUES(#{username}, #{money})")
    int register(@Param("username") String username, @Param("money") BigDecimal money);

    @Select("SELECT money FROM user WHERE user_id = #{user_id}")
    BigDecimal queryMoneyById(@Param("user_id") int userId);

    @Select("SELECT * FROM user WHERE username = #{username}")
    @Results(id = "res1", value = {
            @Result(id = true, column = "user_id", property = "userId"),
            @Result(column = "user_type", property = "userType")
    })
    User findByUsername(@Param("username") String username);

    @Select("SELECT * FROM user WHERE user_id = #{user_id}")
    @ResultMap(value = "res1")
    User findByUserId(@Param("user_id") int userId);

    @Select("SELECT user_id FROM user WHERE username = #{username}")
    int findIdByUsername(@Param("username") String username);

    @Select("SELECT * FROM record_user WHERE user_id = #{user_id} ORDER BY time DESC")
    @Results(id = "res2", value = {
            @Result(id = true, column = "user_id", property = "userId")
    })
    List<RecordUser> findRecord(@Param("user_id") int userId);
}
