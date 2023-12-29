package org.example.controller;

import org.example.pojo.User;
import org.example.pojo.UserPassword;
import org.example.service.UserService;
import org.example.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @模块文件夹：UserController
 * @项目名称：drug-administration
 * @描述：
 * @作者 ：陈鸿凯
 * @date：2023.11.19.22:28
 */

@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ApiResult login(@RequestBody User user){
        return userService.login(user);
//        return null;
    }

    @GetMapping("getUserInfo")
    public ApiResult getUserInfo(@RequestHeader(defaultValue = "0") String token){
        return userService.getUserInfo(token);
    }

    @PostMapping("register")
    public ApiResult register(@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("sendImage")
    public Object sendImage(@RequestParam Object userId ,@RequestParam("file") MultipartFile file){
        return userService.sendImage(userId,file);
    }

    @PostMapping("updateUserInfo")
    public ApiResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    @PostMapping("updateUserPassword")
    public ApiResult updateUserPassword(@RequestBody UserPassword userPassword){
        return userService.updateUserPassword(userPassword);
    }
}
