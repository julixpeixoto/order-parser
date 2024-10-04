package com.luizalabs.order_parser.mock;

import com.luizalabs.order_parser.entity.OrderModel;
import com.luizalabs.order_parser.entity.ProductSoldModel;
import com.luizalabs.order_parser.entity.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderParserMock {
    public static UserModel getValidUser() {
        return UserModel.builder()
                .id(14L)
                .name("John Doe")
                .build();
    }

    public static Page<UserModel> getUsersPage() {
        List<UserModel> users = List.of(UserModel.builder()
                .id(14L)
                .orders(List.of(getOrderWithProducts()))
                .name("John Doe")
                .build());

        return new PageImpl<>(users, PageRequest.of(0, 1), users.size());
    }

    public static OrderModel getValidOrder() {
        return OrderModel.builder()
                .id(1L)
                .user(getValidUser())
                .orderAt(LocalDateTime.now())
                .build();
    }

    public static OrderModel getOrderWithProducts() {
        return OrderModel.builder()
                .id(1L)
                .user(getValidUser())
                .orderAt(LocalDateTime.now())
                .products(List.of(getValidProduct()))
                .build();
    }

    public static ProductSoldModel getValidProduct() {
        return ProductSoldModel.builder()
                .order(getValidOrder())
                .productId(1L)
                .soldValue(BigDecimal.valueOf(100))
                .build();
    }
}
