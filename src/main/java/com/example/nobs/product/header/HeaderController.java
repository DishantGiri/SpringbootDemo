package com.example.nobs.product.header;

import com.example.nobs.product.model.Product;
import com.example.nobs.product.model.ProductDTO;
import com.example.nobs.product.services.GetProductsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class HeaderController {
    private final GetProductsService getProductsService;

    public HeaderController(GetProductsService getProductsService) {
        this.getProductsService = getProductsService;
    }

    @GetMapping("/header")
    public String getRegionalResponse(@RequestHeader(required = false, defaultValue = "Global Citizen") String region) {
        if (region.endsWith("us")) return "Bald Eagle Freedom";
        else if(region.equalsIgnoreCase("CAN")) return "Maple Leaf Strong";
        else if(region.equalsIgnoreCase("MEX")) return "Aztec Heritage";
        else return "Global Citizen";
    }
    @GetMapping(value = "/header/product", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity <List<ProductDTO>> getProduct(){
        return getProductsService.execute(null);
    }
}
