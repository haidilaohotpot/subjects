<template>
	<el-menu :default-active="activeIndex2" class="el-menu-demo" mode="horizontal" @select="handleSelect" 
	background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
		<el-menu-item index="1">
			<router-link to="/">主页</router-link>
		</el-menu-item>
		<el-menu-item index="2">
			<router-link to="/operation">新增</router-link>
		</el-menu-item>
	</el-menu>
</template>


<script>
	export default {
		data() {
			return {
				activeIndex: '1',
				activeIndex2: '1'
			};
		},
		methods: {
			handleSelect(key, keyPath) {
				console.log(key, keyPath);
			}
		}
	}
</script>

<style>
</style>
