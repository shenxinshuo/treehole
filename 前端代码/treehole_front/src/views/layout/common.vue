<template>
	<div id="app">
		<div v-if="seen">
			<el-row>
				<Head></Head>
			</el-row>
		</div>
		<router-view></router-view>
	</div>
</template>

<script>
	import Head from '@/components/grobal/Head.vue'
	export default {
		data() {
			return {
				// v-if的""里面如果有大写字母，v-if没法起作用
				seen: true
			}
		},
		components: {
			Head
		}
	}
</script>

<style>
	.el-header,
	.el-footer {
		background-color: #B3C0D1;
		color: #333;
		text-align: center;
		line-height: 60px;
	}

	.el-aside {
		background-color: #D3DCE6;
		color: #333;
		text-align: center;
		line-height: 200px;
	}

	.el-main {
		background-color: #E9EEF3;
		color: #333;
		text-align: center;
		line-height: 160px;
	}

	body>.el-container {
		margin-bottom: 40px;
	}

	.el-container:nth-child(5) .el-aside,
	.el-container:nth-child(6) .el-aside {
		line-height: 260px;
	}

	.el-container:nth-child(7) .el-aside {
		line-height: 320px;
	}
</style>
