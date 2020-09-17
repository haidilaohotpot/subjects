<template>
	<div>
		<van-sticky style="color: #8c9cea;">
			<van-row class="search-top">
				<van-col offset="10" span="10">
					<div class="cart-text">我的订单</div>
				</van-col>
			</van-row>
		</van-sticky>
		<order-master :orderMasterList="orderMasterList"></order-master>
		<foot-tabs></foot-tabs>
	</div>
</template>
<script>
	import FootTabs from "../components/foot-tabs.vue"
	import OrderMaster from "../components/order-master.vue"
	import axios from 'axios'
	export default {
		data() {
		  return {
			 orderMasterList:[]
		  };
		},
		components: {
			FootTabs,OrderMaster
		},
		created() {
			
			var that = this;
			var openid= 1
			axios.get('http://localhost:8089/sell//buyer/order/list?openid='+openid).then(function(result){
				
				if(result.data.code == 0){
					console.log(result)
					that.orderMasterList = result.data.data;
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
