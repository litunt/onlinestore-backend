package com.online.store.repository;

import com.online.store.model.Product;
import com.online.store.utils.PetType;
import com.online.store.utils.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPetTypeAndCategory(@Param("petType") PetType petType,
                                              @Param("category") ProductCategory category);

    List<Product> findByCategory(@Param("category") ProductCategory category);
}
