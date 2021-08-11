<template>
	<div>
		<div class="bg">
			<el-row type="flex" justify="center">
				<el-col :span="10">
					<el-card class="box-card" style="padding: 30px 10px;">
						<el-row type="flex" justify="center">
							<el-col :span="20">
								<el-form label-width="80px" :model="user">
									<el-form-item label="昵称">
										<el-input v-model="user.username"></el-input>
									</el-form-item>
									<el-form-item label="生日">
										<el-input v-model="user.birthday"></el-input>
									</el-form-item>
									<el-form-item label="电话">
										<el-input v-model="user.telephone"></el-input>
									</el-form-item>
									<el-form-item label="密码">
										<el-input v-model="user.password" show-password></el-input>
									</el-form-item>
									<el-form-item label="所在城市">
										<!-- <el-input v-model="user.area" ></el-input> -->
										<el-select v-model="user.area">
											<el-option value="广东"></el-option>
											<el-option value="北京"></el-option>
											<el-option value="上海"></el-option>
											<el-option value="天津"></el-option>
											<el-option value="重庆"></el-option>
											<el-option value="内蒙古"></el-option>
											<el-option value="广西"></el-option>
											<el-option value="西藏"></el-option>
											<el-option value="宁夏"></el-option>
											<el-option value="新疆"></el-option>
											<el-option value="香港特别行政区"></el-option>
											<el-option value="澳门特别行政区"></el-option>
											<el-option value="河北"></el-option>
											<el-option value="山西"></el-option>
											<el-option value="辽宁"></el-option>
											<el-option value="吉林"></el-option>
											<el-option value="黑龙江"></el-option>
											<el-option value="江苏"></el-option>
											<el-option value="浙江"></el-option>
											<el-option value="安徽"></el-option>
											<el-option value="福建"></el-option>
											<el-option value="江西"></el-option>
											<el-option value="山东"></el-option>
											<el-option value="河南"></el-option>
											<el-option value="湖北"></el-option>
											<el-option value="湖南"></el-option>
											<el-option value="海南"></el-option>
											<el-option value="四川"></el-option>
											<el-option value="贵州"></el-option>
											<el-option value="云南"></el-option>
											<el-option value="陕西"></el-option>
											<el-option value="甘肃"></el-option>
											<el-option value="青海"></el-option>
											<el-option value="台湾"></el-option>
										</el-select>
									</el-form-item>
								</el-form>
								<el-button type="primary" style="margin-left: 50px;" @click="UpdateUser()">修改</el-button>
								<el-button type="text" @click="handleCancle()">返回</el-button>
							</el-col>
						</el-row>
					</el-card>
				</el-col>
			</el-row>
		</div>
	</div>
</template>

<script>
	import request from '@/utils/request.js'
	export default {
		data() {
			return {
				user: {
					id: this.$route.params.id,
					username: '',
					birthday: '',
					telephone: '',
					password: '',
					area: '',

				},
				oldUsername: ''
			}
		},
		methods: {
			handleCancle() {
				window.history.back(-1)
			},
		    getCookie(name) {
				var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
				if (arr = document.cookie.match(reg))
					return unescape(arr[2]);
				else
					return null;
			},
			delCookie(name) {
				var exp = new Date();
				exp.setTime(exp.getTime() - 1);
				var cval = this.getCookie(name);
				if (cval != null)
					document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
			},
			UpdateUser() {
				//修改之前参数检验
				if (this.user.username.length == 0) {
					this.$message.error("昵称不能为空")
					return
				} else if (this.user.username.length > 10) {
					this.$message.error("昵称长度不能超过10个字符")
					return
				}
				//创建正则表达式
				let re = /\d{4}-\d{1,2}-\d{1,2}/
				if (!re.test(this.user.birthday)) {
					this.$message.error("生日日期格式有错误，请检查")
					return
				}
				let re2 = /(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}/
				if (!re2.test(this.user.telephone)) {
					this.$message.error("请输入正确的手机号")
					return
				}
				//密码不做检验，不为空即可
				if (this.user.password.length == 0) {
					this.$message.error("密码不能为空")
					return
				}
				
				request.post("/updateUser", {
					id: this.user.id,
					username: this.user.username,
					birthday: this.user.birthday,
					telephone: this.user.telephone,
					password: this.user.password,
					area: this.user.area,
				}).then(response => {
					if (response.data.status== 200) {
						this.$message.success("个人信息修改成功")
						if (this.user.username != this.oldUsername) {
							this.$message.warning("修改了用户名，请重新登录")
							this.delCookie("currentUserID")
							this.delCookie("token")
							this.$router.push("/user/login")
						}
					}else {
						this.$message.error("个人信息修改失败")
					}
				})
			}
		},
		mounted() {
			function getCookie(name) {
				var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
				if (arr = document.cookie.match(reg))
					return unescape(arr[2]);
				else
					return null;
			}
			request.get("/getUserByID/" + getCookie("currentUserID"))
				.then(response => {
					console.log(response)
					this.user = response.data.data
					this.user.birthday = new Date(this.user.birthday).getFullYear() + "-" +
						(new Date(this.user.birthday).getMonth() + 1) + "-" +
						new Date(this.user.birthday).getMonth()
					this.oldUsername = response.data.data.username
				})
		}
	}
</script>

<style>
	.bg {
		background: url(../../assets/img/login_bg.jpg) repeat fixed center;
		width: 100%;
		height: 100%;
		position: fixed;
		background-size: 100%;
		padding: 10px;
	}
</style>
