import request from "@/utils/request"


//登录接口
export const loginApi = (userInfo) =>{
    return request.post("user/login",userInfo)
}

export const getUserInfo = () =>{
    return request.get("user/getUserInfo")
}

export const regist = (userInfo) =>{
    return request.post("user/register",userInfo)
}

export const updateUserInfo = (userInfo) =>{
    return request.post("user/updateUserInfo",userInfo)
}

export const updateUserPassword = (userPassword) =>{
    return request.post("user/updateUserPassword",userPassword)
}