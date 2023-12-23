package com.imanali.SpringQuickStart.api.controller;

import com.imanali.SpringQuickStart.model.Order;
import com.imanali.SpringQuickStart.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/orders")
@PreAuthorize("hasRole('CUSTOMER')")
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public Order newOrder(@RequestBody Order order) throws Exception {
       Order savedOrder = orderService.createOrder(order);
       return savedOrder;
    }
}
