<template>
	<div>
		<el-row type="flex" justify="center">
			<el-col :span="16">
				<div v-if="isDelete">
					该问题已被删除或不存在
				</div>
				<div v-else>
					<el-card class="box-card">
						<h3>{{question.title}}</h3>
						作者 ：{{user.username}}
						<span v-html="kongge"></span>
						{{question.created_time}}
						<br><br>
						<p>
							<div v-html="question.description"></div>
						</p>
						<el-row>
							<el-button type="primary" plain @click="answerVisible = true">写回答</el-button>
							<!--未点赞时显示-->
							<el-button v-if="userLike.status == 0" type="info" plain @click="giveALikeForQuestion()">
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-hand-thumbs-up"  viewBox="0 0 16 16">
								  <path fill-rule="evenodd" d="M6.956 1.745C7.021.81 7.908.087 8.864.325l.261.066c.463.116.874.456 1.012.965.22.816.533 2.511.062 4.51a9.84 9.84 0 0 1 .443-.051c.713-.065 1.669-.072 2.516.21.518.173.994.681 1.2 1.273.184.532.16 1.162-.234 1.733.058.119.103.242.138.363.077.27.113.567.113.856 0 .289-.036.586-.113.856-.039.135-.09.273-.16.404.169.387.107.819-.003 1.148a3.163 3.163 0 0 1-.488.901c.054.152.076.312.076.465 0 .305-.089.625-.253.912C13.1 15.522 12.437 16 11.5 16v-1c.563 0 .901-.272 1.066-.56a.865.865 0 0 0 .121-.416c0-.12-.035-.165-.04-.17l-.354-.354.353-.354c.202-.201.407-.511.505-.804.104-.312.043-.441-.005-.488l-.353-.354.353-.354c.043-.042.105-.14.154-.315.048-.167.075-.37.075-.581 0-.211-.027-.414-.075-.581-.05-.174-.111-.273-.154-.315L12.793 9l.353-.354c.353-.352.373-.713.267-1.02-.122-.35-.396-.593-.571-.652-.653-.217-1.447-.224-2.11-.164a8.907 8.907 0 0 0-1.094.171l-.014.003-.003.001a.5.5 0 0 1-.595-.643 8.34 8.34 0 0 0 .145-4.726c-.03-.111-.128-.215-.288-.255l-.262-.065c-.306-.077-.642.156-.667.518-.075 1.082-.239 2.15-.482 2.85-.174.502-.603 1.268-1.238 1.977-.637.712-1.519 1.41-2.614 1.708-.394.108-.62.396-.62.65v4.002c0 .26.22.515.553.55 1.293.137 1.936.53 2.491.868l.04.025c.27.164.495.296.776.393.277.095.63.163 1.14.163h3.5v1H8c-.605 0-1.07-.081-1.466-.218a4.82 4.82 0 0 1-.97-.484l-.048-.03c-.504-.307-.999-.609-2.068-.722C2.682 14.464 2 13.846 2 13V9c0-.85.685-1.432 1.357-1.615.849-.232 1.574-.787 2.132-1.41.56-.627.914-1.28 1.039-1.639.199-.575.356-1.539.428-2.59z"/>
								</svg>
							     {{likeQuestionCount}}
							</el-button>
							<!--点赞过显示-->
							<el-button v-if="userLike.status == 1" type="info" plain @click="unLikeForQuestion()">
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="blue" class="bi bi-hand-thumbs-up"  viewBox="0 0 16 16">
								  <path  fill-rule="evenodd" d="M6.956 1.745C7.021.81 7.908.087 8.864.325l.261.066c.463.116.874.456 1.012.965.22.816.533 2.511.062 4.51a9.84 9.84 0 0 1 .443-.051c.713-.065 1.669-.072 2.516.21.518.173.994.681 1.2 1.273.184.532.16 1.162-.234 1.733.058.119.103.242.138.363.077.27.113.567.113.856 0 .289-.036.586-.113.856-.039.135-.09.273-.16.404.169.387.107.819-.003 1.148a3.163 3.163 0 0 1-.488.901c.054.152.076.312.076.465 0 .305-.089.625-.253.912C13.1 15.522 12.437 16 11.5 16v-1c.563 0 .901-.272 1.066-.56a.865.865 0 0 0 .121-.416c0-.12-.035-.165-.04-.17l-.354-.354.353-.354c.202-.201.407-.511.505-.804.104-.312.043-.441-.005-.488l-.353-.354.353-.354c.043-.042.105-.14.154-.315.048-.167.075-.37.075-.581 0-.211-.027-.414-.075-.581-.05-.174-.111-.273-.154-.315L12.793 9l.353-.354c.353-.352.373-.713.267-1.02-.122-.35-.396-.593-.571-.652-.653-.217-1.447-.224-2.11-.164a8.907 8.907 0 0 0-1.094.171l-.014.003-.003.001a.5.5 0 0 1-.595-.643 8.34 8.34 0 0 0 .145-4.726c-.03-.111-.128-.215-.288-.255l-.262-.065c-.306-.077-.642.156-.667.518-.075 1.082-.239 2.15-.482 2.85-.174.502-.603 1.268-1.238 1.977-.637.712-1.519 1.41-2.614 1.708-.394.108-.62.396-.62.65v4.002c0 .26.22.515.553.55 1.293.137 1.936.53 2.491.868l.04.025c.27.164.495.296.776.393.277.095.63.163 1.14.163h3.5v1H8c-.605 0-1.07-.081-1.466-.218a4.82 4.82 0 0 1-.97-.484l-.048-.03c-.504-.307-.999-.609-2.068-.722C2.682 14.464 2 13.846 2 13V9c0-.85.685-1.432 1.357-1.615.849-.232 1.574-.787 2.132-1.41.56-.627.914-1.28 1.039-1.639.199-.575.356-1.539.428-2.59z"/>
								</svg>
							     {{likeQuestionCount}}
							</el-button>
							<el-button type="danger" plain @click="complainQuestionVisible = true">举报</el-button>
							<el-button type="warning" plain v-if="question.uid == currentUserID" @click="deleteQuestion(question.id)">删除</el-button>
						</el-row>
					</el-card>
					<el-card class="box-card">
						<!-- <el-divider></el-divider> -->
						{{answerForQuestionTotal}}个回答
						<el-divider></el-divider>
						<div v-for="(answer,index) in answers">
							<el-row>
								<!-- {{answer}} -->
								<el-avatar size="medium" :src="answer.user.img" shape="square"></el-avatar>
								<div style="display: inline-block;margin-left: 10px;">
									<span class="answer_username">{{answer.username}}</span>
									<br>
									<span class="time">{{answer.created_time}}</span>
								</div>
							</el-row>
							<p style="color: grey;">{{answer.likeAnswerCount}}个人赞同了该回答</p>
							<!-- {{answer.content}} -->
							<div v-html="answer.content"></div>
							<br><br>
							<el-row>
								<el-button type="primary" plain @click="showAddAnswerForAnswer(answer.id)">写回答</el-button>
								<el-button type="success" plain @click="seeAnswer(index)">查看回复({{answer.total}})</el-button>
								<!-- 未点赞时显示 -->
								<el-button v-if="!answer.likeThisAnswer" type="info" plain  @click="giveALikeForAnswer(answer.id,index)">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-hand-thumbs-up"  viewBox="0 0 16 16">
									  <path fill-rule="evenodd" d="M6.956 1.745C7.021.81 7.908.087 8.864.325l.261.066c.463.116.874.456 1.012.965.22.816.533 2.511.062 4.51a9.84 9.84 0 0 1 .443-.051c.713-.065 1.669-.072 2.516.21.518.173.994.681 1.2 1.273.184.532.16 1.162-.234 1.733.058.119.103.242.138.363.077.27.113.567.113.856 0 .289-.036.586-.113.856-.039.135-.09.273-.16.404.169.387.107.819-.003 1.148a3.163 3.163 0 0 1-.488.901c.054.152.076.312.076.465 0 .305-.089.625-.253.912C13.1 15.522 12.437 16 11.5 16v-1c.563 0 .901-.272 1.066-.56a.865.865 0 0 0 .121-.416c0-.12-.035-.165-.04-.17l-.354-.354.353-.354c.202-.201.407-.511.505-.804.104-.312.043-.441-.005-.488l-.353-.354.353-.354c.043-.042.105-.14.154-.315.048-.167.075-.37.075-.581 0-.211-.027-.414-.075-.581-.05-.174-.111-.273-.154-.315L12.793 9l.353-.354c.353-.352.373-.713.267-1.02-.122-.35-.396-.593-.571-.652-.653-.217-1.447-.224-2.11-.164a8.907 8.907 0 0 0-1.094.171l-.014.003-.003.001a.5.5 0 0 1-.595-.643 8.34 8.34 0 0 0 .145-4.726c-.03-.111-.128-.215-.288-.255l-.262-.065c-.306-.077-.642.156-.667.518-.075 1.082-.239 2.15-.482 2.85-.174.502-.603 1.268-1.238 1.977-.637.712-1.519 1.41-2.614 1.708-.394.108-.62.396-.62.65v4.002c0 .26.22.515.553.55 1.293.137 1.936.53 2.491.868l.04.025c.27.164.495.296.776.393.277.095.63.163 1.14.163h3.5v1H8c-.605 0-1.07-.081-1.466-.218a4.82 4.82 0 0 1-.97-.484l-.048-.03c-.504-.307-.999-.609-2.068-.722C2.682 14.464 2 13.846 2 13V9c0-.85.685-1.432 1.357-1.615.849-.232 1.574-.787 2.132-1.41.56-.627.914-1.28 1.039-1.639.199-.575.356-1.539.428-2.59z"/>
									</svg>
									{{answer.likeAnswerCount}}
								</el-button>
								<!-- 点过赞时显示 -->
								<el-button v-if="answer.likeThisAnswer" type="info" plain  @click="unLikeForAnswer(answer.id, index)">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="blue" class="bi bi-hand-thumbs-up"  viewBox="0 0 16 16">
									  <path fill-rule="evenodd" d="M6.956 1.745C7.021.81 7.908.087 8.864.325l.261.066c.463.116.874.456 1.012.965.22.816.533 2.511.062 4.51a9.84 9.84 0 0 1 .443-.051c.713-.065 1.669-.072 2.516.21.518.173.994.681 1.2 1.273.184.532.16 1.162-.234 1.733.058.119.103.242.138.363.077.27.113.567.113.856 0 .289-.036.586-.113.856-.039.135-.09.273-.16.404.169.387.107.819-.003 1.148a3.163 3.163 0 0 1-.488.901c.054.152.076.312.076.465 0 .305-.089.625-.253.912C13.1 15.522 12.437 16 11.5 16v-1c.563 0 .901-.272 1.066-.56a.865.865 0 0 0 .121-.416c0-.12-.035-.165-.04-.17l-.354-.354.353-.354c.202-.201.407-.511.505-.804.104-.312.043-.441-.005-.488l-.353-.354.353-.354c.043-.042.105-.14.154-.315.048-.167.075-.37.075-.581 0-.211-.027-.414-.075-.581-.05-.174-.111-.273-.154-.315L12.793 9l.353-.354c.353-.352.373-.713.267-1.02-.122-.35-.396-.593-.571-.652-.653-.217-1.447-.224-2.11-.164a8.907 8.907 0 0 0-1.094.171l-.014.003-.003.001a.5.5 0 0 1-.595-.643 8.34 8.34 0 0 0 .145-4.726c-.03-.111-.128-.215-.288-.255l-.262-.065c-.306-.077-.642.156-.667.518-.075 1.082-.239 2.15-.482 2.85-.174.502-.603 1.268-1.238 1.977-.637.712-1.519 1.41-2.614 1.708-.394.108-.62.396-.62.65v4.002c0 .26.22.515.553.55 1.293.137 1.936.53 2.491.868l.04.025c.27.164.495.296.776.393.277.095.63.163 1.14.163h3.5v1H8c-.605 0-1.07-.081-1.466-.218a4.82 4.82 0 0 1-.97-.484l-.048-.03c-.504-.307-.999-.609-2.068-.722C2.682 14.464 2 13.846 2 13V9c0-.85.685-1.432 1.357-1.615.849-.232 1.574-.787 2.132-1.41.56-.627.914-1.28 1.039-1.639.199-.575.356-1.539.428-2.59z"/>
									</svg>
									{{answer.likeAnswerCount}}
								</el-button>
								<el-button type="danger" plain @click="showComplainAnswer(answer.id)">举报</el-button>
								<el-button type="warning" plain v-if="question.uid == currentUserID || answer.uid == currentUserID" @click="deleteAnswer(answer.id)">删除</el-button>
								<!-- <el-button type="warning" plain v-elseif="answer.uid == currentUserID" @click="deleteAnswer(answer.id)">删除</el-button> -->
							</el-row>
							<div v-if="answers[index].show_answer">
								<AnswerForAnswer :answerID="answer.id"></AnswerForAnswer>
							</div>
							<el-divider></el-divider>
						</div>
						<div class="block">
							<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="pageNum"
							 :page-size="pageSize" layout="prev, pager, next, jumper" :total="answerForQuestionTotal">
							</el-pagination>
						</div>
					</el-card>
				</div>
			</el-col>
		</el-row>
		<!-- 回答dialog -->
		<el-dialog title="回答" :visible.sync="answerVisible" style="width: 100%;">
			<!-- <el-input type="textarea" rows="6" v-model="answer.content" placeholder="写下你的回答吧"></el-input> -->
			<editoritem v-model="answer.content" ref="editor1" :isClear="isClear"></editoritem>
			<br><br>
			<el-button type="primary" @click="addAnswer()">回答</el-button>
		</el-dialog>
		<!-- 回复回答 -->
		<el-dialog title="回答" :visible.sync="answerForAnswerVisible">
			<!-- <el-input type="textarea" rows="6" v-model="answer.content" placeholder="写下你的回答吧"></el-input> -->
			<editoritem v-model="answer.content" ref="editor2" :isClear="isClear"></editoritem>
			<br><br>
			<el-button type="primary" @click="addAnswerForAnswer()">回答</el-button>
		</el-dialog>
		<!-- 举报问题dialog -->
		<el-dialog title="举报" :visible.sync="complainQuestionVisible">
			<el-input type="textarea" rows="6" v-model="comp" placeholder="写下举报的理由,长度不要超过255"></el-input>
			<br><br>
			<el-button type="danger" @click="complainQuestion()">举报</el-button>
		</el-dialog>
		<!-- 举报回复dialog -->
		<el-dialog title="举报" :visible.sync="complainAnswerVisible">
			<el-input type="textarea" rows="6" v-model="comp" placeholder="写下举报的理由,长度不要超过255"></el-input>
			<br><br>
			<el-button type="danger" @click="complainAnswer()">举报</el-button>
		</el-dialog>
	</div>
