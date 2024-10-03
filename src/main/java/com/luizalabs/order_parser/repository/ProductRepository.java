package com.luizalabs.order_parser.repository;

import com.luizalabs.order_parser.entity.ProductSoldModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductSoldModel, Long> {
}
