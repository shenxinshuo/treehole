<template>
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
</template>

<script>
	export default {
		data(){
			return {
				pageNum: 1,
				pageSize: 10,
				total: ''
			}
		},
		methods: {
			handleSizeChange() {
				
			},
			handleCurrentChange() {
				
			}
		}
	}
</script>

<style>
</style>
