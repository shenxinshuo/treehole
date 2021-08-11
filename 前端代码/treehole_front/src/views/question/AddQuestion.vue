<template>
	<div>
		<el-row type="flex" justify="center">
			<el-col :span="18">
				<el-card class="box-card">
					<el-form ref="form" :model="form" label-width="80px">
						<input type="text" hidden="hidden" v-model="form.uid" />
						<el-form-item label="标题">
							<el-input v-model="form.title"></el-input>
						</el-form-item>
						
							<el-form-item label="类别">
								<el-select v-model="form.type" placeholder="请选择问题类别">
									<el-option label="职场" value="work"></el-option>
									<el-option label="校园" value="school"></el-option>
									<el-option label="情感" value="emotion"></el-option>
									<el-option label="生活" value="life"></el-option>
								</el-select>
							</el-form-item>
						

						<el-form-item label="具体描述">
							<!-- <el-input type="textarea" v-model="form.description" rows="8"></el-input> -->
							<editoritem v-model="form.description" ref="editor1" :isClear="isClear" @change="change"></editoritem>
						</el-form-item>
						<el-form-item>
							<el-button type="primary" @click="onSubmit">立即创建</el-button>
							<el-button @click="handleCancle()">取消</el-button>
						</el-form-item>
					</el-form>

				</el-card>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	import request from '@/utils/request.js';
	import editoritem from '@/components/grobal/EditorItem.vue'
	export default {
		data() {
			return {
				form: {
					uid: '',
					title: '',
					type: '',
					description: ''
				},
				isClear: false
			}
		},
		components: {
			editoritem
		},
		methods: {
			onSubmit() {
				if (this.form.title.length == 0) {
					this.$message.error("标题不能为空")
					return 
				}
				if (this.form.title.length > 200) {
					this.$message.error("标题超度不能超过200")
					return 
				}
				//console.log("执行请求")
				request.post("/addQuestion", {
					uid: this.form.uid,
					title: this.form.title,
					type: this.form.type,
					description: this.form.description
				}).then(response => {
					// console.log(response)
					if (response.data.status == 200) {
						this.$message.success("添加成功！")
						//this.$router.push("/")
					}
				})
			},
			change(val) {

			},
			handleCancle() {
				window.history.back(-1)
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
			this.form.uid = getCookie("currentUserID")
		}
	}
</script>

<style>
	
</style>
