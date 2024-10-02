package com.luizalabs.order_parser.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "tb_order_product")
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductModel {
    @EmbeddedId
    private OrderProductId id = new OrderProductId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private OrderModel order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ProductModel product;

    @Column(name = "product_sold_value")
    private BigDecimal productSoldValue;

    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
