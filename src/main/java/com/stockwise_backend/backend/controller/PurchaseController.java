package com.stockwise_backend.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stockwise_backend.backend.model.Purchase;
import com.stockwise_backend.backend.repository.PurchaseRepository;

@RestController
@RequestMapping("/api/purchases")
@CrossOrigin(origins = "*")
public class PurchaseController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    // GET all purchases ordered by date
    @GetMapping
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAllByOrderByDateDesc();
    }
}
