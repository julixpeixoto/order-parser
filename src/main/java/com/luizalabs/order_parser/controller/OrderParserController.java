package com.luizalabs.order_parser.controller;

import com.luizalabs.order_parser.service.OrderParserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/order-parser")
public class OrderParserController {

    private final OrderParserService orderParserService;

    public OrderParserController(OrderParserService orderParserService) {
        this.orderParserService = orderParserService;
    }

    @PostMapping("/process-file")
    public ResponseEntity<?> index() {
        orderParserService.processFile();
        return ResponseEntity.ok("File to be processed");
    }
}
