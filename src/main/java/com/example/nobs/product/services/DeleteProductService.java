package com.example.nobs.product.services;

import com.example.nobs.exception.ProductNotFoundException;
import com.example.nobs.product.model.Product;
import com.example.nobs.product.Command;
import com.example.nobs.product.ProductRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class DeleteProductService implements Command<Long,Void> {
    private final ProductRepo productRepo;

    public DeleteProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }


    @Override
    public ResponseEntity<Void> execute(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        if(productOptional.isPresent()){
            productRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new ProductNotFoundException();
    }
}
