package com.stockwise_backend.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockwise_backend.backend.model.Product;
import com.stockwise_backend.backend.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // GET all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // POST add new product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        int qty = product.getQuantity() != null ? product.getQuantity() : 0;
        if (qty == 0) product.setStatus("Out of Stock");
        else if (qty <= 10) product.setStatus("Low Stock");
        else product.setStatus("In Stock");
        return productRepository.save(product);
    }

    // DELETE product
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return Map.of("success", true);
    }
}
