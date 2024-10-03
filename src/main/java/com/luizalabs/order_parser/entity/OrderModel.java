package com.luizalabs.order_parser.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "tb_order")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderModel {
    @Id
    @Column(name = "id", columnDefinition = "INT(11)")
    private Long id;

    private LocalDateTime orderAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserModel user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<ProductSoldModel> products = new ArrayList<>();

    @CreationTimestamp
    @Column(updatable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public BigDecimal getTotal() {
        return products.stream().map(ProductSoldModel::getSoldValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String getDate() {
        return orderAt.toLocalDate().toString();
    }
}
