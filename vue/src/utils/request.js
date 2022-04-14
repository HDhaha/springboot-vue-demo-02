import axios from 'axios'
import router from "@/router";

const request = axios.create({
    baseURL: "/api",  /*设置前端每次请求的连接默认  加上/api  例如:  localhost:9896/api/user/getUses*/
    timeout: 5000
})

// request 拦截器
// 可以自请求发送前对请求做一些处理
// 比如统一加token，对请求参数统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    /*  设置请求验证登陆拦截 */
    let userJSON=sessionStorage.getItem("user")? JSON.parse(sessionStorage.getItem("user")) :null
    console.log("请求用户: "+userJSON)
    if(!userJSON){
        router.push('/login')
    }
    // config.headers['token'] = user.token;  // 设置请求头
    return config
}, error => {
    return Promise.reject(error)
});

// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        return res;
    },
    error => {
        console.log('err' + error) // for debug
        return Promise.reject(error)
    }
)


export default request