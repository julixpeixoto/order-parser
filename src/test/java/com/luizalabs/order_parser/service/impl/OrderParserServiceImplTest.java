package com.luizalabs.order_parser.service.impl;

import com.luizalabs.order_parser.exception.ProcessFileException;
import com.luizalabs.order_parser.mock.FileMock;
import com.luizalabs.order_parser.mock.OrderParserMock;
import com.luizalabs.order_parser.repository.OrderRepository;
import com.luizalabs.order_parser.repository.ProductRepository;
import com.luizalabs.order_parser.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderParserServiceImplTest {
    @InjectMocks
    private OrderParserServiceImpl service;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Process file with success")
    void testProcessFileSuccess() throws IOException {
        MultipartFile file = FileMock.getValidFile();
        when(userRepository.save(any())).thenReturn(OrderParserMock.getValidUser());
        when(userRepository.findById(any())).thenReturn(Optional.of(OrderParserMock.getValidUser()));
        when(orderRepository.save(any())).thenReturn(OrderParserMock.getValidOrder());
        when(orderRepository.findById(any())).thenReturn(Optional.of(OrderParserMock.getValidOrder()));
        when(productRepository.save(any())).thenReturn(OrderParserMock.getValidProduct());
        Assertions.assertDoesNotThrow(() -> service.processFile(file));
    }

    @Test
    @DisplayName("Process file with empty file")
    void testProcessFileEmptyFile() throws IOException {
        MultipartFile file = FileMock.getEmptyFile();
        when(userRepository.save(any())).thenReturn(OrderParserMock.getValidUser());
        when(userRepository.findById(any())).thenReturn(Optional.of(OrderParserMock.getValidUser()));
        when(orderRepository.save(any())).thenReturn(OrderParserMock.getValidOrder());
        when(orderRepository.findById(any())).thenReturn(Optional.of(OrderParserMock.getValidOrder()));
        when(productRepository.save(any())).thenReturn(OrderParserMock.getValidProduct());
        Assertions.assertDoesNotThrow(() -> service.processFile(file));
    }

    @Test
    @DisplayName("Process file with invalid file")
    void testProcessFileInvalidFile() throws IOException {
        MultipartFile file = FileMock.getInvalidFile();
        when(userRepository.save(any())).thenReturn(OrderParserMock.getValidUser());
        when(userRepository.findById(any())).thenReturn(Optional.of(OrderParserMock.getValidUser()));
        when(orderRepository.save(any())).thenReturn(OrderParserMock.getValidOrder());
        when(orderRepository.findById(any())).thenReturn(Optional.of(OrderParserMock.getValidOrder()));
        when(productRepository.save(any())).thenReturn(OrderParserMock.getValidProduct());
        Assertions.assertThrows(ProcessFileException.class, () -> service.processFile(file));
    }

    @Test
    @DisplayName("Process file with IO exception")
    void testProcessFileIOException() throws IOException {
        MultipartFile file = mock(MultipartFile.class);
        when(file.getInputStream()).thenThrow(new IOException("Mocked I/O error"));
        when(userRepository.save(any())).thenReturn(OrderParserMock.getValidUser());
        when(userRepository.findById(any())).thenReturn(Optional.of(OrderParserMock.getValidUser()));
        when(orderRepository.save(any())).thenReturn(OrderParserMock.getValidOrder());
        when(orderRepository.findById(any())).thenReturn(Optional.of(OrderParserMock.getValidOrder()));
        when(productRepository.save(any())).thenReturn(OrderParserMock.getValidProduct());
        Assertions.assertThrows(ProcessFileException.class, () -> service.processFile(file));
    }

    @Test
    @DisplayName("Process file with user not found")
    void testProcessFileUserNotFound() throws IOException {
        MultipartFile file = FileMock.getValidFile();
        when(userRepository.save(any())).thenReturn(OrderParserMock.getValidUser());
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        when(orderRepository.save(any())).thenReturn(OrderParserMock.getValidOrder());
        when(orderRepository.findById(any())).thenReturn(Optional.of(OrderParserMock.getValidOrder()));
        when(productRepository.save(any())).thenReturn(OrderParserMock.getValidProduct());
        Assertions.assertThrows(ProcessFileException.class, () -> service.processFile(file));
    }

    @Test
    @DisplayName("Process file with order not found")
    void testProcessFileOrderNotFound() throws IOException {
        MultipartFile file = FileMock.getValidFile();
        when(userRepository.save(any())).thenReturn(OrderParserMock.getValidUser());
        when(userRepository.findById(any())).thenReturn(Optional.of(OrderParserMock.getValidUser()));
        when(orderRepository.save(any())).thenReturn(OrderParserMock.getValidOrder());
        when(orderRepository.findById(any())).thenReturn(Optional.empty());
        when(productRepository.save(any())).thenReturn(OrderParserMock.getValidProduct());
        Assertions.assertThrows(ProcessFileException.class, () -> service.processFile(file));
    }

    @Test
    @DisplayName("Get all orders")
    void testGetAllOrders() {
        when(userRepository.findByOrderById(any())).thenReturn(OrderParserMock.getUsersPage());
        Assertions.assertNotNull(service.getAll(any()));
    }
}