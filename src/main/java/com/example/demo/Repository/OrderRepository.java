package com.example.demo.Repository;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Member;
import com.example.demo.entity.OrderData;

@Repository
public class OrderRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<OrderData> searchOrders(OrderData orderData) {
		String username = orderData.getUsername();
		String orderId = orderData.getOrderId();
		String productName = orderData.getProductName();
		LocalDate purchaseDate = orderData.getPurchaseDate();

		List<Object> parameters = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT * FROM order_Data WHERE 1=1 ");

		if (username != null && !username.isEmpty()) {
			sql.append("AND username = ? ");
			parameters.add(username);
		}
		if (orderId != null && !orderId.isEmpty()) {
			sql.append("AND orderId = ? ");
			parameters.add(orderId);
		}
		if (productName != null && !productName.isEmpty()) {
			sql.append("AND product_Name = ? ");
			parameters.add(productName);
		}
		if (purchaseDate != null) {
			sql.append("AND purchase_Date = ? ");
			parameters.add(purchaseDate);
		}

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql.toString(), parameters.toArray());

		List<OrderData> orderList = new ArrayList<>();
		for (Map<String, Object> row : rows) {
			OrderData order = new OrderData();
			order.setUsername((String) row.get("USERNAME"));
			order.setOrderId((String) row.get("ORDER_ID"));
			order.setProductName((String) row.get("PRODUCT_NAME"));
			
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    String dateString = dateFormat.format(row.get("PURCHASE_DATE"));
	        Date sqlDate = Date.valueOf(dateString);
	        LocalDate localDate = sqlDate.toLocalDate();
			order.setPurchaseDate(localDate);
			orderList.add(order);
		}
		return orderList;

	}
	
	public List<Member> searchOrderStatistics(Integer statisticsNumber) {

		List<Object> parameters = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT * FROM MEMBER WHERE USERNAME IN ( "
												+ "SELECT USERNAME FROM ( "
													+ "SELECT USERNAME, COUNT(*) AS order_count "
													+ "FROM ORDER_DATA GROUP BY USERNAME ) AS subquery "
												+ "WHERE order_count > ? ) ");

		parameters.add(statisticsNumber);

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql.toString(), parameters.toArray());

		List<Member> memberList = new ArrayList<>();
		for (Map<String, Object> row : rows) {
			Member member = new Member();
			member.setFullName((String) row.get("FULL_NAME"));
			member.setUsername((String) row.get("USERNAME"));
			memberList.add(member);
		}
		return memberList;

	}
}
