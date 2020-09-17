<template>
	<div>
		<van-sticky style="color: #8c9cea;">
			<van-row class="search-top">
				<van-col offset="10" span="10">
					<div class="cart-text">订单详情</div>
				</van-col>
			</van-row>
		</van-sticky>
		<van-grid :border="false"  :column-num="2">
			<van-grid-item v-for="(orderDetail,index) in orderDetailList" 
					:key="index">
				<order-detail :orderDetail="orderDetail" ></order-detail>
			</van-grid-item>
		</van-grid>
		
		<foot-tabs></foot-tabs>
	</div>
</template>
<script>
	import FootTabs from "../components/foot-tabs.vue"
	import OrderDetail from "../components/order-detail.vue"
	import axios from 'axios'
	export default {
		data() {
		  return {
			 orderDetailList:[]
		  };
		},
		components: {
			FootTabs,OrderDetail
		},
		created() {
			var that = this
			var openid= this.$route.query.openid
			var orderId= this.$route.query.orderId
			axios.get('http://localhost:8089/sell//buyer/order/detail?openid='+openid+'&orderId='+orderId).then(function(result){
				if(result.data.code == 0){
					that.orderDetailList = result.data.data.orderDetailList;
				}
			})
		
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
