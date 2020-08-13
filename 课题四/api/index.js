//api.js
import Mock from 'mockjs'

var dataList = [{
	date: '2016-05-02',
	name: '王小虎',
	address: '上海市普陀区金沙江路 1518 弄'
}, {
	date: '2016-05-04',
	name: 'GGD',
	address: '上海市普陀区金沙江路 1517 弄'
}, {
	date: '2016-05-01',
	name: 'SERT',
	address: '上海市普陀区金沙江路 1519 弄'
}, {
	date: '2016-05-03',
	name: 'GSDF',
	address: '上海市普陀区金沙江路 1516 弄'
}, {
	date: '2016-05-03',
	name: 'GSDF',
	address: '上海市普陀区金沙江路 1516 弄'
}, {
	date: '2016-05-03',
	name: 'GSDF',
	address: '上海市普陀区金沙江路 1516 弄'
}, {
	date: '2016-05-03',
	name: 'GSDF',
	address: '上海市普陀区金沙江路 1516 弄'
}, {
	date: '2016-05-03',
	name: 'GSDF',
	address: '上海市普陀区金沙江路 1516 弄'
}, {
	date: '2016-05-03',
	name: 'GSDF',
	address: '上海市普陀区金沙江路 1516 弄'
}, {
	date: '2016-05-03',
	name: 'GSDF',
	address: '上海市普陀区金沙江路 1516 弄'
}, {
	date: '2016-05-03',
	name: 'GSDF',
	address: '上海市普陀区金沙江路 1516 弄'
}]

export default [
	Mock.mock('/getList', {
		'dataList': dataList
	}),
	Mock.mock('/delete', 'post', function(options) {
		// var idx = options.body.idx
		var body = JSON.parse(options.body)
		console.log(body)
		dataList.splice(body.idx,1)
		return {
			'dataList': dataList
		}
	}),
	Mock.mock('/update', 'post', function(options) {
		// var idx = options.body.idx
		var body = JSON.parse(options.body)
		console.log(body)
		// dataList.splice(body.idx,1)
		dataList.splice(body.idx,1,body.item)
		return {
			'dataList': dataList
		}
	}),
	Mock.mock('/add', 'post', function(options) {
		// var idx = options.body.idx
		var body = JSON.parse(options.body)
		console.log(body)
		// dataList.splice(body.idx,1)
		dataList.push(body.item)
		return {
			'dataList': dataList
		}
	})
]
