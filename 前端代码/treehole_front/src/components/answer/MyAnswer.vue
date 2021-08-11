<template>
	<div>
		<div v-if="answers">
			<h3>{{total}}个回答</h3>
			<div v-for="(answer, index) in answers">
				<el-divider class="fengexian"></el-divider>
				<div v-if="answer.qid == 0">
					<el-link @click="handleClick(answer.aid)">
						<div v-text="answer.content" ></div>
					</el-link>
					<div style="display: inline-block;float: right;">
						<font color="grey">{{answer.created_time}}</font>
					</div>
				</div>
				<div v-else>
					<el-link :href="public_path+'/question/'+answer.qid">
						<div v-text="answer.content" ></div>
					</el-link>
					<div style="display: inline-block;float: right;">
						<font color="grey">{{answer.created_time}}</font>
					</div>
				</div>
				
			</div>
			<el-divider></el-divider>
			<div class="block">
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
		<div v-else>
			您还未发表过回答
		</div>
	</div>
</template>

<script>
	import request from '@/utils/request.js'
	export default {
		data() {
			return {
				currentUserID: '',
				pageNum: 1,
				pageSize: 10,
				answers: [],
				total: 0,
				qid: 0, //为处理从myAnswer跳转到问题列表定义的临时变量
				public_path: process.env.VUE_APP_PUBLIC_PATH,
			}
		},
		computed: {},
		methods: {
			handleSizeChange() {

			},
			handleCurrentChange() {
				request.get("/getAnswerByUID/" + this.currentUserID +
					"?pageNum=" + this.pageNum + "&pageSize=" + this.pageSize).then(response => {
					console.log(response);
					this.answers = response.data.data.list;
					this.total = response.data.data.total
				})
			},
			handleClick(aid) {
				request.get("/getAnswerByID/"+aid).then(response => {
					console.log(response)
					this.$router.push('/question/'+response.data.data.qid)
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
			this.currentUserID = getCookie("currentUserID")
			request.get("/getAnswerByUID/" + this.currentUserID +
				"?pageNum=" + this.pageNum + "&pageSize=" + this.pageSize).then(response => {
				//console.log(response);
				this.answers = response.data.data.list;
				this.total = response.data.data.total
				
			})
			
		}
	}
</script>

<style>
	h3 {
		margin-top: 0px;
		margin-bottom: 0px;
	}

	.fengexian {
		margin-top: 20px;
		margin-bottom: 10px;
	}
</style>
