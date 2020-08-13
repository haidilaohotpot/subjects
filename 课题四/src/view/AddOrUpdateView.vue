<template>
	<el-form ref="form" :model="form" label-width="80px">
		<div>
			<el-divider><i class="el-icon-mobile-phone"></i></el-divider>
		</div>
		<el-form-item label="姓名">
			<el-input v-model="form.name"></el-input>
		</el-form-item>
		<el-form-item label="地址">
			<el-input v-model="form.address"></el-input>
		</el-form-item>
		<el-form-item label="出生日期">
			<el-col :span="11">
				<el-date-picker type="date" placeholder="选择日期" v-model="form.date" style="width: 100%;"></el-date-picker>
			</el-col>

		</el-form-item>
		<el-form-item>
			<el-button type="primary" @click="onSubmit">提交</el-button>
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
			onSubmit() {
				console.log('submit!');
				var that = this
				var result = {}
				result.item = that.form
				if (that.id == -1) {
					// that.$store.commit('add',result)
					this.$store.dispatch('add',result)
					
				} else {
					result.idx = that.id
					// that.$store.commit('update',result)
					that.$store.dispatch('update',result)
				}
				that.onCancel()
			},
			onCancel() {
				this.$router.push({
					path: "/"
				})
			}
		}
	}
</script>
