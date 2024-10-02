package com.luizalabs.order_parser.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "tb_product")
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
    @Id
    @Column(name = "id", columnDefinition = "INT(11)")
    private Long id;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<OrderProductModel> orderProducts = new HashSet<>();

    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
