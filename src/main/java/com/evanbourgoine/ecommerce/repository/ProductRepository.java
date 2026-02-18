package com.evanbourgoine.ecommerce.repository;

import com.evanbourgoine.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Extends JpaRepository to get all the basic CRUD operations for free.
// JpaRepository<Product, Long> means this repository manages Product entities and the primary key is of type Long.
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}