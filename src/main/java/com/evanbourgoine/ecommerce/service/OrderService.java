package com.evanbourgoine.ecommerce.service;

import com.evanbourgoine.ecommerce.exception.ResourceNotFoundException;
import com.evanbourgoine.ecommerce.model.*;
import com.evanbourgoine.ecommerce.repository.OrderRepository;
import com.evanbourgoine.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Order placeOrder(String customerName, String customerEmail,
        List<OrderRequest> orderRequests) {
            
            Order order = Order.builder()
                .customerName(customerName)
                .customerEmail(customerEmail)
                .totalAmount(BigDecimal.ZERO)
                .build();

            BigDecimal total = BigDecimal.ZERO;

            for (OrderRequest request : orderRequests) {
                Product product = productRepository.findById(request.productId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + request.productId()));

                if (product.getStockQuantity() < request.quantity()) {
                    throw new IllegalStateException(
                        "Insufficient stock for product: " + product.getName() + 
                        ". Available: " + product.getStockQuantity() +
                        ", Requested: " + request.quantity());
                }

            product.setStockQuantity(product.getStockQuantity() - request.quantity());
            productRepository.save(product);

            OrderItem item = OrderItem.builder()
                .order(order)
                .product(product)
                .quantity(request.quantity())
                .unitPrice(product.getPrice())
                .build();

            order.getItems().add(item);
            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(request.quantity())));
            }

            order.setTotalAmount(total);
            return orderRepository.save(order);
        }

        public List<Order> getOrdersByEmail(String email) {
            return orderRepository.findByCustomerEmail(email);
        }

        public Order getOrderById(Long id) {
            return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Order not found with id: " + id));
        }

        public List<Order> getOrdersByStatus(OrderStatus status) {
            return orderRepository.findByStatus(status);
        }

        public record OrderRequest(Long productId, Integer quantity) {}
}