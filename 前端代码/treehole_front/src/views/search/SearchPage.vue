<template>
	<div>
		<el-row type="flex" justify="center">
			<el-col :span="12">
				<el-card class="box-card">
					"{{search}}"的搜索结果共有<strong>{{total}}</strong>条
					<hr style="margin-left: 0px;margin-right: 0px;"></hr>
					<div v-for="(question,index) in questions">
						<el-link :underline="false" :href="'/question/'+question.id"><h2>{{ question.title }}</h2></el-link>
						<p v-html="question.description"></p>...
						<el-link :href="'/question/'+question.id" type="primary">查看全文</el-link>
						<el-divider></el-divider>
					</div>
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
				search: this.$route.query.search,
				pageNum: 0,
				pageSize: 10,
				total: 0,
				questions:{
					
				}
			}
		},
		methods:{
			handleSizeChange(){
				
			},
			handleCurrentChange() {
				request.get("/getQuestionsBySearch?search="+this.search+"&pageNum="+this.pageNum+"&pageSize="+this.pageSize).then(response=>{
					this.questions = response.data.data.list
					this.total = response.data.data.total
				})
			}
		},
		created() {
			request.get("/getQuestionsBySearch?search="+this.search+"&pageNum="+this.pageNum+"&pageSize="+this.pageSize).then(response => {
				this.questions = response.data.data.list
				this.total = response.data.data.total
			})
		}
	}
</script>

<style>
</style>
