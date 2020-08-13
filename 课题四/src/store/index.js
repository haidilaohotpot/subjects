import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

const store = new Vuex.Store({
	state: {
		dataList: []
	},
	mutations: {
		add(state, data) {
			// state.dataList.push(data.item)
			// axios.post('/add', data).then((res) => {
			// 	if (res.status == 200) {
			// 		state.dataList = res.data.dataList
			// 	}
			// })
			state.dataList = data
		},
		update(state, data) {
			// state.dataList.splice(data.idx,1,data.item)
			// axios.post('/update', data).then((res) => {
			// 	if (res.status == 200) {
			// 		state.dataList = res.data.dataList
			// 	}
			// })
			state.dataList = data
		},
		delete(state, data) {
			// state.dataList.splice(data.idx,1)
			// axios.post('/delete', data).then((res) => {
			// 	if (res.status == 200) {
			// 		state.dataList = res.data.dataList
			// 	}
			// })
			state.dataList = data
		}
	},
	actions: {
		
		async add(context,data){
			var res = await axios.post('/add', data)
			if (res.status == 200) {
				// context.dataList = res.data.dataList
				context.commit('add',res.data.dataList)
			}
		},
			
		async update(context,data){
			var res = await axios.post('/update', data)
			if (res.status == 200) {
				// context.dataList = res.data.dataList
				context.commit('update',res.data.dataList)
			}
		},
		
		async delete(context,data){
			var res = await axios.post('/delete', data)
			if (res.status == 200) {
				// context.dataList = res.data.dataList
				context.commit('delete',res.data.dataList)
			}
		}
		
		
	}
})

export default store
