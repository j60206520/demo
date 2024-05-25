package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.OrderRepository;
import com.example.demo.entity.Member;
import com.example.demo.entity.OrderData;

@Service
public class OrderService {
	
    @Autowired
    private OrderRepository orderRepository;

    public List<OrderData> searchOrders(OrderData orderData) {
    	return orderRepository.searchOrders(orderData);
    }
    
    public List<Member> searchOrderStatistics(Integer statisticsNumber) {
    	return orderRepository.searchOrderStatistics(statisticsNumber);
    }
}
