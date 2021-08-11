<template>
	<div>
		<div class="bg">
			<el-row type="flex" justify="center">
				<el-col :span="14">
					<el-card>
						<div>
							<!-- <el-image :src="require('E:/treehole/upload/'+user.img)" style="width: 200px; height: 200px;margin-right: 50px;"></el-image> -->
							<el-upload style="display: inline-block;margin-right: 50px;" class="avatar-uploader" action="#" :show-file-list="false"
							 :on-success="handleAvatarSuccess" :on-change="handleChange" :before-upload="beforeAvatarUpload">
								<img v-if="user.img" :src="require('E:/treehole/upload/'+user.img)" class="avatar">
								<i v-else class="el-icon-plus avatar-uploader-icon"></i>
							</el-upload>
							<div style="display: inline-block;margin-top: 20px;">
								<span style="font-size: xx-large;">{{user.username}}</span><br>
								<!-- 处理一下生日的显示 -->
								<span style="font-size: large;">{{user.birthday.split(" ")[0]}}</span><br>
								<el-link @click="toEditPersonalPage()" type="primary" style="font-size: medium;">编辑资料>></el-link>
								<br><br><br><br><br>
							</div>
						</div>

						<div style="width: 100%;">
							<el-tabs>
								<el-tab-pane label="我的问题" name="">
									<MyQuestion></MyQuestion>
								</el-tab-pane>
								<!-- 不开发了 -->
								<!-- <el-tab-pane label="我的文章" name="">
									<MyArticle></MyArticle>
								</el-tab-pane> -->
								<el-tab-pane label="我的回答" name="">
									<MyAnswer></MyAnswer>
								</el-tab-pane>
							</el-tabs>
						</div>
					</el-card>

				</el-col>
			</el-row>
		</div>
	</div>
</template>

<script>
	import head_image from '@/assets/img/userhead.png'
	import MyQuestion from '@/components/question/MyQuestion.vue'
	import MyArticle from '@/components/article/MyArticle.vue'
	import MyAnswer from '@/components/answer/MyAnswer.vue'
	import request from '@/utils/request.js'
	export default {
		data() {
			return {
				user: {
					username: '沈鑫烁',
					//head_img: head_image,
					birthday: '',
					sex: '男'
				},
				imageUrl: '',
				file: '',
				fileList: []
			}
		},
		components: {
			MyQuestion,
			MyArticle,
			MyAnswer
		},
		methods: {
			getCookie(name) {
				var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
				if (arr = document.cookie.match(reg))
					return unescape(arr[2]);
				else
					return null;
			},
			toEditPersonalPage() {
				this.$router.push("/editPersonalInfo/" + this.getCookie("currentUserID"))
			},
			handleChange(file, fileList) {
				this.fileList = fileList;
				this.file = file
				//console.log(file);
				//生成本地图片的URL，直接src显示，删除或移动后URL就失效了
				this.imageUrl = URL.createObjectURL(file.raw);
				//console.log(this.imageUrl);
				var fd = new FormData();
				var face = this.file;
				fd.append("file", face.raw);
				fd.append("user", JSON.stringify(this.user));
				request.post("/updateUserImg", fd).then(response => {
					console.log(response)
					location.reload()
				})
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
			handleAvatarSuccess(res, file) {
				this.imageUrl = URL.createObjectURL(file.raw);
				
			},
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
					this.user = response.data.data
					if (this.user.img == "") {
						this.user.img = "head.jpg"
					}
				})
		}
	}
</script>

<style>
	.bg {
		background: url(../../assets/img/login_bg.jpg) repeat fixed center;
		width: 98.7%;
		height: 100%;
		min-height: calc(100vh);
		/* position: fixed; */
		background-size: 100%;
		padding: 10px;
	}

	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
		display: inline-block;
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
		width: 200px;
		height: 200px;
		display: inline-block;
	}
</style>
