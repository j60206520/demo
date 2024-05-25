function searchOrders() {
	var username = document.getElementById('username').innerText;
	var orderId = document.getElementById('orderId').value;
	var productName = document.getElementById('productName').value;
	var purchaseDate = document.getElementById('purchaseDate').value;

	if (orderId == "" && productName == "" && purchaseDate == "") {
		alert("至少填一個查詢項目");
		return;
	}

	var resultsDiv = document.getElementById('results');
	resultsDiv.innerHTML = ''; // 清空之前的結果

	var url = "/orderSearch";

	$.ajax({
		type: 'POST',
		url: url,
		data: JSON.stringify({
			username: username,
			orderId: orderId,
			productName: productName,
			purchaseDate: purchaseDate
		}),
		contentType: "application/json;charset=utf8",
		dataType: 'json',
		success: function(orders) {
			if (orders.length > 0) {
				orders.forEach(function(order) {
		            var resultItem = document.createElement('div');
		            resultItem.textContent = `訂單編號: ${order.orderId}, 產品名稱: ${order.productName}, 購買日期: ${order.purchaseDate}`;
		            resultsDiv.appendChild(resultItem);
				});
			} else {
				resultsDiv.innerText = '未找到符合條件的訂單';
			}
		},
		error: function(xhr, status) {
			console.log("Status: " + status);
			console.log(xhr);
			window.alert("請求失敗");
		}
	});
}