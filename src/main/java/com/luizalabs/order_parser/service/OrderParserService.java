package com.luizalabs.order_parser.service;

import com.luizalabs.order_parser.dto.ResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface OrderParserService {

    ResponseDto processFile(MultipartFile file);
}
