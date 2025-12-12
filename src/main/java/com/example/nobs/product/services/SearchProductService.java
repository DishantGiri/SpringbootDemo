package com.example.nobs.product.services;

import com.example.nobs.product.ProductRepo;
import com.example.nobs.product.Query;
import com.example.nobs.product.model.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SearchProductService implements Query<String, List<ProductDTO>> {
    private final ProductRepo productRepo;

    public SearchProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(String name) {
        return ResponseEntity.ok(productRepo.findByNameOrDescriptionContaining(name)
                .stream()
                .map(ProductDTO::new)
                .toList());
    }
}
