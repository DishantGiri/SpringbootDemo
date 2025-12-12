package com.example.nobs.product.model;

import lombok.Data;

@Data

public class ProductDTO {
    private Long Id;
    private String name;
    private String description;

    public ProductDTO(Product product) {

        this.Id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
    }

}