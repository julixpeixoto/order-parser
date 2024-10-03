package com.luizalabs.order_parser.controller;

import com.luizalabs.order_parser.dto.ResponseDto;
import com.luizalabs.order_parser.service.OrderParserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Upload do arquivo de pedidos",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json"))
            }
    )
    @PostMapping(value = "/process-file", consumes = "multipart/form-data")
    public ResponseEntity<ResponseDto> processFile(@RequestParam("file") MultipartFile file) {
        ResponseDto responseDto = orderParserService.processFile(file);
        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "Listagem pedidos paginados",
            parameters = {
                    @Parameter(name = "page", description = "Número da página", schema = @Schema(type = "integer")),
                    @Parameter(name = "size", description = "Tamanho da página", schema = @Schema(type = "integer"))
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pedidos encontrados", content = @Content(mediaType = "application/json"))
            }
    )
    @GetMapping("/all")
    public ResponseEntity<Page<ResponseDto>> getAll(@PageableDefault(size = 10) Pageable pagination, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sort) {
        Page<ResponseDto> response = orderParserService.getAll(pagination);
        return ResponseEntity.ok(response);
    }
}
