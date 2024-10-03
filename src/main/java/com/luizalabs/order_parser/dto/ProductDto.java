package com.luizalabs.order_parser.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDto {
    @JsonProperty("product_id")
    public Integer productId;

    @JsonProperty("value")
    public String value;
}
