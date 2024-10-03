package com.luizalabs.order_parser.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "tb_product_sold")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductSoldModel {
    @Id
    @Column(name = "id", columnDefinition = "INT(11)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private BigDecimal soldValue;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderModel order;

    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
