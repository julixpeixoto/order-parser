package com.luizalabs.order_parser.controller;

import com.luizalabs.order_parser.dto.ResponseDto;
import com.luizalabs.order_parser.service.OrderParserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/all")
    public ResponseEntity<Page<ResponseDto>> getAll(@PageableDefault(size = 10) Pageable pagination) {
        Page<ResponseDto> response = orderParserService.getAll(pagination);
        return ResponseEntity.ok(response);
    }
}
