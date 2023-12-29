package org.example.utils;

import com.google.protobuf.Api;
import com.mysql.cj.xdevapi.Result;
import lombok.Data;

/**
 * @模块文件夹：Result
 * @项目名称：drug-administration
 * @描述：
 * @作者 ：陈鸿凯
 * @date：2023.11.19.21:39
 */

@Data
public class ApiResult<T> {

    private Integer code;
    private String message;
    private T data;

    public static <T> ApiResult<T> build(T data){
        ApiResult<T> apiResult = new ApiResult<T>();
        if(data != null)
            apiResult.setData(data);
        return apiResult;
    }


    public static <T> ApiResult<T> error(T data,Integer code,String message){
        ApiResult apiResult = build(data);
        apiResult.setCode(code);
        apiResult.setMessage(message);
        return  apiResult;
    }

    public static <T> ApiResult<T> ok(T data){
        ApiResult apiResult = build(data);
        apiResult.setCode(200);
        apiResult.setMessage("success");
        return apiResult;
    }

}

















