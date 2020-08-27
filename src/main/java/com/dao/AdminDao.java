package com.dao;

import com.entity.RecordAdmin;
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
public interface AdminDao {

    @Insert("INSERT INTO user('username', 'money') VALUES(#{username}, #{money})")
    int addUser(@Param("username") String username, @Param("money") BigDecimal money);

    @Insert("INSERT INTO user('username', 'user_type') VALUES(#{username}, 1)")
    int addAdmin(@Param("username") String username);

    @Update("UPDATE user SET deleted = 1 WHERE user_id = #{user_id}")
    int deleteAccount(@Param("user_id") int adminId);

    @Update("UPDATE user SET money = #{option} WHERE user_id = {user_id}")
    int updateMoney(@Param("user_id") int adminId, @Param("option") BigDecimal decimal);

    @Insert("INSERT INTO record_admin VALUES(#{user_id}, #{option_type}, #{option_id}, #{option}), #{time}")
    int addRecord(@Param("user_id") int adminId, @Param("option_type") int optionType,
                  @Param("option_id") int userId, @Param("option") BigDecimal option,
                  @Param("time") Timestamp time);

    @Select("SELECT money FROM user WHERE user_id = #{user_id}")
    BigDecimal queryMoneyById(@Param("userId") int adminId);

    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(@Param("username") String username);

    @Update("UPDATE user SET password = #{password} WHERE user_id = #{user_id}")
    int changePassword(@Param("user_id") int userId, @Param("password") String password);

    @Select("SELECT * FROM record_admin WHERE user_id = #{user_id} ORDER BY time DESC")
    List<RecordAdmin> findRecord(@Param("user_id") int adminId);
}
