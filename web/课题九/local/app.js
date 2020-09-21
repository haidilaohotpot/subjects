window.app = {
	serverUrl: "http://localhost:8089/sell/",
	userInfo: {},
	userIsLogin: false,
	cookieDomain: "",
	ctx: "",

	getUrlParam: function(paramName) {
		var reg = new RegExp("(^|&)" + paramName + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
		var r = window.location.search.substr(1).match(reg); //匹配目标参数
		if (r != null) return decodeURI(r[2]);
		return null; //返回参数值
	},

	getCookie: function(cname) {
		var name = cname + "=";
		var ca = document.cookie.split(';');
		for (var i = 0; i < ca.length; i++) {
			var c = ca[i];
			// console.log(c)
			while (c.charAt(0) == ' ') c = c.substring(1);
			if (c.indexOf(name) != -1) {
				return c.substring(name.length, c.length);
			}
		}
		return "";
	},


	setCookie: function(name, value) {
		var Days = 365;
		var exp = new Date();
		exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
		var cookieContent = name + "=" + encodeURIComponent(value) + ";path=/;";
		if (this.cookieDomain != null && this.cookieDomain != undefined && this.cookieDomain != '') {
			cookieContent += "domain=" + this.cookieDomain;
		}
		document.cookie = cookieContent + cookieContent;
		// document.cookie = name + "="+ encodeURIComponent (value) + ";path=/;domain=" + cookieDomain;//expires=" + exp.toGMTString();
	},


	deleteCookie: function(name) {
		document.cookie = name + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
	},


	logout: function() {
		axios.defaults.withCredentials = true;
		axios.post(app.serverUrl + 'user/logout').then(function(response) {
			console.log(response)
			if (response.data.status == 200) {
				app.deleteCookie('user')
				window.location.href = 'index.html';
			} else {
				alert(response.data.msg);
			}
		});
	}

}
