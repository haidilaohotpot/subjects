<template>
	<van-form validate-first>
	 <!-- 输入任意文本 -->
	 <van-field required v-model="orderForm.name" label="姓名" placeholder="请输入姓名" />
	 <!-- 输入手机号，调起手机号键盘 -->
	 <van-field required v-model="orderForm.phone" type="tel" label="手机号" placeholder="请输入电话号码" />
	 <!-- 允许输入正整数，调起纯数字键盘 -->
	 <!-- 允许输入数字，调起带符号的纯数字键盘 -->
	 <van-field required v-model="orderForm.address" type="text" label="收货地址" placeholder="请输入地址" />
	 <!-- 输入密码 -->
	  <div style="margin: 16px;">
	    <van-button round block type="info" @click="onSubmit()" native-type="submit">
	      提交
	    </van-button>
	  </div>
	</van-form>
</template>

<script>
	
	import {Toast} from 'vant'
	import axios from 'axios'
	import {
		mapState,
		mapGetters,
	} from "vuex"
	
	export default {
	  data() {
	    return {
		  orderForm:{
			  name: '',
			  phone: '',
			  address: '',
			  openid:'',
			  items:''
		  }
	    };
	  },
	  computed: {
	  	...mapState(['cart']),
	  	...mapGetters(['cartTotal'])
	  },
	  methods: {
		onSubmit(){
			var that = this;
			if(that.orderForm.name == '' || that.orderForm.name == null ){
				Toast('姓名不能为空');return false;
			}
			if(that.orderForm.phone == '' || that.orderForm.phone == null ){
				Toast('电话不能为空');return false;
			}
			
			if(that.orderForm.address == '' || that.orderForm.address == null ){
				Toast('地址不能为空');
				return false;
			}
			
			var items = [];
			
			that.cart.forEach((item,index)=>{
				if(item.selected == true){
					var orderDetail = {};
					orderDetail.productId = item.id
					orderDetail.productName = item.name
					orderDetail.productPrice = item.price
					orderDetail.productQuantity = item.num
					orderDetail.productIcon = item.icon
					items.push(orderDetail)
				}
				
			})
			
			that.orderForm.items  = JSON.stringify(items);
			that.orderForm.openid = 1;
			
			console.log(that.orderForm)
			
			// 提交订单
			axios.post('http://localhost:8089/sell/buyer/order/create',that.orderForm).then(function(result){
				
				if(result.data.code ==0 ){
					// 删除本地存储
					that.cart.forEach((item,index)=>{
						if(item.selected == true){
							that.cart.splice(index,1)
						}
						
					})
					
					// 跳转订单页面
					that.$router.push({path: 'orderMaster',query: {openid: 1}})
					
				}else{
					Toast(result.data.msg)
				}
				
			})
			
			
			
		}
	  },
	};
</script>

<style>
</style>
