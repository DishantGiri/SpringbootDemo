package com.example.nobs.product;

import com.example.nobs.product.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CustomUserRepo extends JpaRepository<CustomUser, String> {
}
