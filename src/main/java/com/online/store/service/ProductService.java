package com.online.store.service;

import com.online.store.dto.ProductDTO;
import com.online.store.model.Product;
import com.online.store.repository.ProductRepository;
import com.online.store.transformer.ProductTransformer;
import com.online.store.utils.PetType;
import com.online.store.utils.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTransformer productTransformer;

    @Transactional
    public List<ProductDTO> getProductByCategory(String category, String petType) {
        ProductCategory categoryEnum = ProductCategory.valueOf(category);
        List<Product> products;
        if (!petType.isEmpty()) {
            products = productRepository.findAllByPetTypeAndCategory(PetType.valueOf(petType), categoryEnum);
        } else {
            products = productRepository.findByCategory(categoryEnum);
        }
        return productTransformer.entitiesToDtos(products);
    }
}
