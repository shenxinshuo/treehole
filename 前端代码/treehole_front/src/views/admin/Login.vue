<template>
	<div style="margin-top: 50px;">
		<el-row type="flex" justify="center">
			<el-col :span="5">
				<el-card class="box-card">
					<h2>管理员登录</h2>
					<br>
					<div class="login_input">
						<form action="">
							<el-input v-model="admin.name" placeholder="用户名"></el-input><br><br>
							<el-input v-model="admin.password" placeholder="密码" show-password></el-input>
						</form>
						<br><br>
						<el-button type="primary" style="width: 100%;" @click="handleLogin">登录</el-button>
					</div>
				</el-card>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	import request from '@/utils/request.js'
	export default {
		data() {
			return {
				admin:{
					name: '',
					password: ''
				}
			}
		},
		methods:{
			setCookie(name, value) {
				var Days = 30;
				var exp = new Date();
				exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
				document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
			},
			handleLogin() {
				request.post("/admin/login", {
					name: this.admin.name,
					password: this.admin.password
				}).then(response => {
					if (response.data.status == 204) {
						this.$message.error("账号密码错误")
					}
					if (response.data.status == 200) {
						this.$message.success("登录成功")
						this.setCookie("currentAdmin", response.data.data.name)
						this.$router.push("/admin/index")
					}
				})
			}
		}
	}
</script>

<style>
</style>
