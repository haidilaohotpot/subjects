<template>
	<div>
		<van-sticky style="color: #8c9cea;">
			<van-row class="search-top">
				<van-col offset="10" span="10">
					<div class="cart-text">订单收货地址</div>
				</van-col>
			</van-row>
		</van-sticky>
		<order-template></order-template>
		<foot-tabs></foot-tabs>
	</div>
</template>
<script>
	import FootTabs from "../components/foot-tabs.vue"
	import OrderTemplate from "../components/order-template.vue"
	export default {
		components: {
			FootTabs,OrderTemplate
		}
	}
</script>
<style scoped="scoped">
	.search-top {
		background:yellow;
		width: 100%;
		height: 50px;
	}

	.cart-text {
		color: black;
		font-size: 18px;
		line-height: 50px;
		font-weight: bold;
	}

	.gray-text {
		color: #EEEEEE;
		line-height: 50px;
	}
</style>
