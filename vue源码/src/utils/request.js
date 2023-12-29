import axios from "axios";
// import router from "../router"

const request = axios.create({
    baseURL:'http://localhost:8080/',
    timeout:10000
})

//请求拦截器
request.interceptors.request.use(
    config => {
        const token = localStorage.getItem('token')  //从本地获取token
        // console.log("token:");
        // console.log(token);
        if(token){
            // config.headers.Authorization = `Bearer ${token}`
            config.headers["token"] = token;
        }
        return config;
    },
    error => {
      return Promise.reject(error);
    }
)

//响应拦截器
request.interceptors.response.use(
    // response =>{
    //     return response.data.code === 200 ? response : Promise.reject(response.data.message);
    // }
    response =>{
        return response;
    },
    error => {
        return Promise.reject(error)
    }
)

//对外暴露axios实例
export default request

