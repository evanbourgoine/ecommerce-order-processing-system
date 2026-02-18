package com.evanbourgoine.ecommerce.controller;

import com.evanbourgoine.ecommerce.model.Product;
import com.evanbourgoine.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // tells Spring this class is a controller, 
// and every method in it should return data directly as JSON
// rather than rendering an HTML page.

@RequestMapping("/api/products") // sets the base URL path for every endpoint
// in this class. So every method here starts with /api/products automatically.
// The "/api/" prefix is a convention that signals "this is an API endpoint, not a webpage"

@RequiredArgsConstructor // Lombok injection | Spring injects ProductService automatically.
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
    }

    /**
     * ResponseEntity is a wrapper that gives you full control
     * over the HTTP response you send back.
     * 
     * Lets you set:
     *  - Status code (200 OK, 201 Created, 404 Not Found, etc.)
     *  - Headers: metadata about the response
     *  - Body: the actual data being returned.
     */
}