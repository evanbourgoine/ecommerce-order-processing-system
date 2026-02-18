package com.evanbourgoine.ecommerce.service;

import com.evanbourgoine.ecommerce.model.Product;
import com.evanbourgoine.ecommerce.repository.ProductRepository;
import com.evanbourgoine.ecommerce.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // tells Spring this is a service later class
@RequiredArgsConstructor // Lombok generating a constructor for all final fields.
// how spring injects ProductRepository automatically: dependecy injection.
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}