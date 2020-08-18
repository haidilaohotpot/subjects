<template>
	<el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
		<el-menu-item index="1" @click="toIndex()">主页</el-menu-item>
		<el-menu-item index="2" @click="toOperator()">新增</el-menu-item>
	</el-menu>
</template>


<script>
	export default {
		data() {
			return {
				activeIndex: '1',
			};
		},
		methods: {
			handleSelect(key, keyPath) {
				console.log(key, keyPath);
			},
			toIndex() {
				this.$router.push({
					path: "/"
				})
			},
			toOperator() {
				this.$router.push({
					path: "/operation"
				})
			}
		}
	}
</script>

<style>
</style>
