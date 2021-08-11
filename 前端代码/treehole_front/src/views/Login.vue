<template>
	<div>
		<div style="" class="login_bg">
			<el-col :span="6" :offset="9" class="login_box">
				<el-card class="box-card">
					<p>用户名密码登录</p>
					<div class="login_input">
						<form action="">
							<el-input v-model="user.username" placeholder="用户名"></el-input><br><br>
							<el-input v-model="user.password" placeholder="密码" show-password></el-input>
						</form>
						<br><br>
						<el-button type="primary" style="width: 100%;" @click="handleLogin">登录</el-button>
						<p>
							<el-link>忘记密码</el-link>
							&nbsp;&nbsp;
							<el-link href="/user/register">立即注册</el-link>
						</p>
					</div>
				</el-card>
			</el-col>
			
		</div>
	</div>
</template>

<script>
	import request from '@/utils/request.js'
	import Head from '@/components/grobal/Head.vue'
	import eventBus from '@/utils/EventBus.js'
	export default {
		data() {
			return {
				user: {
					username: "",
					password: ""
				}
			}
		},
		methods: {
			handleLogin() {
				request.post(
					'/user/login',
					{
						username: this.user.username,
						password: this.user.password,
					}
				).then(response => {
					//console.log(response)
					if (response.data.message == "用户不存在") {
						this.$message.error("账号不存在")
					}
					if (response.data.message == "密码错误") {
						this.$message.error("密码错误")
					}
					if (response.data.message == "认证错误") {
						this.$message.error("认证失败")
					}
					if (response.data.message == "登录成功") {
						this.$message.success("登录成功")
						console.log(response.data.data)
						// window.document.cookie = "token="+response.data.data.token
						// window.document.cookie = "currentUserID="+response.data.data.currentUser.id
						this.setCookie("token", response.data.data.token)
						this.setCookie("currentUserID", response.data.data.currentUser.id)
						//console.log(this.$parent)
						this.$parent.seen = true
						eventBus.$emit("loginSuccess",'aaa')
						this.$router.push("/")
					}
				})
				// if (this.user.username == "xinshuo" && this.user.password == "123456") {
				// 	this.$router.push({
				// 		path: "/"
				// 	});
				// 	this.$parent.seen = true;
				// 	//sessionStorage.setItem("user",this.user)
				// } else {
				// 	this.$message.error("账号密码错误");
				// }
			},
			setCookie(name, value) {
				//半天token过期
				var Days = 0.5;
				var exp = new Date();
				exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
				document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
			}
		},
		created() {
			this.$parent.seen = false;
		}
	}
</script>

<style>
.login_bg {
	background: url(../assets/img/login_bg.jpg) repeat fixed center;
	width: 100%;
	height: 100%;
	position: fixed;
	background-size: 100%;
	text-align: center;
}
.login_box {
	margin-top: 180px;
	margin-left: ;
}
.login_input input {
	
}
</style>
