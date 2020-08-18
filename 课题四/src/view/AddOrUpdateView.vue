<template>
	<el-form ref="form" :model="form" label-width="80px" :rules="rules">
		<div>
			<el-divider><i class="el-icon-mobile-phone"></i></el-divider>
		</div>
		<el-form-item label="姓名" prop="name">
			<el-input v-model="form.name"></el-input>
		</el-form-item>
		<el-form-item label="地址" prop="address">
			<el-input v-model="form.address"></el-input>
		</el-form-item>
		<el-form-item label="出生日期">
			<el-col :span="11">
				<el-date-picker v-model="form.date" type="date" placeholder="选择日期" format="yyyy 年 MM 月 dd 日" value-format="yyyy-MM-dd">
				</el-date-picker>

			</el-col>
		</el-form-item>
		<el-form-item>
			<el-button type="primary" @click="onSubmit('form')">提交</el-button>
			<el-button @click="onCancel">取消</el-button>
		</el-form-item>
	</el-form>
</template>
<script>
	export default {
		data() {
			return {
				id: -1,
				form: {
					name: '',
					address: '',
					date: '1999-06-12',
				},
				rules: {
					name: [{
						required: true,
						message: '请输入姓名',
						trigger: 'blur'
					}],
					address: [{
						required: true,
						message: '请输入地址',
						trigger: 'blur'
					}],
					date: [{
						type: 'date',
						required: true,
						message: '请选择日期',
						trigger: 'blur'
					}]
				}
			}
		},
		created() {
			var that = this
			var id = this.$route.query.id;
			console.log(that.id)
			if (undefined != id) {
				that.id = id
				var data = this.$store.state.dataList[id]
				that.form.name = data.name
				that.form.address = data.address
				that.form.date = data.date
			}
			// console.log(data)
		},
		methods: {

			onSubmit(formName) {
				var that = this
				that.$refs[formName].validate((valid) => {
					if (valid) {
						console.log('submit!');
						var result = {}
						result.item = that.form
						if (that.id == -1) {
							// that.$store.commit('add',result)
							that.$store.dispatch('add', result)

						} else {
							result.idx = that.id
							// that.$store.commit('update',result)
							that.$store.dispatch('update', result)
						}
						that.onCancel()
					} else {
						console.log('error submit!!');
						return false;
					}
				});

			},
			onCancel() {
				this.$router.push({
					path: "/"
				})
			}
		}
	}
</script>
