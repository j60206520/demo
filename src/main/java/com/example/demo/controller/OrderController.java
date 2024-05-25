package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Member;
import com.example.demo.entity.OrderData;
import com.example.demo.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("orderSearchPage")
	public String orderSearchPage(@RequestParam("username") String username, HttpServletRequest req) {
		req.getSession().setAttribute("username", username);
		return "orderSearchPage";
	}

	@PostMapping("orderSearch")
	@ResponseBody
	public List<OrderData> orderSearch(@RequestBody OrderData orderData) {
		System.out.println(orderData.getUsername());
		System.out.println(orderData.getProductName());
		System.out.println(orderData.getOrderId());
		System.out.println(orderData.getPurchaseDate());
		
		return orderService.searchOrders(orderData);

	}
	
	@PostMapping("orderStatistics")
	@ResponseBody
	public List<Member> orderStatistics(@RequestBody Map<String, String> rspMap) {
		System.out.println(rspMap.get("statisticsNumber"));
		Integer statisticsNumber = Integer.valueOf(rspMap.get("statisticsNumber"));
		return orderService.searchOrderStatistics(statisticsNumber);

	}

}
