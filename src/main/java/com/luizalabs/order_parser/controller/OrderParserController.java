package com.luizalabs.order_parser.controller;

import com.luizalabs.order_parser.dto.ResponseDto;
import com.luizalabs.order_parser.service.OrderParserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/order-parser")
public class OrderParserController {

    private final OrderParserService orderParserService;

    public OrderParserController(OrderParserService orderParserService) {
        this.orderParserService = orderParserService;
    }

    @PostMapping("/process-file")
    public ResponseEntity<ResponseDto> processFile(@RequestParam("file") MultipartFile file) {
        ResponseDto responseDto = orderParserService.processFile(file);
        return ResponseEntity.ok(responseDto);
    }
}
