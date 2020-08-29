package com.controller;

import com.dao.AdminDao;
import com.dao.UserDao;
import com.entity.RecordAdmin;
import com.entity.RestResp;
import com.entity.User;
import com.service.AdminService;
import com.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;
    private final UserService userService;

    @PostMapping("/login")
    public RestResp login(@RequestParam("username") String username,
                          @RequestParam("password") String password) {
        User user = userService.login(username, password);
        return (user != null && user.getUserType() == 2) ? RestResp.ok(user) : RestResp.error("登录失败");
    }

    @Transactional
    @PostMapping("/addUser")
    public RestResp addUser(@RequestParam("adminId") int userId,
                            @RequestParam("username") String username,
                            @RequestParam("decimal") BigDecimal decimal) {
        int res = adminService.addUser(userId, username, decimal);
        return res > 0 ? RestResp.ok() : RestResp.error("创建新用户失败");
    }

    @Transactional
    @PostMapping("/addAdmin")
    public RestResp addAdmin(@RequestParam("adminId") int adminId,
                             @RequestParam("username") String username) {
        int res = adminService.addAdmin(adminId, username);
        return res > 0 ? RestResp.ok() : RestResp.error("创建新用户失败");
    }

    @Transactional
    @PostMapping("/deleteAccount")
    public RestResp deleteAccount(@RequestParam("adminId") int adminId) {
        return adminService.deleteAccount(adminId) > 0 ? RestResp.ok() : RestResp.error("删除失败");
    }

    @Transactional
    @PostMapping("/manageMoney")
    public RestResp manageMoney(@RequestParam("adminId") int adminId,
                                @RequestParam("userId") int userId,
                                @RequestParam("decimal") BigDecimal decimal) {
        int res = adminService.manageMoney(adminId, userId, decimal);
        return res > 0 ? RestResp.ok() : RestResp.error("修改失败");
    }

    @Transactional
    @PostMapping("/changePassword")
    public RestResp changePassword(@RequestParam("adminId") int adminId,
                                   @RequestParam("newPassword") String newPassword) {
        return adminService.changePassword(adminId, newPassword) > 0 ? RestResp.ok() : RestResp.error("修改失败");
    }

    @GetMapping("/queryRecords")
    public RestResp queryRecords(@RequestParam("adminId") int adminId) {
        return RestResp.ok(adminService.queryRecords(adminId));
    }
}
