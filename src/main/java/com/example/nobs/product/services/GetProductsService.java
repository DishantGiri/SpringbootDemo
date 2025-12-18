package com.example.nobs.product.services;

import com.example.nobs.product.model.Product;
import com.example.nobs.product.model.ProductDTO;
import com.example.nobs.product.ProductRepo;
import com.example.nobs.product.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class GetProductsService implements Query<Void,List<ProductDTO>> {
    private final ProductRepo productRepo;
    private static final Logger logger = LoggerFactory.getLogger(GetProductsService.class);

    public GetProductsService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(Void Input) {
        logger.info("executing"+ this.getClass()+" Input: "+Input);
        List<Product> products = productRepo.findAll();
        List<ProductDTO> productDtos = products.stream().map(ProductDTO::new).toList();
        System.out.println(productDtos);
        return ResponseEntity.status(HttpStatus.OK).body(productDtos);

    }
}
