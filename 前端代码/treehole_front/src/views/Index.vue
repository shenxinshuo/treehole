<template>
	<div class="content">
		<el-row type="flex" justify="center">
			<el-col :span="18">
				<el-row>
					<el-col :span="18">
						<el-card class="box-card">
							<el-tabs v-model="activeName" @tab-click="handleClick">
								<el-tab-pane label="全部" name="first">
									<QuestionList question_type="all"></QuestionList>
								</el-tab-pane>
								<el-tab-pane label="职场" name="second">
									<QuestionList question_type="work"></QuestionList>
								</el-tab-pane>
								<el-tab-pane label="校园" name="third">
									<QuestionList question_type="school"></QuestionList>
								</el-tab-pane>
								<el-tab-pane label="情感" name="fourth">
									<QuestionList question_type="emotion"></QuestionList>
								</el-tab-pane>
								<el-tab-pane label="生活" name="fifth">
									<QuestionList question_type="life"></QuestionList>
								</el-tab-pane>
							</el-tabs>
						</el-card>
					</el-col>
					<el-col :span="6">
						<el-card class="box-card">
							<el-row type="flex" justify="center" style="text-align: center;">
								<el-col :span="12">
									<el-link :href="public_path+'/writeQuestion'" type="primary">
										<svg width="40" height="40" viewBox="0 0 40 40" class="GlobalWrite-navIcon" fill="currentColor">
											<g fill="#0084FF" fill-rule="evenodd">
												<circle cx="20" cy="20" r="20" opacity=".12"></circle>
												<path d="M23.487 10.463c1.896 0 2.583.193 3.277.555a3.824 3.824 0 0 1 1.607 1.573c.371.678.569 1.35.569 3.206v8.472c0 1.855-.198 2.527-.569 3.205a3.824 3.824 0 0 1-1.607 1.573c-.694.363-1.381.556-3.277.556h-6.96c-1.895 0-2.583-.193-3.276-.556a3.824 3.824 0 0 1-1.608-1.573c-.37-.678-.568-1.35-.568-3.205v-8.472c0-1.855.197-2.528.568-3.206.37-.678.915-1.21 1.608-1.573.693-.362 1.38-.556 3.277-.556h6.959zm0 2.08h-6.96c-1.407 0-1.836.081-2.273.31a1.72 1.72 0 0 0-.735.72c-.234.427-.317.847-.317 2.224v8.472c0 1.377.083 1.796.317 2.224.172.316.412.551.735.72.437.229.866.31 2.274.31h6.959c1.407 0 1.836-.081 2.274-.31a1.72 1.72 0 0 0 .735-.72c.234-.428.317-.847.317-2.224v-8.472c0-1.377-.083-1.797-.317-2.225a1.72 1.72 0 0 0-.735-.72c-.438-.228-.867-.309-2.274-.309zm-1.991 9.778v1.873h-5.955V22.32h5.955zm2.977-3.328v1.872h-8.932v-1.872h8.932zm0-3.33v1.873h-8.932v-1.872h8.932z"
												 fill-rule="nonzero"></path>
											</g>
										</svg>
										<br>
										<span>提问</span>
									</el-link>
								</el-col>
								<!-- 暂不开发 -->
								<!-- <el-col :span="12">
									<el-link href="" type="primary">
										<svg width="40" height="40" viewBox="0 0 40 40" class="GlobalWrite-navIcon" fill="currentColor">
											<g fill="none" fill-rule="evenodd">
												<circle cx="20" cy="20" r="20" fill="#F4C807" opacity=".12">
												</circle>
												<path d="M6 6h28v28H6z"></path>
												<path fill="#F4C807" d="M20.406 11.772l-2.172 2.176h-2.29c-1.438 0-1.875.085-2.322.324-.33.176-.575.422-.751.752-.24.448-.324.886-.324 2.326v7.12c0 1.44.085 1.878.324 2.326.176.33.421.576.75.752.421.225.834.314 2.08.323l7.35.001c1.438 0 1.876-.084 2.323-.324.33-.176.575-.422.751-.752.24-.448.324-.886.324-2.326v-4.905l2.172-2.175v7.08c0 1.94-.202 2.643-.58 3.352a3.95 3.95 0 0 1-1.643 1.645c-.708.379-1.41.58-3.346.58h-7.108c-1.936 0-2.639-.201-3.347-.58a3.95 3.95 0 0 1-1.642-1.645c-.378-.71-.58-1.413-.58-3.352v-7.12c0-1.94.202-2.643.58-3.352a3.95 3.95 0 0 1 1.642-1.645c.708-.379 1.41-.58 3.347-.58h4.462zm6.908-2.053c.384.116.747.338 1.168.759l.188.189c.42.421.642.785.758 1.17a1.98 1.98 0 0 1 0 1.163c-.116.385-.337.749-.758 1.17l-6.9 6.911c-.62.622-.827.81-1.078 1.004-.251.193-.496.34-.784.47-.288.131-.553.226-1.392.48l-1.088.332a1.303 1.303 0 0 1-1.625-1.629l.33-1.09c.255-.84.35-1.104.48-1.393.13-.29.277-.534.47-.785.193-.252.381-.46 1.001-1.081l6.9-6.911c.42-.421.784-.643 1.168-.76a1.97 1.97 0 0 1 1.162 0zm-3.204 4.096l-4.797 4.805c-.547.548-.709.723-.852.91-.112.146-.19.276-.265.443-.097.214-.175.44-.4 1.182l-.094.31.31-.095c.74-.225.965-.303 1.179-.4.167-.076.297-.154.442-.266.187-.143.361-.305.909-.853l4.797-4.805-1.23-1.23zm2.546-2.43c-.109.033-.23.11-.443.324l-.874.875 1.228 1.231.875-.876c.213-.213.29-.334.323-.444a.24.24 0 0 0 0-.153c-.033-.11-.11-.23-.323-.445l-.189-.188c-.213-.214-.334-.291-.443-.325a.238.238 0 0 0-.154 0z"
												 fill-rule="nonzero"></path>
											</g>
										</svg>
										<br>
										<span>写文章</span>
									</el-link>
								</el-col> -->
							</el-row>
						</el-card>
						<el-card class="box-card">
							<div v-html="notice"></div>
						</el-card>
						<el-card class="box-card">
							<p>用户名：{{user.username}}</p>
							<p>年龄：{{user.age}}</p>
							<p>地区省：{{user.area}}</p>
						</el-card>
						<el-card class="box-card">
							广告位
						</el-card>
						<el-card class="box-card">
							广告位
						</el-card>
						<div style="padding: 10px;color: grey;" class="foot">
							<el-link href="" type="info">联系我们</el-link>
							•
							<el-link href="https://www.baidu.com" type="info">有问题找百度</el-link>
						</div>
					</el-col>
				</el-row>
			</el-col>
		</el-row>

	</div>


