package com.example.nobs.product.validators;

import com.example.nobs.exception.ErrorMessages;
import com.example.nobs.exception.ProductNotValidException;
import com.example.nobs.product.model.Product;
import org.springframework.util.StringUtils;

public class ProductValidator {
    private ProductValidator(){

    }
    public static void execute(Product product){
        if (StringUtils.isEmpty(product.getName()))
            throw new ProductNotValidException(ErrorMessages.NAME_REQUIRED.getMessage());
        if (product.getDescription().length() < 20)
            throw new ProductNotValidException(ErrorMessages.DESCRIPTION_REQUIRED.getMessage());
        if (product.getPrice() <= 0)
            throw new ProductNotValidException(ErrorMessages.PRICE_CANNOT_BE_NEGATIVE.getMessage());
    }

}