</template>

<script>
	import request from '@/utils/request.js'
	import avatar from '@/assets/img/login_bg.jpg';
	import AnswerForAnswer from '@/components/answer/AnswerForAnswer.vue'
	import editoritem from '@/components/grobal/EditorItem.vue'
	export default {
		data() {
			return {
				pageNum: 1,
				pageSize: 10,
				total: '',
				reload: '',
				show_answer: false,
				user: {
					id: '1',
					username: "沈鑫烁",
					img: avatar
				},
				kongge: '&nbsp;&nbsp;&nbsp;&nbsp;',
				question: {
					id: this.$route.params.id,
					uid: "1",
					title: "为什么 2020 年底突然很多地方开始限电？",
					type: "",
					description: "为什么突然很多地方开始限电？ 近日，湖南、浙江等地，陆续发布了“有序用电”通知，多年不见的“拉闸限电”再现。国网湖南电力新闻发言人陈浩表示， 发电能力相对滞后造成了湖南省供电出现缺口。他认为，近几年，湖南全省工业、商业、居民生活用电负荷、用电量全部持续增长 ，但电源装机容量并未大幅增长。和湖南一样因为发电能力滞后，而面临限电风险的还有江西和陕西省。浙江省的限电主要受工业用电高峰来临 ，电力供应保障压力增大影响。浙江省的限电还受“双控”和“减煤”工作面临的严峻形势和绿色发展的要求影响。（界面新闻） 湖南、浙江多地“拉闸限电”，背后原因却不一样|界面新闻 ​ www.jiemian.com 图标 近期湖南、江西电力供应偏紧，浙江也出现限制用电情况。国家发改委17日回应称，工业生产高速增长和低温寒流叠加导致电力需求超预期高速增长。 目前为止电力供应保持平稳有序，居民生活用电未受影响。国家发改委已会同相关部门企业，采取措施切实保障电力需求，确保电力供应总体平稳有序。 （新华社记者安蓓）",
					created_time: "2020-12-19 ",
					delete_flag: ""
				},
				answerForAnswerTotal: '520',
				answers: [{

				}],
				answerVisible: false,
				answerForAnswerVisible: false,
				complainQuestionVisible: false,
				complainAnswerVisible: false,
				complainAnswerID: '',
				comp: "",
				answer: {
					//id: '',
					content: '',
				},
				answerForQuestionTotal: '',
				isClear: false,
				currentUserID: this.getCookie("currentUserID"),
				isDelete: false,
				likeQuestionCount: 0,
				userLike: {
					likeUserID: this.currentUserID,
					likePostID: this.$route.params.id,
					likeAnswerID: 0,
					status: 0
				}
			}
		},
		methods: {
			handleSizeChange() {

			},
			handleCurrentChange() {
				request.get("/getAnswerByQuestionID/" + that.$route.params.id + "?pageNum=" + this.pageNum + "&pageSize=" + this.pageSize)
					.then(response => {
						that.answers = response.data.data.list
						that.answerForQuestionTotal = response.data.data.total
						for (var i = 0; i < that.answers.length; i++) {
							that.$set(this.answers[i], 'show_answer', false)
							that.answers[i].created_time = new Date(that.answers[i].created_time).toLocaleString()
						}
						for (var i = 0; i < that.answers.length; i++) {
							(function(i) {
								console.log(i)
								request.get("/getTotalOfAnswerForAnswer/" + that.answers[i].id)
									.then(response => {
										// console.log(that.answers[i])
										//that.answers[i].total = response.data.data
										that.$set(that.answers[i], 'total', response.data.data)
									})
							})(i);
						}
					})
			},
			seeAnswer(index) { //alert(this.answers[index].show_answer)
				//console.log(this.answers[index])
				//console.log(this.a)
				if (this.answers[index].show_answer == true) {
					//console.log(this.answers[index])
					this.answers[index].show_answer = false;
				} else {
					//alert(this.answers[index].show_answer)
					this.answers[index].show_answer = true;
				}
				//this.answers[index].username = "xinshuo"
				//this.answers[index].show_answer = true
				//this.$set(this.answers,this.answers[index].show_answer,true)
			},
			handleClose() {

			},
			addAnswer() {
				var that = this
				request.post("/addAnswer", {
					qid: this.question.id,
					content: this.answer.content,
					uid: that.getCookie("currentUserID")
				}).then(response => {
					// console.log(response)
					if (response.data.status == 200) {
						this.$message.success("回答成功")
						this.answerVisible = false
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
			},
			showAddAnswerForAnswer(answerID) {
				this.answerForAnswerVisible = true
				this.answer.id = answerID
			},
			addAnswerForAnswer() {
				request.post("/addAnswer", {
					aid: this.answer.id,
					content: this.answer.content,
					uid: this.getCookie("currentUserID")
				}).then(response => {
					//console.log(response)
					if (response.data.status == 200) {
						this.$message.success("回答成功")
						this.answerVisible = false
						location.reload()
					}
				})
			},
			complainQuestion() {
				if (this.comp.length == 0) {
					this.$message.error("举报内容不能为空")
					return
				}
				if (this.comp.length > 255) {
					this.$message.error("请将字数控制在255字以内")
					return
				}
				request.post("/addComplain", {
					qid: this.question.id,
					uid: this.getCookie("currentUserID"),
					reason: this.comp
				}).then(response => {
					if (response.data.status == 200) {
						this.$message.success("举报问题成功")
						this.complainQuestionVisible = false
						location.reload()
					}
				})
			},
			showComplainAnswer(answerID) {
				this.complainAnswerVisible = true
				this.complainAnswerID = answerID
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
						this.$message.success("举报问题成功")
						this.complainAnswerVisible = false
						location.reload()
					}
				})
			},
			deleteQuestion(qid) {
				this.$confirm("将永久删除该问题，确定吗？", "确认删除", {
					confirmButtonText: '确认删除',
					cancelButtonText: '取消',
					type: 'warring'
				}).then(() => {
					request.post("/deleteQuestion/" + qid).then(response => {
						if (response.data.status == 200) {
							this.$message.success("删除成功")
							window.history.back(-1)
						} else {
							this.$message.error("删除失败")
						}
					})
				}).catch(() => {
					this.$message.info("取消删除")
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
			giveALikeForQuestion() {
				//点赞问题
				var currentUserID = this.getCookie("currentUserID")
				if (currentUserID == null) {
					this.$router.push("/user/login")
					this.$message.error("请先登录")
					return;
				}
				request.post("/userLike", {
					"likeUserID": currentUserID,
					"likePostID": this.question.id,
					"likeAnswerID": 0
				}).then(response => {
					if (response.data.status == 200){
						this.$message.success("点赞成功");
						this.likeQuestionCount++;
						this.userLike.status = 1
					} else {
						this.$message.error("取消点赞失败")
					}
				})
			},
			giveALikeForAnswer(aid,index) {
				//点赞回复
				var currentUserID = this.getCookie("currentUserID")
				if (currentUserID == null) {
					this.$router.push("/user/login")
					this.$message.error("请先登录")
				}
				request.post("/userLike", {
					"likeUserID": currentUserID,
					"likePostID": 0,
					"likeAnswerID": aid
				}).then(response => {
					if (response.data.status == 200) {
						//点赞成功
						this.$message.success("点赞成功");
						this.answers[index].likeAnswerCount++;
						this.answers[index].likeThisAnswer = true
					} else {
						this.$message.error("点赞失败")
					}
				})
			},
			unLikeForQuestion() {
				//取消点赞问题
				var currentUserID = this.getCookie("currentUserID")
				if (currentUserID == null) {
					this.$router.push("/user/login")
					this.$message.error("请先登录")
					return;
				}
				request.post("/userUnLike", {
					"likeUserID": currentUserID,
					"likePostID": this.question.id,
					"likeAnswerID": 0
				}).then(response => {
					if (response.data.status == 200) {
						this.$message.success("取消点赞成功");
						this.likeQuestionCount--;
						this.userLike.status = 0;
					} else {
						this.$message.error("取消点赞失败")
					}
				})
			},
			unLikeForAnswer(aid,index) {
				//取消点赞回复
				var currentUserID = this.getCookie("currentUserID")
				if (currentUserID == null) {
					this.$router.push("/user/login")
					this.$message.error("请先登录")
					return;
				}
				request.post("/userUnLike", {
					"likeUserID": currentUserID,
					"likePostID": 0,
					"likeAnswerID": aid
				}).then(response => {
					if (response.data.status == 200) {
						this.$message.success("取消点赞成功");
						this.answers[index].likeAnswerCount--;
						this.answers[index].likeThisAnswer = false;
						//console.log("index:"+index)
					} else {
						this.$message.error("取消点赞失败")
					}
				})
			}
		},
		components: {
			AnswerForAnswer,
			editoritem
		},
		mounted() {
			var that = this
			request.get("/getQuestionByID/" + that.$route.params.id)
				.then(response => {
					if (response.data.data == null) {
						that.isDelete = true;
					}
					that.question = response.data.data
					that.question.created_time = new Date(response.data.data.created_time).toLocaleString()
					request.get("/getUserByID/"+that.question.uid).then(response => {
						that.user = response.data.data
					})
				})
			//获取点赞数据
			request.get("/getLikeCountOfQuestion/"+that.$route.params.id).then(response => {
				that.likeQuestionCount = response.data.data
			})
			//查看登录用户是否已经点赞过该问题
			if (this.currentUserID != null) {
				request.get("/isUserLikedThisQuestion/"+this.currentUserID+"/"+this.$route.params.id).then(response => {
					//console.log(response)
					if (response.data.data == true) {
						//点赞过该问题
						this.userLike.status = 1
					} else {
						//未点赞
						this.userLike.status = 0
					}
				})
			}
			request.get("/getAnswerByQuestionID/" + that.$route.params.id + "?pageNum=1&pageSize=10")
				.then(response => {
					that.answers = response.data.data.list
					that.answerForQuestionTotal = response.data.data.total
					for (var i = 0; i < that.answers.length; i++) {
						that.$set(this.answers[i], 'show_answer', false)
						that.answers[i].created_time = new Date(that.answers[i].created_time).toLocaleString()
					}
					for (var i = 0; i < that.answers.length; i++) {
						(function(i) {
							request.get("/getTotalOfAnswerForAnswer/" + that.answers[i].id)
								.then(response => {
									// console.log(that.answers[i])
									//that.answers[i].total = response.data.data
									that.$set(that.answers[i], 'total', response.data.data)
								})
							//获取回复的点赞记录
							request.get("/getLikeCountOfAnswer/"+that.answers[i].id).then(response => {
								that.$set(that.answers[i],"likeAnswerCount",response.data.data)
							})
							//查询登录用户是否点赞过该回复
							if (that.currentUserID != null) {
								request.get("/isUserLikedThisAnswer/"+that.currentUserID+"/"+that.answers[i].id).then(response => {
									if (response.data.data == true) {
										//点赞过该回复
										that.$set(that.answers[i],"likeThisAnswer",true)
									} else {
										//未点赞
										that.$set(that.answers[i],"likeThisAnswer",false)
									}
								})
							}
							//修改一下回答用户头像的路径
							that.$set(that.answers[i].user,"img","http://120.77.37.14:8080/treehole/upload/"+that.answers[i].user.img)
						})(i);
					}
				})
		},

	}
</script>

<style>
	.answer_username {
		font-weight: bold;
	}

	.time {
		color: grey;
		font-size: small;
	}

	.el-dialog {
		width: 1020px;
	}
</style>
