package com.luizalabs.order_parser.service;

import com.luizalabs.order_parser.dto.ResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface OrderParserService {

    ResponseDto processFile(MultipartFile file);
    Page<ResponseDto> getAll(Pageable pagination);
}
