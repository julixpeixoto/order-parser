package com.luizalabs.order_parser.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "tb_user")
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    @Id
    @Column(name = "id", columnDefinition = "INT(11)")
    private Long id;

    private String name;

    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
