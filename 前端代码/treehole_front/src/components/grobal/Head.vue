<template>
	<div>
		<el-row type="flex" justify="center">
			<el-col :span="18">
				<el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
					<el-menu-item index="1">
						<el-link :href="public_path"><span style="font-size: xx-large;">树洞</span></el-link>
					</el-menu-item>
					<el-menu-item index="2">
						<el-link :href="public_path"><span style="font-size: large;">首页</span></el-link>
					</el-menu-item>
					<el-menu-item index="3">
						<el-link href=""><span style="font-size: large;">发现</span></el-link>
					</el-menu-item>
					<el-menu-item index="4">
						<el-link href=""><span style="font-size: large;">等你来答</span></el-link>
					</el-menu-item>
					<el-menu-item>
						<!-- <el-input v-model="search" placeholder="输入要查找的内容" suffix-icon="el-icon-search"></el-input> -->
						<el-input v-model="search" placeholder="请输入要查找的内容" class="input-with-select">
							 <el-button slot="append" icon="el-icon-search" @click="handleSearch()"></el-button>
						</el-input>
					</el-menu-item>
					<!-- <div style="margin-top: 11px;margin-right:30px; float: right; height: 40px;">
						<el-avatar shape="square" :src="head_img"></el-avatar>
					</div> -->
					<div v-if="isLogin">
						<!-- 已登录 -->
						<el-popover style="margin-top: 11px;margin-right:30px; float: right;" placement="bottom" width="400px" trigger="click">
							<!-- <el-avatar slot="reference"  shape="square" :src="img"></el-avatar> -->
							<el-avatar slot="reference"  shape="square" :src="require('E:/treehole/upload/'+this.user.img)"></el-avatar>
							<div style="">
								<el-link :href="public_path+'/user/center'">个人中心</el-link><br>
								<el-link @click="handleLogout()">退出</el-link>
							</div>	
						</el-popover>
					</div>
					<div v-else>
						<!-- 未登录 -->
						<el-button type="button" style="margin-top: 11px;margin-right:30px; float: right;" @click="toLogin()">登录/注册</el-button>
					</div>
					
				</el-menu>
				<div class="line"></div>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	import avatar from '@/assets/img/login_bg.jpg'
	import '../../utils/functionUtil.js'
	import eventBus from '@/utils/EventBus.js'
	import request from '@/utils/request.js'
	export default {
		data() {
			return {
				search: '',
				//head_img: 'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',
				activeIndex: '',
				img: avatar,
				user: {
					username: "沈鑫烁",
					age: "23",
					area: "广东省",
					// img: require('E:/treehole/upload/laochen.jpg')
				},
				public_path: process.env.VUE_APP_PUBLIC_PATH,
				isLogin: false
			}
		},
		methods: {
			handleSelect() {

			},
			handleSearch() {
				if (this.search.length == 0) {
					this.$message.error("请输入要查找的内容")
					return
				} 
				if (this.search.length > 40) {
					this.$message.error("输入内容长度不能超过40字")
					return
				}
				this.$router.push({path:"/searchPage",query:{search: this.search}})
			},
			handleClick() {
				alert(this.isLogin)
			},
			toLogin() {
				this.$router.push("/user/login")
			},
			handleLogout() {
				var that = this
				request.get('/user/logout')
					.then(response => {
						that.delCookie('token')
						that.delCookie("currentUserID")
						that.isLogin = false
					})
			},
			delCookie(name) {
				var exp = new Date();
				var that = this
				exp.setTime(exp.getTime() - 1);
				var cval = that.getCookie(name);
				if (cval != null)
					document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
			},
			getCookie(name) {
				var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
				if (arr = document.cookie.match(reg))
					return unescape(arr[2]);
				else
					return null;
			}
		},
		created() {
			//this.user = sessionStorage.getItem("user");
		},
		mounted() {
			function getCookie(name) {
				var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
				if (arr = document.cookie.match(reg))
					return unescape(arr[2]);
				else
					return null;
			}
			var that = this
			// eventBus.$on("loginSuccess",(message) => {
			// 	//that.isLogin = true
			// })
			if(getCookie("token") == null) {
				this.isLogin = false 
			} else {
				this.isLogin = true
				request.get("/getUserByID/"+getCookie("currentUserID")).then(response => {
					//console.log(response)
					this.user = response.data.data
					if (this.user.img == "") {
						this.user.img = "head.jpg"
					}
				})
			}
		}
	}
</script>

<style>
</style>
