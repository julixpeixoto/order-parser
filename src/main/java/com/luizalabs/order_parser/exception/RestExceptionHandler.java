package com.luizalabs.order_parser.exception;

import com.luizalabs.order_parser.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
@Slf4j
public class RestExceptionHandler {
    @ExceptionHandler(value = { ProcessFileException.class })
    protected ResponseEntity<Object> handleNotPermittedOperationException(ProcessFileException ex) {
        log.error("Error while processing file: {}", ex.getMessage(), ex);
        MessageDto message = MessageDto.builder().message(ex.getMessage()).build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
