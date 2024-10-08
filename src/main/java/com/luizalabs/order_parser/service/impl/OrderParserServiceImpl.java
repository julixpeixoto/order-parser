package com.luizalabs.order_parser.service.impl;

import com.luizalabs.order_parser.dto.ResponseDto;
import com.luizalabs.order_parser.entity.OrderModel;
import com.luizalabs.order_parser.entity.ProductSoldModel;
import com.luizalabs.order_parser.entity.UserModel;
import com.luizalabs.order_parser.exception.ProcessFileException;
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
import java.util.*;

@Service
@Slf4j
public class OrderParserServiceImpl implements OrderParserService {
    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public OrderParserServiceImpl(UserRepository userRepository,
                                  OrderRepository orderRepository,
                                  ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void processFile(MultipartFile file) {
        try {
            log.info("Processing file");
            List<String> lines = getLines(file);

            if (!lines.isEmpty()) {
                List<String> uniqueUsers = getUniqueUsers(lines);
                uniqueUsers.forEach(this::insertUser);
                lines.forEach(this::insertOrder);
                lines.forEach(this::insertProduct);
            }

            log.info("File processed");
        } catch (Exception e) {
            throw new ProcessFileException("Error while processing file... " + e.getMessage());
        }
    }

    @Override
    public Page<ResponseDto> getAll(Pageable pagination) {
        Page<UserModel> users = userRepository.findByOrderById(pagination);
        return users.map(add -> modelMapper.map(add, ResponseDto.class));
    }

    private List<String> getLines(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            var lines = Arrays.asList(reader.lines().toList().toArray(new String[0]));
            lines.sort(Comparator.comparing(line -> line.substring(0, 10).trim()));
            return lines;
        } catch (IOException e) {
            throw new ProcessFileException("Error while reading file: " + e.getMessage());
        }
    }

        @Transactional
        private void insertUser (String line){
            try {
                UserModel user = UserModel.builder()
                        .id(Long.valueOf(line.substring(0, 10).trim()))
                        .name(line.substring(10, 55).trim())
                        .build();
                userRepository.save(user);
            } catch (Exception e) {
                throw new ProcessFileException("Error while inserting user: " + e.getMessage());
            }
        }

        @Transactional
        private void insertOrder (String line){
            try {
                Long userId = Long.valueOf(line.substring(0, 10).trim());
                OrderModel order = OrderModel.builder()
                        .id(Long.valueOf(line.substring(55, 65).trim()))
                        .user(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found by id: " + userId)))
                        .orderAt(DateUtil.formatStringToDate(line.substring(87, 95).trim()).atStartOfDay())
                        .build();
                orderRepository.save(order);
            } catch (Exception e) {
                throw new ProcessFileException("Error while inserting order: " + e.getMessage());
            }
        }

        @Transactional
        private void insertProduct (String line){
            try {
                Long productId = Long.valueOf(line.substring(65, 75).trim());
                ProductSoldModel product = ProductSoldModel.builder()
                        .order(orderRepository.findById(Long.valueOf(line.substring(55, 65).trim()))
                                .orElseThrow(() -> new RuntimeException("Order not found by id: " + line.substring(55, 65).trim())))
                        .productId(productId)
                        .soldValue(BigDecimal.valueOf(Float.valueOf(line.substring(75, 87).trim())))
                        .build();
                productRepository.save(product);
            } catch (Exception e) {
                throw new ProcessFileException("Error while inserting product: " + e.getMessage());
            }
        }

        private List<String> getUniqueUsers(List<String> lines) {
            List<String> uniqueUsers = new ArrayList<>();

            lines.forEach(line -> {
                String userIdentifier = line.substring(0, 55).trim();
                if (!uniqueUsers.contains(userIdentifier)) {
                    uniqueUsers.add(userIdentifier);
                }
            });

            return uniqueUsers;
        }
    }
