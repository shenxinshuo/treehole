<template>
	<div>
		<el-row type="flex" justify="center">
			<el-col :span="16">
				<el-card>
					<div v-if="this.complain.qid != 0">
						<!-- 举报问题模块 -->
						问题描述：{{question.title}} <br>
						举报理由：{{complain.reason}} <br>
						举报者：“{{this.user.username}}”<br>
						举报时间：“{{this.complain.created_time}}”
						<el-divider></el-divider>
						<el-button type="infor" @click="dealComplain(false)">忽略</el-button>
						<el-button type="danger" @click="dealComplain(true)">删除该问题</el-button>
					</div>
					<div v-if="this.complain.aid != 0">
						<!-- 举报回答模块 -->
						评论详情：“{{this.answer.content}}”<br>
						举报者：“{{this.user.username}}”<br>
						举报时间：“{{this.complain.created_time}}”
						<el-divider></el-divider>
						<el-button type="infor" @click="dealComplain(false)">忽略</el-button>
						<el-button type="danger" @click="dealComplain(true)">删除该回答</el-button>
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
				complain: {
					id: this.$route.params.id
				},
				question:{
					
				},
				answer: {
					
				},
				user: {
					
				}
			}
		},
		methods:{
			dealComplain(result) {
				//true:删除，false：忽略
				request.post("/dealComplain/"+result,{
					id:this.complain.id,
					qid:this.complain.qid,
					aid:this.complain.aid,
					uid:this.complain.uid,
					reason:this.complain.reason,
					created_time:this.complain.created_time,
					updated_time:this.complain.updated_time,
					deal_flag:this.complain.deal_flag
				}).then(response => {
					console.log(response)
					if (response.data.status == 200) {
						this.$message.success("举报处理成功")
						this.$router.push("/admin/index")
					}
				})
			}
		},
		mounted() {
			request.get("/getComplainByID/"+this.complain.id).then(response => {
				this.complain = response.data.data;
				if (this.complain.qid != 0) {
					request.get("/getQuestionByID/"+this.complain.qid).then(response => {
						this.question = response.data.data
					})
				}
				if (this.complain.aid != 0) {
					request.get("/getAnswerByID/"+this.complain.aid).then(response => {
						this.answer = response.data.data
					})
				}
				request.get("/getUserByID/"+this.complain.uid).then(response=>{
					this.user = response.data.data
				})
			})
		},
	}
</script>

<style>
</style>
