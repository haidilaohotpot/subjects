<template>
	<el-table :data="this.$store.state.dataList.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))" style="width: 100%">
		<el-table-column label="生日" prop="date">
		</el-table-column>
		<el-table-column label="姓名" prop="name">
		</el-table-column>
		<el-table-column label="地址" prop="address">
		</el-table-column>
		<el-table-column align="right">
			<template slot="header" slot-scope="scope">
				<el-input v-model="search" :name="scope.$index" size="mini" placeholder="输入关键字搜索" />
			</template>
			<template slot-scope="scope">
				<el-button size="mini" @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
				<el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
			</template>
		</el-table-column>
	</el-table>
</template>

<script>
	import axios from 'axios'
	export default {
		data() {
			return {
				search: ''
			}
		},
		mounted() {
			var that = this
			axios.get('/getList').then((res) => {
				if (res.status == 200) {
					that.$store.state.dataList = res.data.dataList
				}
			})
		},
		methods: {
			handleEdit(index, row) {
				this.$router.push({
					path: '/operation',
					query: {
						id: index
					}
				})
				console.log(index, row);
			},
			handleDelete(index, row) {
				var data = {}
				data.idx = index
				// this.$store.commit('delete',data)
				this.$store.dispatch('delete',data)
				console.log(index, row);
			}
		},
	}
</script>
<style>
</style>
