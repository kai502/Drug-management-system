package org.example.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.pojo.User;
import org.example.pojo.UserPassword;
import org.example.service.UserService;
import org.example.mapper.UserMapper;
import org.example.utils.ApiResult;
import org.example.utils.JwtHelper;
import org.example.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
* @author 陈鸿凯
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-11-19 22:19:59
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public ApiResult login(User user) {

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(User::getUsername,user.getUsername());

        User userSelect = userMapper.selectOne(lambdaQueryWrapper);

        if(userSelect == null){
            return ApiResult.error(null,500,"用户名错误");
        }

        if(!StringUtils.isEmpty(user.getUserPwd())
                &&
            userSelect.getUserPwd().equals(MD5Util.encrypt(user.getUserPwd()))
        ){
            String token = jwtHelper.createToken(Long.valueOf(userSelect.getUid()));

            Map tokenMap = new HashMap();
            tokenMap.put("token",token);

            return ApiResult.ok(tokenMap);

        }
        return ApiResult.error(null,501,"用户密码错误");
    }

    @Override
    public ApiResult getUserInfo(String token) {

        if(StringUtils.isEmpty(token)
                ||
            jwtHelper.isExpiration(token)){
            return ApiResult.error(null,502,"token过期或不存在");
        }

        int userId = jwtHelper.getUserId(token).intValue();

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUid,userId);

        User user = userMapper.selectOne(lambdaQueryWrapper);

        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));

        if(user == null){
            return ApiResult.error(null,503,"token查找的用户不存在");
        }

        Map userMap = new HashMap();
        userMap.put("userInfo",user);

        return ApiResult.ok(userMap);
    }

    @Override
    public ApiResult register(User user) {

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(User::getUsername,user.getUsername());

        User userSelect = userMapper.selectOne(lambdaQueryWrapper);

        if(userSelect != null ){

            return ApiResult.error(null,500,"用户名已经存在");
        }
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));

        userMapper.insert(user);

        return ApiResult.ok(null);

    }

    @Override
    public Object sendImage(Object userId,MultipartFile file) {

        if(file.isEmpty()){
            return ApiResult.error(null,600,"上传图片为空");
        }
        //文件名
//        String OriginalFilename = file.getOriginalFilename();

        //封装成全文件名
        String fileName = "user_"+userId+".png";

        //保存路径
        String path = "C:\\Users\\陈鸿凯\\Desktop\\毕业设计\\毕业设计项目代码\\drug-administration\\d1\\src\\assets\\userImage\\";

        //整体文件路径
        File dest = new File(path+fileName);
        System.out.println("dest = " + dest);
        if (!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest);
            return ApiResult.ok("图片上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResult.error(null,601,"图片上传失败");
        }

    }

    @Override
    public ApiResult updateUserInfo(User user) {

        //判断账号是否重复
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(User::getUid,user.getUid());

        User userSelect = userMapper.selectOne(lambdaQueryWrapper);
//        System.out.println(userSelect.getUsername());
//        System.out.println(user.getUsername());

        if(userSelect != null && !userSelect.getUsername().equals(user.getUsername()) ){
            return ApiResult.error(null,500,"用户名已经存在");
        }



        int row = userMapper.updateById(user);

        if(row == 0){
            return ApiResult.error(null,510,"修改失败");
        }
        return ApiResult.ok(null);

    }

    @Override
    public ApiResult updateUserPassword(UserPassword userPassword) {
        //密码加密
        String OldPassword = MD5Util.encrypt(userPassword.getOldPassword());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserPwd,OldPassword)
                .eq(User::getUid,userPassword.getUid());

        User userSelect = userMapper.selectOne(lambdaQueryWrapper);

        User user = new User();
        user.setUid(userPassword.getUid());
        user.setUserPwd(MD5Util.encrypt(userPassword.getNewPassword()));

        if(userSelect != null){
            userMapper.updateById(user);
            return ApiResult.ok(null);
        }

        return ApiResult.error(null,501,"用户旧密码不正确");

    }
}




