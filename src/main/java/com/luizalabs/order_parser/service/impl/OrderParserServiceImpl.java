package com.luizalabs.order_parser.service.impl;

import com.luizalabs.order_parser.dto.ResponseDto;
import com.luizalabs.order_parser.entity.*;
import com.luizalabs.order_parser.repository.OrderProductRepository;
import com.luizalabs.order_parser.repository.OrderRepository;
import com.luizalabs.order_parser.repository.ProductRepository;
import com.luizalabs.order_parser.repository.UserRepository;
import com.luizalabs.order_parser.service.OrderParserService;
import com.luizalabs.order_parser.util.DateUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class OrderParserServiceImpl implements OrderParserService {
    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final OrderProductRepository orderProductRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public OrderParserServiceImpl(UserRepository userRepository,
                                  OrderRepository orderRepository,
                                  ProductRepository productRepository,
                                  OrderProductRepository orderProductRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public ResponseDto processFile(MultipartFile file) {
        log.info("Processing file");
        List<String> lines = getLines(file);

        if (!lines.isEmpty()) {
            lines.forEach(this::insertUser);
            lines.forEach(this::insertOrder);
            lines.forEach(this::insertProduct);
            lines.forEach(this::insertOrderProduct);
        }

        log.info("File processed");
        return null;
    }

    @Override
    public Page<ResponseDto> getAll(Pageable pagination) {
        Page<UserModel> users = userRepository.findByOrderById(pagination);
        return users.map(add -> modelMapper.map(add, ResponseDto.class));
    }

    private List<String> getLines(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            return reader.lines().toList();
        } catch (IOException e) {
            log.error("Error while reading file: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    @Transactional
    private void insertUser(String line) {
        try {
            UserModel user = UserModel.builder()
                    .id(Long.valueOf(line.substring(0, 10).trim()))
                    .name(line.substring(10, 55).trim())
                    .build();
            userRepository.save(user);
        } catch (Exception e) {
            log.error("Error while inserting user: {}", e.getMessage());
        }
    }

    @Transactional
    private void insertOrder(String line) {
        try {
            Long userId = Long.valueOf(line.substring(0, 10).trim());
            OrderModel order = OrderModel.builder()
                    .id(Long.valueOf(line.substring(55, 65).trim()))
                    .user(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found by id: " + userId)))
                    .orderAt(DateUtil.formatStringToDate(line.substring(87, 95).trim()).atStartOfDay())
                    .build();
            orderRepository.save(order);
        } catch (Exception e) {
            log.error("Error while inserting order: {}", e.getMessage());
        }
    }

    @Transactional
    private void insertProduct(String line) {
        try {
            Long productId = Long.valueOf(line.substring(65, 75).trim());
            ProductModel product = ProductModel.builder()
                    .id(productId)
                    .build();
            productRepository.save(product);
        } catch (Exception e) {
            log.error("Error while inserting product: {}", e.getMessage());
        }
    }

    @Transactional
    private void insertOrderProduct(String line) {
        try {
            Long orderId = Long.valueOf(line.substring(55, 65).trim());
            Long productId = Long.valueOf(line.substring(65, 75).trim());
            Float productSoldValue = Float.valueOf(line.substring(75, 87).trim());

            OrderModel order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found by id: " + orderId));
            ProductModel product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found by id: " + productId));

            OrderProductModel orderProduct = OrderProductModel.builder()
                    .order(order)
                    .product(product)
                    .productSoldValue(BigDecimal.valueOf(productSoldValue))
                    .build();

            OrderProductId orderProductId = new OrderProductId();
            orderProductId.setOrderId(orderId);
            orderProductId.setProductId(productId);
            orderProduct.setId(orderProductId);

            orderProductRepository.save(orderProduct);
        } catch (Exception e) {
            log.error("Error while inserting order product: {}", e.getMessage());
        }
    }
}
