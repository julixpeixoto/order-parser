package com.luizalabs.order_parser.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Entity
@Table(name = "order")
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {
    @Id
    private Integer id;

    private LocalDateTime orderAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="order_product",
            joinColumns = {
                    @JoinColumn(name="order_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name="product_id", referencedColumnName = "id")
            }
    )
    private Set<ProductModel> products = new HashSet<>();

    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
