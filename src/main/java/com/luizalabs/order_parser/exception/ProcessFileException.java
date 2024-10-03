package com.luizalabs.order_parser.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ProcessFileException extends RuntimeException {

    private final String message;
}
