package com.luizalabs.order_parser.repository;

import com.luizalabs.order_parser.entity.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
