import axios from 'axios'
import { Message } from 'element-ui'
import router from '../router'

axios.default.withCredentials = true //允许axiso携带cookie
const request = axios.create({
	baseURL: 'http://localhost:8081/treehole',
	timeout: 12000,
	// withCredentials: true,
	headers: {
		'Content-Type': 'application/json',
		'Accept': 'application/json'
	}
})


//请求拦截器
request.interceptors.request.use(function(config) {
	//发送请求之前做些处理
	if (getCookie("token") != null) {
		console.log("带上token");
		config.headers['Token'] = getCookie("token");
	}
	return config;
},function(error) {
	//读请求错误做些什么
	return Promise.reject(error)
})

//响应拦截器
request.interceptors.response.use(
	response => {
		//console.log(response)
		if (response.data.status == 403) {
			router.push("/user/login");
			Message.error("请先登录")
		}
		if ( response.data.code == 500000) {
			Message.error("token认证失败，请尝试重新登录")
		}
		// if (response.data.message== "请先登录") {
		// 	console.log("登录先")
		// 	Message.error({
		// 		message: response.data.message
		// 	})
		// 	router.push("/user/login")
		// }
		
		// console.log(response)
		return response;
	},
	function(error) {
		// console.log(error)
	}
)

export default request


function getCookie(name) {
	var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
	if (arr = document.cookie.match(reg))
		return unescape(arr[2]);
	else
		return null;
}