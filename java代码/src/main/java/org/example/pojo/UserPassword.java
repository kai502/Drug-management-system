package org.example.pojo;

import lombok.Data;

/**
 * @模块文件夹：UserPassword
 * @项目名称：drug-administration
 * @描述：
 * @作者 ：陈鸿凯
 * @date：2023.11.24.21:54
 */
@Data
public class UserPassword {
    private Integer uid;
    private String oldPassword;
    private String newPassword;
}
