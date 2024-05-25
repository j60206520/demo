function login() {
	var url = "/login";
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;

	if (username == "" || password == "") {
		alert("帳號或密碼必填");
		return
	}

	$.ajax({
		type: "POST",
		url: url,
		data: JSON.stringify({
			username: username,
			password: password
		}),
		contentType: "application/json;charset=utf8",
		dataType: "text",
		success: function(text) {
			console.log("text: " + text);
			if (text === "success") {
				window.location.href = "/homePage";
			} else {
				window.alert(text);
			}
		},
		error: function(xhr, status) {
			console.log("Status: " + status);
			console.log(xhr);
			window.alert("請求失败");
		}
	});
};

function register() {
	var url = "/register";
	var fullName = document.getElementById("fullName").value;
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;

	if (username == "" || password == "" || fullName == "") {
		alert("姓名 帳號 密碼 皆為必填");
		return
	}

	$.ajax({
		type: "POST",
		url: url,
		data: JSON.stringify({
			fullName: fullName,
			username: username,
			password: password
		}),
		contentType: "application/json;charset=utf8",
		dataType: "text",
		success: function(text, status) {
			console.log("Status: " + status);
			if (text === "success") {
				window.location.href = "/homePage";
			} else {
				window.alert(text);
			}
		},
		error: function(xhr, status) {
			console.log("Status: " + status);
			console.log(xhr);
			window.alert("請求失败");
		}
	});
};

function remove() {
	var url = "/remove";
	var username = document.getElementById("username").innerText;
	var password = document.getElementById("password").innerText;
	$.ajax({
		type: "POST",
		url: url,
		data: JSON.stringify({
			username: username,
			password: password
		}),
		contentType: "application/json;charset=utf8",
		dataType: "text",
		success: function(text, status) {
			console.log("Status: " + status);
			if (text === "success") {
				window.alert("刪除成功");
				window.location.href = "/loginPage";
			} else {
				window.alert(text);
			}
		},
		error: function(xhr, status) {
			console.log("Status: " + status);
			console.log(xhr);
			window.alert("請求失败");
		}
	});
};

function edit() {
	var url = "/edit";
	var username = document.getElementById("username").innerText;
	var password = document.getElementById("password").innerText;
	var fullName = document.getElementById("fullName").value;

	$.ajax({
		type: "POST",
		url: url,
		data: JSON.stringify({
			username: username,
			password: password,
			fullName: fullName
		}),
		contentType: "application/json;charset=utf8",
		dataType: "text",
		success: function(text, status) {
			console.log("Status: " + status);
			if (text === "success") {
				window.alert("修改成功");
				window.location.href = "/loginPage";
			} else {
				window.alert(text);
			}
		},
		error: function(xhr, status) {
			console.log("Status: " + status);
			console.log(xhr);
			window.alert("請求失败");
		}
	});
};


function loginPage() {
	var url = "/loginPage";
	$.ajax({
		type: "Get",
		url: url,
		dataType: "html",
		success: function(html, status) {
			console.log("Status: " + status);
			$("body").html(html);
		},
		error: function(xhr, status) {
			console.log("Status: " + status);
			console.log(xhr);
			window.alert("請求失败");
		}
	});
};

function registerPage() {
	var url = "/registerPage";
	$.ajax({
		type: "Get",
		url: url,
		dataType: "html",
		success: function(html, status) {
			console.log("Status: " + status);
			$("body").html(html);
		},
		error: function(xhr, status) {
			console.log("Status: " + status);
			console.log(xhr);
			window.alert("請求失败");
		}
	});
};

function orderSearchPage() {
	var username = document.getElementById('username').innerText;
	var url = "/orderSearchPage";
	$.ajax({
		type: "Get",
		url: url,
		data: { username: username },
		dataType: "html",
		success: function(html, status) {
			console.log("Status: " + status);
			$("body").html(html);
		},
		error: function(xhr, status) {
			console.log("Status: " + status);
			console.log(xhr);
			window.alert("請求失败");
		}
	});
};
function orderStatistics() {
	var statisticsNumber = document.getElementById('statisticsNumber').value;
	if (statisticsNumber == "") {
		alert("統計數字不能為空");
		return;
	}
	var resultsDiv = document.getElementById('results');
	resultsDiv.innerHTML = ''; // 清空之前的結果
	
	var url = "/orderStatistics";
	$.ajax({
		type: "POST",
		url: url,
		data: JSON.stringify({
		statisticsNumber: statisticsNumber
		}),
		contentType: "application/json;charset=utf8",
		dataType: 'json',
		success: function(members) {
			console.log(members)
			if (members.length > 0) {
				members.forEach(function(member) {
		            var resultItem = document.createElement('div');
		            resultItem.textContent = `客戶姓名: ${member.fullName}, 帳號: ${member.username}`;
		            resultsDiv.appendChild(resultItem);
				});
			} else {
				resultsDiv.innerText = '未找到符合條件的訂單';
			}
		},
		error: function(xhr, status) {
			console.log("Status: " + status);
			console.log(xhr);
			window.alert("請求失败");
		}
	});
};
