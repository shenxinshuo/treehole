<template>
	<div>
		<div v-for="(complain,index) in complains">
			<el-link :href="'/complain/detail/'+complain.id">{{complain.reason}}</el-link>
			
			<span style="float: right;">{{complain.created_time}}</span>
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
	</div>
</template>

<script>
	import request from '@/utils/request.js'
	export default {
		data() {
			return {
				complains: {
					
				},
				pageNum: 1,
				pageSize: 10,
				total: 0
			}
		},
		methods: {
			handleSizeChange() {
			
			},
			handleCurrentChange() {
				request.get("/getComplainsOfNoDeal?pageNum=" + this.pageNum + "&pageSize=" + this.pageSize).then(response => {
					this.complains = response.data.data.list;
					})
			}
		},
		mounted() {
			request.get("/getComplainsOfNoDeal?pageNum="+this.pageNum+"&pageSize="+this.pageSize).then(response => {
				console.log(response)
				this.total = response.data.data.total;
				this.complains = response.data.data.list;
				for (var i=0; i<this.complains.length; i++){
					if (this.complains[i].reason.length > 50) {
						this.complains[i].reason = this.complains[i].reason.substring(0,50)
					}
				}
				
			})
		}
	}
</script>

<style>
</style>
