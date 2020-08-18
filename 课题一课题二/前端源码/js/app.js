window.app = {
	serverUrl: "http://localhost:8088",
	errorMsg: "",
	userList: [],
	page: 1,
	total: 1,

	upOrCre: function() {

		var item = app.build()
		var idx = $("#idx").val()

		console.log(item)

		// 数据检查
		if (!app.checkInfo(item)) {
			app.showError()
			return false;
		}

		// 新增
		if (item.id == -1 || undefined == item.id || "" == item.id) {
			var url = app.serverUrl + '/user/create'
			app.put(item, url)
		} else {
			// 修改
			var url = app.serverUrl + '/user/update'
			app.put(item, url)
		}

	},

	// 显示错误信息
	showError: function() {
		var saveFlag = $("#saveFlag");
		var htmlF = '';
		htmlF += '<div class="alert alert-warning" >';
		htmlF += '<a href="#" class="close" data-dismiss="alert">';
		htmlF += '&times;';
		htmlF += '</a>';
		htmlF += '<strong>警告！</strong>' + app.errorMsg;
		htmlF += '</div>';
		saveFlag.html(htmlF);
	},


	build: function() {
		var item = {};
		item.name = $("#name").val();
		item.code = $("#code").val();
		item.age = $("#age").val();
		item.sex = $("#sex").find("option:selected").val();
		item.position = $("#position").find("option:selected").val();
		item.id = $("#userId").val();
		console.log(item);
		return item;
	},

	// 删除用户
	deleteUser: function(id) {
		console.log(id)
		$.ajax({
			type: 'POST',
			url: app.serverUrl + '/user/delete/' + id,
			success: function(result) {
				console.log(result);
				if (result.code == 0) {
					app.queryUser(1, 10)
				} else {
					app.errorMsg = result.msg
					app.showError()
				}
			},
		});
	},

	// 点击修改用户信息
	updateUser: function(idx) {
		var item = app.userList[idx];
		console.log(item);
		$("#userId").val(item.id);
		$("#name").val(item.name);
		$("#code").val(item.code);
		$("#age").val(item.age);
		$("#sex").val(item.sex);
		$("#idx").val(idx);
		$("#position").val(item.position);
		$("#code").attr("disabled", true);
		$("#myModal").modal("show");
	},

	// 保存
	put: function(data, url) {
		console.log(data);
		// 发送请求
		$.ajax({
			type: 'POST',
			url: url,
			data: data,
			success: function(result) {
				console.log(result);
				if (result.code == 0) {
					app.queryUser(1, 10)
				} else {
					app.errorMsg = result.msg
					app.showError()
				}
			},
		});

	},

	// 遍历数组
	listFor: function() {
		var html = "";
		var pageHtml = '';
		app.userList.forEach(function(item, idx) {
			html += '<tr>';
			html += '<td>' + item.code + '</td>';
			html += '<td>' + item.name + '</td>';
			// 男
			if (item.sex == 0) {
				html += '<td>男</td>';
			} else {
				html += '<td>女</td>';
			}

			html += '<td>' + item.age + '</td>';

			// 经理
			if (item.position == 1) {
				html += '<td>经理</td>';
			} else {
				html += '<td>职员</td>';
			}

			html += '<td>';
			html += '<button type="button" class="btn btn-info" onclick="app.updateUser(' + idx + ')">修改</button>';
			html += ' ';
			html += '<button type="button" class="btn btn-warning" onclick="app.deleteUser(' + item.id + ')">刪除</button>';
			html += '</td>';

			html += '</tr>';
		});
		$("tbody").html(html);

		if (app.page == 1) {
			pageHtml += '<li class="disabled" ><a href="#">&laquo;</a></li>';
		} else {
			pageHtml += '<li><a href="#" onclick="app.queryUser(' + (app.page - 1) + ',10)">&laquo;</a></li>';
		}
		for (var i = 0; i < app.total; i++) {

			if (app.page == (i + 1)) {
				pageHtml += '<li class="active" ><a href="#">' + (i + 1) + '</a></li>';
			} else {
				pageHtml += '<li ><a href="#" onclick="app.queryUser(' + (i + 1) + ',10)">' + (i + 1) + '</a></li>';
			}
		}
		if (app.page >= app.total) {
			pageHtml += '<li class="disabled" ><a href="#">&raquo;</a></li>';
		} else {
			pageHtml += '<li><a href="#" onclick="app.queryUser(' + (app.page + 1) + ',10)">&raquo;</a></li>';
		}

		$(".pagination").html(pageHtml);
	},

	// 查询用户信息
	queryUser: function(page, pageSize) {

		var queryText = $("#queryText").val();

		$.ajax({
			type: 'GET',
			url: this.serverUrl + '/user/pageQuery',
			data: {
				page,
				pageSize,
				queryText
			},
			success: (result) => {
				console.log(result.data);
				app.userList = result.data.rows;
				app.page = result.data.page;
				app.total = result.data.total;
				app.listFor()
			}
		})

	},

	// 校验数据
	checkInfo: function(item) {

		var code = item.code
		var name = item.name
		var age = item.age

		if (code == null || code == "" || code == undefined) {
			app.errorMsg = "员工代码不能为空"
			return false;
		}

		if (name == null || name == "" || name == undefined) {
			app.errorMsg = "员工姓名不能为空"
			return false;
		}
		// 校验年龄
		if (age == null || age == "" || age == undefined) {
			app.errorMsg = "年龄不能为空"
			return false;
		}
		var reg = /^(?:[1-9][0-9]?|1[01][0-9]|120)$/; //年龄是1-120之间有效
		if (!reg.test(age)) {
			app.errorMsg = "年龄必须在1~120之间"
			return false;
		}

		return true;
	}
};
