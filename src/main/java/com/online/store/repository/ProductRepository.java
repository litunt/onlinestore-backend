package com.online.store.repository;

import com.online.store.model.Product;
import com.online.store.utils.PetType;
import com.online.store.utils.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByPetTypeAndCategory(PetType petType, ProductCategory category, Pageable pageable);

    Page<Product> findByCategory(ProductCategory category, Pageable pageable);

    Long countAllByCategory(ProductCategory category);
}
