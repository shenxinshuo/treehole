<template>
	<div class="bg">
		<el-row type="flex" justify="center">
			<el-col :span="10">
				<el-card class="box-card">
					<div style="width: 100%;text-align: center;">
						<h2>用户注册</h2>
					</div>
					<br>
					<el-row type="flex" justify="center">
						<el-col :span="20">
							<el-form label-width="80px" :model="user">
								<el-form-item label="昵称">
									<el-input v-model="user.username" placeholder="长度不能超过10个字符"></el-input>
								</el-form-item>
								<el-form-item label="生日">
									<el-input v-model="user.birthday" placeholder="日期格式:yyyy-MM-dd,例如:1998-10-09"></el-input>
								</el-form-item>
								<!-- <el-form-item label="所在城市">
							    <el-input v-model="user.city"></el-input>
							  </el-form-item> -->
								<el-form-item label="所在城市">
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
								<el-form-item label="联系方式">
									<el-input v-model="user.telephone"></el-input>
								</el-form-item>
								<el-form-item label="密码">
									<el-input v-model="user.password"></el-input>
								</el-form-item>
								<el-form-item label="确认密码">
									<el-input v-model="confirmPassword"></el-input>
								</el-form-item>
								<el-form-item label="头像">
									<el-upload
									  class="avatar-uploader"
									  action="#"
									  :show-file-list="false"
									  :on-success="handleAvatarSuccess"
									  :on-change="handleChange"
									  :before-upload="beforeAvatarUpload">
									  <img v-if="imageUrl" :src="imageUrl" class="avatar">
									  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
									</el-upload>
								</el-form-item>
							</el-form>
							<div style="width: 100%; text-align: center;">
								<el-button type="primary" @click="handleRegister()">创建</el-button>
								<el-button type="infor" @click="backLogin()">返回登录</el-button>
							</div>
						</el-col>
					</el-row>
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
				user: {
					id: '',
					username: '',
					password: '',
					birthday: '',
					area: '',
					telephone: ''
				},
				confirmPassword: '',
				imageUrl: '',
				fileList: [],
				file: ''
			}
		},
		methods: {
		      handleAvatarSuccess(res, file) {
		        this.imageUrl = URL.createObjectURL(file.raw);
		      },
			  handleChange(file, fileList){
				this.fileList = fileList;
				this.file = file
				  //console.log(file);
				  //生成本地图片的URL，直接src显示，删除或移动后URL就失效了
				  this.imageUrl = URL.createObjectURL(file.raw);
				  //console.log(this.imageUrl);
			  },
		      beforeAvatarUpload(file) {
		        const isJPG = file.type === 'image/jpeg';
		        const isLt2M = file.size / 1024 / 1024 < 2;
		
		        if (!isJPG) {
		          this.$message.error('上传头像图片只能是 JPG 格式!');
		        }
		        if (!isLt2M) {
		          this.$message.error('上传头像图片大小不能超过 2MB!');
		        }
		        return isJPG && isLt2M;
		      },
			  backLogin() {
				  this.$router.push('/user/login')
			  },
			  handleRegister() {
				  //注册之前参数检验
				  if(this.user.username.length == 0) {
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
				  if (this.confirmPassword != this.user.password) {
					  this.$message.error("确认密码跟密码不一致，请检查")
					  return 
				  }
				  var fd = new FormData();
				  var face = this.file;
				  fd.append("file",face.raw);
				  fd.append("user",JSON.stringify(this.user));
				  // fd.append("username",this.user.username)
				  // fd.append("password",this.user.password)
				  //console.log(fd.user)
				  request.post("/addUser", fd).then(response => {
					  if (response.data.status == 200) {
						  this.$message.success("注册成功")
						  this.$router.push("/user/login")
					  } else if (response.data.message == "用户名已被注册过"){
						  this.$message.error("用户名已被注册过")
					  } else {
						  this.$message.error("注册失败")
					  }
				  })
			  }
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
		/* text-align: center; */
	}
	.avatar-uploader .el-upload {
	    border: 1px dashed #d9d9d9;
	    border-radius: 6px;
	    cursor: pointer;
	    position: relative;
	    overflow: hidden;
	  }
	  .avatar-uploader .el-upload:hover {
	    border-color: #409EFF;
	  }
	  .avatar-uploader-icon {
	    font-size: 28px;
	    color: #8c939d;
	    width: 50px;
	    height: 50px;
	    line-height: 50px;
	    text-align: center;
	  }
	  .avatar {
	    width: 50px;
	    height: 50px;
	    display: block;
	  }
</style>
