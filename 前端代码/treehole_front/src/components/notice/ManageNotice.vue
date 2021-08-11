<template>
	<div>
		<el-input type="textarea" rows="5" v-model="notice"></el-input>
		<br><br>
		<el-button type="primary" @click="updateNotice">修改</el-button>
	</div>
</template>

<script>
	import request from '@/utils/request.js'
	export default {
		data() {
			return {
				notice: ''
			}
		},
		methods: {
			updateNotice() {
				request.post("/updateNotice",{
					"notice": this.notice
				}).then(response => {
					if (response.data.status == 200) {
						this.$message.success("公告修改成功")
					} else {
						this.$message.error("公告修改失败")
					}
				})
			}
		},
		mounted() {
			request.get("/getNotice").then(response => {
				this.notice = response.data.data
			})
		}
	}
</script>

<style>
</style>
