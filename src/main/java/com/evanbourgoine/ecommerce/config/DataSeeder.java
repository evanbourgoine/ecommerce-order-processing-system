package com.evanbourgoine.ecommerce.config;

import com.evanbourgoine.ecommerce.model.Product;
import com.evanbourgoine.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        if (productRepository.count() == 0) {
            List<Product> products = List.of(
                Product.builder()
                    .name("Running Shoes")
                    .description("Lightweight running shoes for everyday training")
                    .price(new BigDecimal("99.99"))
                    .stockQuantity(50)
                    .category("Footwear")
                    .sku("SHOE-RUN-001")
                    .build(),
                Product.builder()
                    .name("Wireless Headphones")
                    .description("Noise cancelling over-ear headphones with 30hr battery")
                    .price(new BigDecimal("249.99"))
                    .stockQuantity(30)
                    .category("Electronics")
                    .sku("ELEC-HEAD-001")
                    .build(),
                Product.builder()
                    .name("Yoga Mat")
                    .description("Non-slip yoga mat, 6mm thick")
                    .price(new BigDecimal("34.99"))
                    .stockQuantity(100)
                    .category("Fitness")
                    .sku("FIT-MAT-001")
                    .build(),
                Product.builder()
                    .name("Stainless Steel Water Bottle")
                    .description("32oz insulated water bottle, keeps cold 24hrs")
                    .price(new BigDecimal("29.99"))
                    .stockQuantity(75)
                    .category("Accessories")
                    .sku("ACC-BTL-001")
                    .build(),
                Product.builder()
                    .name("Mechanical Keyboard")
                    .description("Tenkeyless mechanical keyboard with Cherry MX switches")
                    .price(new BigDecimal("129.99"))
                    .stockQuantity(20)
                    .category("Electronics")
                    .sku("ELEC-KEY-001")
                    .build()
            );

            productRepository.saveAll(products);
            System.out.println("Seeded " + products.size() + " products into the database");
        } else {
            System.out.println("Database already seeded, skipping...");
        }
    }
}