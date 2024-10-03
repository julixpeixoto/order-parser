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

@Data
@Builder
@Entity
@Table(name = "tb_order")
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {
    @Id
    @Column(name = "id", columnDefinition = "INT(11)")
    private Long id;

    private LocalDateTime orderAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserModel user;

    @CreationTimestamp
    @Column(updatable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
