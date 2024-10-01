package com.luizalabs.order_parser.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseDto {
    @JsonProperty("user_id")
    public Integer userId;

    @JsonProperty("name")
    public String name;

    @JsonProperty("orders")
    public List<OrderDto> orders;
}
