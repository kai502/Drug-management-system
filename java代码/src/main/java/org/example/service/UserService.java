package org.example.service;

import org.example.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.example.pojo.UserPassword;
import org.example.utils.ApiResult;
import org.springframework.web.multipart.MultipartFile;

/**
* @author 陈鸿凯
* @description 针对表【user】的数据库操作Service
* @createDate 2023-11-19 22:19:59
*/
public interface UserService extends IService<User> {

    ApiResult login(User user);

    ApiResult getUserInfo(String token);

    ApiResult register(User user);

    Object sendImage(Object userId,MultipartFile file);

    ApiResult updateUserInfo(User user);

    ApiResult updateUserPassword(UserPassword userPassword);
}
