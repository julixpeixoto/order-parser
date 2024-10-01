package com.luizalabs.order_parser.service.impl;

import com.luizalabs.order_parser.service.OrderParserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderParserServiceImpl implements OrderParserService {
    @Override
    public void processFile() {
        log.info("Processing file");
        // TODO: implement file processing
    }
}
