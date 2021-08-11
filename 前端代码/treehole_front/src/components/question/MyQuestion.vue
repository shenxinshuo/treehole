<template>
	<div>
		<div v-if="questions">
			<h3>{{total}}个问题</h3>
			<div v-for="(question, index) in questions">
				<el-divider class="fengexian"></el-divider>
				<el-link :href="public_path+'/question/'+question.id">{{question.title}}</el-link>
				<div style="display: inline-block;float: right;">
					<font color="grey">{{question.created_time}}</font>
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
			您还未提出过问题
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
				questions:[],
				total: 0,
				public_path: process.env.VUE_APP_PUBLIC_PATH,
			}
		},
		methods:{
			handleSizeChange() {
			
			},
			handleCurrentChange() {
				request.get("/getQuestionsByUID/" + this.currentUserID +
					"?pageNum=" + this.pageNum + "&pageSize=" + this.pageSize).then(response => {
					//console.log(response);
					this.questions = response.data.data.list;
					this.total = response.data.data.total
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
			request.get("/getQuestionsByUID/"+this.currentUserID+
						"?pageNum="+this.pageNum+"&pageSize="+this.pageSize).then(response => {
				//console.log(response);
				this.questions = response.data.data.list;
				this.total = response.data.data.total
			})
		}
	}
</script>

<style>
	h3{
		margin-top: 0px;
		margin-bottom: 0px;
	}
	.fengexian {
		margin-top: 10px;
		margin-bottom: 10px;
	}
</style>
