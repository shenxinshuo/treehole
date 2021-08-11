<template>
	<div>
		<el-row type="flex" justify="center">
			<el-col :span="16">
				<el-card>
					当前登录管理员为：{{admin.name}}
					<el-divider></el-divider>
					<el-tabs v-model="actionName">
						<el-tab-pane label="处理举报" name="first">
							<Index></Index>
						</el-tab-pane>
						<el-tab-pane label="管理公告" name="second">
							<ManageNotice></ManageNotice>
						</el-tab-pane>
						<el-tab-pane label="审核" name="third">审核</el-tab-pane>
					</el-tabs>
				</el-card>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	import request from '@/utils/request.js'
	import Index from '@/components/complain/index.vue'
	import ManageNotice from '@/components/notice/ManageNotice.vue'
	export default {
		data() {
			return {
				admin: {
					name: ''
				},
				actionName: "first"
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
			this.admin.name = getCookie("currentAdmin");
		},
		components: {
			Index,ManageNotice
		}
	}
</script>

<style>
</style>
