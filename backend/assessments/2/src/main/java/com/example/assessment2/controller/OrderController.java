package com.example.assessment2.controller;

import com.example.assessment2.model.Orders;
import com.example.assessment2.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class OrderController {
    private final OrderService orderService;


    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @PostMapping("/order")
    public ResponseEntity<String> placeOrder(@RequestBody Orders order){
        logger.info("placing order");
        orderService.placeOrder(order);
        return ResponseEntity.ok("product Added successfully!");
    }
}
