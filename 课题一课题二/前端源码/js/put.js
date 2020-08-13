window.app = {
	serverUrl: "http://localhost:8088",
	userJsonData: [],
	userList: [],
	page: 1,
	total: 1,

	upOrCre: function() {
		var item = {};
		item.name = $("#name").val();
		item.code = $("#code").val();
		item.age = $("#age").val();
		item.sex =$("#sex").find("option:selected").val();
		var p = $("#position").find("option:selected").val();
		var userId = $("#userId").val();
		item.position = p;
		var userId = $("#userId").val();
		item.id = userId;
		var idx = $("#idx").val()
		console.log(item)
		if (userId == -1 || undefined == userId || "" == userId) {
			app.userList.push(item);
			app.build(item, "add");
		} else {
			app.userList.splice(idx, 1, item)
			app.build(item, "update");
		}

	},

	// 新增一个用户
	createUser: function(item) {
		var method = "add";
		app.build(item, method);
	},

	build: function(item, method) {
		// 代码
		var code = item.code;
		// 姓名
		var name = item.name;
		// 性别
		var sex = item.sex;
		// 年龄
		var age = item.age;
		// 职位
		var position = item.position;
		var id = item.id;
		
		item.method = method;
		app.userJsonData.push(item);
		console.log(app.userJsonData)
		$('#save').removeAttr("disabled");
		$("#name").val("");
		$("#code").val("");
		$("#age").val("");
		$("#sex").val(0);
		$("#position").val(1);
		$("#userId").val(-1);
		app.listFor();
	},
	// 删除用户
	deleteUser: function(idx) {
		console.log(idx)
		var item = app.userList[idx];
		console.log(item);
		app.userList.splice(idx, 1);
		app.build(item, "delete");
	},
	// 修改用户信息
	updateUser: function(idx) {
		var item = app.userList[idx];
		console.log(item);
		$("#userId").val(item.id);
		$("#name").val(item.name);
		$("#code").val(item.code);
		$("#age").val(item.age);
		var sex = item.sex;
		$("#sex").val(sex);
		$("#idx").val(idx);
		$("#position").val(item.position);
		$("#myModal").modal("show");
	},

	// 保存
	put: function() {
		
		console.log(JSON.stringify(app.userJsonData))
		
		// 发送请求
		$.ajax({
			type: 'POST',
			// contentType:'application/json',
			url: app.serverUrl + '/user/doService',
			data:{
				userJsonData:JSON.stringify(app.userJsonData)
			},
			success: function(result) {
				console.log(result);
				window.location.href = "index.html"
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
			// var itemStr = JSON.stringify(item).replace(/\"/g,"'");


			html += '<td>';
			html += '<button type="button" class="btn btn-info" onclick="app.updateUser(' + idx + ')">修改</button>';
			html += '<button type="button" class="btn btn-warning" onclick="app.deleteUser(' + idx + ')">刪除</button>';
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

		var disabled = $("#save").attr("disabled");

		if(undefined == disabled){
			// alert("请先保存")
			var saveFlag = $("#saveFlag");
			
			var htmlF = '';
			htmlF+='<div class="alert alert-warning" >';
			htmlF+='<a href="#" class="close" data-dismiss="alert">';
			htmlF+='&times;';
			htmlF+='</a>';
			htmlF+='<strong>警告！</strong>请先保存本页数据。';
			htmlF+='</div>';
			saveFlag.html(htmlF);
			return false
		}
		console.log(disabled)

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
				app.userList = result.data;
				app.page = result.page;
				app.total = result.total;
				app.listFor()

			}
		})

	},
};
