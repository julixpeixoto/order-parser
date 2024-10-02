package com.luizalabs.order_parser.service.impl;

import com.luizalabs.order_parser.dto.ResponseDto;
import com.luizalabs.order_parser.service.OrderParserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class OrderParserServiceImpl implements OrderParserService {
    @Override
    public ResponseDto processFile(MultipartFile file) {
        log.info("Processing file");
        // TODO: implement file processing
        return null;
    }
}
