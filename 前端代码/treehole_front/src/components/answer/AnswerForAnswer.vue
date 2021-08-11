<template>
	<div >
		<div v-if="this.pageInfo.total != 0">
			<!-- 遍历打印回答 -->
			<el-row type="flex" justify="center" v-for="(answer,index) in answers">
				<el-col :span="22">
					<el-divider class="fengexian"></el-divider>
					<div style="display: inline-block;margin-left: 5px;width: 80%;">
						<span class="answer_username">{{answer.user.username}}</span>	
						<!-- 回复 -->
						<!-- <span class="answer_username">{{username2}}</span> -->
						<span style="float: right;">{{answer.created_time}}</span>
						<br>
						<p>
							<!-- {{answer.content}} -->
							<div v-html="answer.content"></div>
						</p>
						<el-link type="primary" disabled>回复</el-link>
						&nbsp;
						<el-link type="primary" @click="showAnswerComplain(answer.id)">举报</el-link>
						&nbsp;
						<el-link type="text" v-if="answer.uid == currentUserID" @click="deleteAnswer(answer.id)">删除</el-link>
					</div>
				</el-col>
			</el-row>
			
			<div class="block" style="margin-left: 25px;width: 80%;">
				<el-divider class="fengexian"></el-divider>
				<el-pagination 
				@size-change="handleSizeChange"
				 @current-change="handleCurrentChange" 
				 :current-page.sync="pageNum"
				 :page-size="pageSize" 
				 layout="prev, pager, next, jumper" 
				 :total="total">
				</el-pagination>
			</div>
			
		</div>
		<div v-else align="center">
			<p style="color: #909399">暂无回复</p>
		</div>
		
		<!-- 举报回复dialog -->
		<el-dialog title="举报" :visible.sync="complainAnswerVisible">
			<el-input type="textarea" rows="6" v-model="comp" placeholder="写下举报的理由,长度不要超过255"></el-input>
			<br><br>
			<el-button type="danger" @click="complainAnswer()">举报</el-button>
		</el-dialog>
	</div>
	
</template>

<script>
	import avatar from '@/assets/img/userhead.png'
	import a2 from '@/assets/img/login_bg.jpg'
	import request from '@/utils/request.js'
	export default {
		data() {
			return {
				pageInfo: {
					
				},
				pageNum: 1,
				pageSize: 10,
				total: 0,
				user: {
					username: "陈炫明",
					img: avatar
				},
				answers: [
					
				],
				username2: "陈壮彬",
				user2_img: a2,
				currentUserID: this.getCookie("currentUserID"),
				complainAnswerVisible: false,
				comp: '',
				complainAnswerID:0//待举报answerID
			}
		},
		methods:{
			handleSizeChange() {
			
			},
			handleCurrentChange() {
				request.get("/getAnswerByAnswerID/"+this.answerID+"?pageNum=" + this.pageNum + "&pageSize=" + this.pageSize)
				.then(response => {
					that.pageInfo = response.data.data
					that.answers = response.data.data.list
				})
			},
			deleteAnswer(aid) {
				this.$confirm("将永久删除该回答，确定吗？","确认删除", {
					confirmButtonText: '确认删除',
					cancelButtonText: '取消',
					type: 'warring'
				}).then(()=>{
					request.post("/deleteAnswer/"+aid).then(response => {
						if (response.data.status == 200) {
							this.$message.success("删除成功")
							location.reload()
						} else {
							this.$message.error("删除失败")
						}
					})
				}).catch(()=> {
					this.$message.info("取消删除")
				})
			},
			showAnswerComplain(aid) {
				this.complainAnswerVisible = true
				this.complainAnswerID = aid
			},
			complainAnswer() {
				if (this.comp.length == 0) {
					this.$message.error("举报内容不能为空")
					return
				}
				if (this.comp.length > 255) {
					this.$message.error("请将字数控制在255字以内")
					return
				}
				request.post("/addComplain", {
					aid: this.complainAnswerID,
					uid: this.getCookie("currentUserID"),
					reason: this.comp
				}).then(response => {
					if (response.data.status == 200) {
						this.$message.success("举报回答成功")
						this.complainAnswerVisible = false
						location.reload()
					}
				})
			},
			getCookie(name) {
				var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
				if (arr = document.cookie.match(reg))
					return unescape(arr[2]);
				else
					return null;
			}
		},
		props: ['answerID'],
		mounted() {
			var that = this
			request.get("/getAnswerByAnswerID/"+this.answerID+"?pageNum=1&pageSize=10")
			.then(response => {
				that.pageInfo = response.data.data
				that.answers = response.data.data.list
				that.total = response.data.data.total
				//that.user = response.data.data.list.user
				//console.log(response)
			})
		}
	}
</script>

<style>
	.answer_username {
		font-weight: bold;
	}
	.fengexian {
		margin: 10px 0px;
	}
</style>
