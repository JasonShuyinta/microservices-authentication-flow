package com.dotjson.controller;

import com.dotjson.dto.OrderResponseDTO;
import com.dotjson.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interview")
public class InterviewController {

    @Autowired
    private InterviewService service;

    @GetMapping
    public String greetingMessage() {
        return service.greeting();
    }

    @GetMapping("/orders/status/{orderId}")
    public OrderResponseDTO getOrder(@PathVariable String orderId) {
        return service.getOrder(orderId);
    }
}
