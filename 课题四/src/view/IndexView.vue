<template>
	<div>

		<!-- <img alt="Vue logo" src="./assets/logo.png"> -->
		<!-- <HelloWorld msg="Welcome to Your Vue.js App"/> -->
		<UserTable></UserTable>
		<!-- <router-link to="/helloworld">HelloWorld</router-link> -->
		<!-- <router-view></router-view> -->
		<Pagination></Pagination>
	</div>
</template>

<script>
	import UserTable from '../components/UserTable.vue'
	import Pagination from '../components/Pagination.vue'
	
	export default {
		name: 'IndexView',
		components: {
			UserTable,
			Pagination
		}
	}
</script>

<style>

</style>
