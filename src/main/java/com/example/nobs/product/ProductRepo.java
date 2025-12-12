package com.example.nobs.product;

import com.example.nobs.product.model.Product;
import com.example.nobs.product.model.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductRepo extends JpaRepository<Product,Long> {
    List<Product> findByNameContainingIgnoreCase(String name);
    @Query("Select p from Product p where p.name like %:keyword% or p.description like %:keyword%")
    List<Product> findByNameOrDescriptionContaining(@Param("keyword") String keyword);
}
