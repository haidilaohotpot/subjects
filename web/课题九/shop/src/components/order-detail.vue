<template>
	<div class="content" style="margin-left: 60px;">
		<van-card
		  :num="orderDetail.productQuantity"
		  :price="orderDetail.productPrice"
		  :title="orderDetail.productName"
		  desc="订单号"
		  :thumb="orderDetail.productIcon"
		>
		 <template #tags>
		    <van-tag plain type="danger">{{orderDetail.orderId}}</van-tag>
		  </template>
		</van-card>
	</div>
	
</template>

<script>
	export default {
		props:['orderDetail'],
		methods:{
			
		}
	}
</script>


<style scoped="scoped">
	.cart-wrap{
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}
	.gray_text{
		color:#ccc;
	}
</style>
