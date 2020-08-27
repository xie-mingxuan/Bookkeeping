package com.dao;

import com.entity.RecordUser;
import com.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface UserDao {

    @Select("SELECT FROM user WHERE user_type = 1 AND username = #{username} " +
            "AND password = #{password} AND deleted = 0")
    int login(@Param("username") String username, @Param("password") String password);

    @Update("UPDATE user SET money = option WHERE user_id = #{user_id}")
    int updateMoney(@Param("user_id") int userId, @Param("option") BigDecimal option);

    @Insert("INSERT INTO record_user VALUES(#{user_id}, #{option}, #{time})")
    int addRecord(@Param("user_id") int userId, @Param("option") BigDecimal option, @Param("time") Timestamp time);

    @Update("UPDATE user SET password = #{new_password} WHERE user_id = #{user_id}")
    int changePassword(@Param("user_id") int userId, @Param("new_password") String newPassword);

    @Update("UPDATE user SET deleted = 1 WHERE user_id = #{user_id}")
    int deleteAccount(@Param("user_id") int userId);

    @Insert("INSERT INTO user ('username', 'money') VALUES(#{username}, #{money})")
    int register(@Param("username") String username, @Param("money") BigDecimal money);

    @Select("SELECT FROM user WHERE user_id = #{user_id}")
    BigDecimal queryMoneyById(@Param("user_id") int userId);

    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(@Param("username") String username);

    @Select("SELECT * FROM record_user WHERE user_id = #{user_id} ORDER BY time DESC")
    List<RecordUser> findRecord(@Param("username") int userId);
}
