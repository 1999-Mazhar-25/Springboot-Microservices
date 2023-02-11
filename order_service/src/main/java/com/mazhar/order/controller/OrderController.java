package com.mazhar.order.controller;

import com.mazhar.order.dto.OrderRequest;
import com.mazhar.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/placeOrder")
    public String placeOrder(@RequestBody OrderRequest orderResquest){
        orderService.placeOrder(orderResquest);
        return "Ordered place successfully";
    }
}
