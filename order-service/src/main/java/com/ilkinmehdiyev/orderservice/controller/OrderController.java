package com.ilkinmehdiyev.orderservice.controller;

import com.ilkinmehdiyev.orderservice.controller.response.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @PostMapping("/makeOrder")
    public ResponseEntity<OrderResponse> makeOrder(){
        return ResponseEntity.ok(new OrderResponse("Order have been created!!!"));
    }
}
