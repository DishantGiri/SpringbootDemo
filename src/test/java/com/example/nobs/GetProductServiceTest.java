package com.example.nobs;

import com.example.nobs.product.ProductRepo;
import com.example.nobs.product.model.Product;
import com.example.nobs.product.model.ProductDTO;
import com.example.nobs.product.services.GetProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetProductServiceTest {
    @Mock
    private ProductRepo productRepo;
    @InjectMocks
    private GetProductService getProductService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void given_product_exists_when_get_Product_service_then_returnProductDTO() {
        //TODO implement test
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("This is a test product");
        product.setPrice(100.0);

        when(productRepo.findById(1L)).thenReturn(Optional.of(product));

        ResponseEntity<ProductDTO> response = getProductService.execute(1L);
        assertEquals(ResponseEntity.ok(new ProductDTO(product)), response);
    }
}
