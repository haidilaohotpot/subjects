import Vue from "vue";
import VueRouter from "vue-router";

// 引入组件
import IndexView from '../view/IndexView.vue'
import AddOrUpdateView from '../view/AddOrUpdateView.vue'
// 要告诉 vue 使用 vueRouter
Vue.use(VueRouter);

const routes = [{
		path: "/",
		component: IndexView
	},
	{
		path: "/operation",
		component: AddOrUpdateView
	}
]

var router = new VueRouter({
	routes
})
export default router;
