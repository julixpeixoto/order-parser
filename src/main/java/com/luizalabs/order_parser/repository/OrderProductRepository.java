package com.luizalabs.order_parser.repository;

import com.luizalabs.order_parser.entity.OrderProductId;
import com.luizalabs.order_parser.entity.OrderProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProductModel, OrderProductId> {
}
