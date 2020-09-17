<template>
	<div>
		<div>
			<div class="cart" v-for="(item,index) in cart" :key="index">
				<van-checkbox :value="item.selected" @click="oneCheckHandler(item)"></van-checkbox>
				<van-card :price="item.price" :title="item.name" :thumb="item.icon">
				</van-card>
				<van-stepper size="32" :value="item.num" min="1" max="10" @plus="inc(item)" @minus="dec(item)" />
			</div>
		</div>
		<van-submit-bar v-if="cart.length > 0" custom-class="van-submit-bar" :price="cartTotal*100" @submit="orderAddress()" button-text="提交订单">
			<van-checkbox @click="checkAllHandler" v-model="checked">全选</van-checkbox>
		</van-submit-bar>
		<span v-if="cart.length <=0 " style="text-align: center; color: #25B4ED; margin-left: 100px;margin-top: 100px;">您的购物车中无商品~</span>
	</div>
</template>

<script>
	import {
		mapState,
		mapGetters,
		mapActions
	} from "vuex"
	export default {
		data() {
			return {
				checked: true
			}
		},
		computed: {
			...mapState(['cart']),
			...mapGetters(['cartTotal'])
		},
		methods: {
			...mapActions(['inc', 'dec', 'checkAll', 'checkOne']),
			checkAllHandler() {
				this.checked = !this.checked;
				this.checkAll(this.checked)
			},
			oneCheckHandler(item) {
				this.checkOne(item);
			},
			orderAddress(){
				this.$router.push({ path: '/orderAddress' })
			}
		}
	}
</script>

<style scoped="scoped">
	.cart {
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: space-around;
	}
	.van-submit-bar {
	    position: relative !important;
	}
</style>
