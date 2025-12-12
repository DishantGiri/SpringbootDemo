package com.example.nobs.product.services;

import com.example.nobs.exception.ProductNotFoundException;
import com.example.nobs.product.model.Product;
import com.example.nobs.product.model.ProductDTO;
import com.example.nobs.product.model.UpdateProductCommand;
import com.example.nobs.product.Command;
import com.example.nobs.product.ProductRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UpdateProductService implements Command<UpdateProductCommand, ProductDTO> {
    private final ProductRepo productRepo;

    public UpdateProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }


    @Override
    public ResponseEntity<ProductDTO> execute(UpdateProductCommand updateProductCommand) {
        Optional<Product> productOptional = productRepo.findById(updateProductCommand.getId());
        if(productOptional.isPresent()){
            Product product = updateProductCommand.getProduct();
            product.setId(updateProductCommand.getId());
            productRepo.save(product);
            return ResponseEntity.ok(new ProductDTO(product));
        }
        throw new ProductNotFoundException();
    }
}
