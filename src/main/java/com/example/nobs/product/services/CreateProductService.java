package com.example.nobs.product.services;

import com.example.nobs.exception.ErrorMessages;
import com.example.nobs.exception.ProductNotValidException;
import com.example.nobs.product.model.Product;
import com.example.nobs.product.model.ProductDTO;
import com.example.nobs.product.Command;
import com.example.nobs.product.ProductRepo;
import com.example.nobs.product.validators.ProductValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service

public class CreateProductService implements Command<Product, ProductDTO> {
    private final ProductRepo productRepo;

    public CreateProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }


    @Override
    public ResponseEntity<ProductDTO> execute(Product product) {
        ProductValidator.execute(product);

        Product savedProduct = productRepo.save(product);
        return ResponseEntity.status(HttpStatus.OK).body(new ProductDTO(savedProduct));
    }


}
