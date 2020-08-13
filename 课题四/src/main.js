import Vue from 'vue'
// import Vuex from 'vuex'
import App from './App.vue'
import router from './router/index.js'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import '../api/index.js'
import store from './store/index.js'


Vue.config.productionTip = false

Vue.use(ElementUI)

new Vue({
	router,
	store,
	render: h => h(App),
}).$mount('#app')
