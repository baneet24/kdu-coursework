package com.example.assessment2.service;

import com.example.assessment2.model.Orders;
import com.example.assessment2.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;


    @Autowired
    public OrderService(OrderRepository orderRepository) {
            this.orderRepository = orderRepository;
    }

    public void placeOrder(Orders order) {
        orderRepository.save(order);
    }

}
