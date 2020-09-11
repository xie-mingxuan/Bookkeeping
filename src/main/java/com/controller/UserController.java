package com.controller;

import com.entity.RestResp;
import com.entity.User;
import com.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static com.ComputeMD5.encryptPassword;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public RestResp login(@RequestParam("username") String username,
                          @RequestParam("password") String password) {
        User user = userService.login(username, password);
        if (user == null) return RestResp.error("登陆失败");
        return RestResp.ok(user);
    }

    @Transactional
    @PostMapping("/register")
    public RestResp register(@RequestParam("username") String username,
                             @RequestParam("money") BigDecimal money) {
        if (!userService.checkUsername(username)) return RestResp.error("用户名已被注册");
        int res = userService.register(username, money);
        if (res > 0) return RestResp.ok("注册成功");
        return RestResp.error("注册失败");
    }

    @Transactional
    @PostMapping("/manageMoney")
    public RestResp manageMoney(@RequestParam("userId") int userId,
                                @RequestParam("decimal") BigDecimal decimal,
                                @RequestParam("explanatory") String explanatory) {
        int res = userService.manageMoney(userId, decimal, explanatory);
        return res > 0 ? RestResp.ok() : RestResp.error("余额变动失败");
    }

    @Transactional
    @PostMapping("/deleteAccount")
    public RestResp deleteAccount(@RequestParam("userId") int userId) {
        return userService.deleteAccount(userId) > 0 ? RestResp.ok() : RestResp.error("删除失败");
    }

    @Transactional
    @PostMapping("/changePassword")
    public RestResp changePassword(@RequestParam("userId") int userId,
                                   @RequestParam("password") String password,
                                   @RequestParam("newPassword") String newPassword) {
        User user = userService.findById(userId);
        if (!encryptPassword(password).equals(user.getPassword())) return RestResp.error("原密码输入不正确");
        return userService.changePassword(userId, newPassword) > 0 ? RestResp.ok() : RestResp.error("修改失败");
    }

    @GetMapping("/queryRecords")
    public RestResp queryRecords(@RequestParam("userId") int userId) {
        return RestResp.ok(userService.queryRecords(userId));
    }
}