</template>

<script>
	import QuestionList from '../components/article/QuestionList.vue'
	import request from '@/utils/request.js'
	export default {
		data() {
			return {
				activeName: "first",
				user: {
					username: "--",
					age: "--",
					area: "--",
					img: "E:/毕设/项目代码/前端代码/treehole_front/src/assets/img/login_bg.jpg"
				},
				public_path: process.env.VUE_APP_PUBLIC_PATH,
				notice: ""
			}
		},
		methods: {
			handleClick() {
				
			}
		},
		components: {
			QuestionList
		},
		mounted() {
			function getCookie(name) {
				var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
				if (arr = document.cookie.match(reg))
					return unescape(arr[2]);
				else
					return null;
			}
			var that = this;
			request.get("/getUserByID/"+getCookie("currentUserID"))
				.then(response => {console.log(response)
					this.user = response.data.data
					this.user.age = ( new Date().getFullYear() - new Date(response.data.data.birthday).getFullYear())
					console.log(new Date(response.data.data.birthday).toLocaleString())
				})
			//到后端获取公告数据
			request.get("/getNotice").then(response => {
				this.notice = response.data.data
			})
		}
	}
</script>

<style>
	.content {
		background: rgb(247, 246, 246);
		height: 100%;
		min-height: ;
		padding-top: 10px;
		padding-left: 0px;
		padding-bottom: 10px;
	}

	.box-card {
		margin: 5px;
	}

	el-link {}
</style>
