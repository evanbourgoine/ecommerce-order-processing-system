package com.evanbourgoine.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

// JPA Entity File: Java class that maps directly
// to a database table.

// -------  ANNOTATIONS -------
@Entity // tells Spring this class represents a database table
@Table(name = "products") // specifies exact table name.
@Data // Lombok generates all getters, setters, equals, hashCode, toString methods at compile time.
@NoArgsConstructor
@AllArgsConstructor
@Builder // Lombok generates a builder pattern so you can create a product without 8 parameters
// instead, you can Product product = Product.builder().name("Widget").price(new BigDecimal("19.99")).build();


public class Product {
    // ID marks id as primary key of table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank // validates the field cannot be blank.
    @Column(nullable = false) // enforces blank at databse level.
    private String name;

    @Column(length = 1000)
    private String description;

    @NotNull
    @DecimalMin("0.0") // price cannot be negative.
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Integer stockQuantity;

    @Column(nullable = false)
    private String category;

    @Column(unique = true, nullable = false) // no two products can have the same SKU.
    private String sku;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    /**
     * SKU: Stock Keeping Unit
     * unique code that identifies a specific product.
     */
}