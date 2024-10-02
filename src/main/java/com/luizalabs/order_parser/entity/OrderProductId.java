package com.luizalabs.order_parser.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class OrderProductId implements Serializable {
    @Column(name = "order_id", columnDefinition = "INT(11)")
    private Long orderId;

    @Column(name = "product_id", columnDefinition = "INT(11)")
    private Long productId;
}
