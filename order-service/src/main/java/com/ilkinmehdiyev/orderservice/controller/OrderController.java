package com.ilkinmehdiyev.orderservice.controller;

import com.ilkinmehdiyev.orderservice.controller.response.OrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @PostMapping("/makeOrder")
    public ResponseEntity<OrderResponse> makeOrder(){
        UUID uuid = UUID.randomUUID();
        log.info("Starting to create a new Order. UUID: {}", uuid);
        return ResponseEntity.ok(new OrderResponse(uuid,"Order have been created!!!"));
    }
}
