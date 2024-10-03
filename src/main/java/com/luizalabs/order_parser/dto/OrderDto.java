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
public class OrderDto {
    @JsonProperty("order_id")
    public int orderId;

    @JsonProperty("total")
    public String total;

    @JsonProperty("date")
    public String date;

    @JsonProperty("products")
    public List<ProductDto> products;
}
