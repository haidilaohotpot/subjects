<template>
	
	<van-collapse v-model="activeNames">
	  <van-collapse-item v-for="(orderMaster,index) in orderMasterList" :key="index"  :title="'订单号：'+orderMaster.orderId" :name="index">
		  
		  <van-cell-group>
		    <van-cell title="姓名" :value="orderMaster.buyerName" />
			<van-cell title="手机号" :value="orderMaster.buyerPhone" />
			<van-cell title="地址" :value="orderMaster.buyerAddress" />
			<van-cell title="实收" :value="orderMaster.orderAmount" />
			<van-cell title="订单状态" :value="orderStatus[orderMaster.orderStatus]" />
			<van-cell title="时间" :value="orderMaster.createTime" />
			<van-cell title="" value="">
				<van-button size="mini" type="primary" @click="toDetail(orderMaster.orderId,orderMaster.buyerOpenid)">订单详情</van-button>
				<van-button v-if="orderMaster.orderStatus != 2" size="mini" type="primary" @click="cancelOrder(orderMaster.orderId,orderMaster.buyerOpenid)" >取消订单</van-button>
			</van-cell>
		  </van-cell-group>
		
	  </van-collapse-item>
	  
	</van-collapse>
</template>

<script>
	import axios from 'axios'
	import {Toast} from 'vant'
	export default {
	  props:['orderMasterList'],
	  data() {
	    return {
	      activeNames: [0],
		  orderStatus:[
			  '新订单',
			  '已完结',
			  '已取消'
		  ]
	    };
	  },
	  methods:{
		  toDetail(orderId,openid){
			  console.log(orderId)
			   this.$router.push({path: '/orderDetail',query: {openid: openid, orderId: orderId}})
			  
		  },
		  cancelOrder(orderId,openid){
			  var that = this
			  // 取消订单
			  axios.post('http://localhost:8089/sell/buyer/order/cancel?openid='+openid+'&orderId='+orderId
			  ).then(function(result){
			  	
			  	if(result.data.code == 0 ){
					Toast('取消成功')
					that.$router.go(0)
			  		// 跳转订单页面	
			  	}else{
			  		Toast('取消失败')
			  	}
			  	
			  })
			  
		  }
	  }
	};
</script>

<style>
</style>
