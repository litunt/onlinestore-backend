package com.online.store.service;

import com.online.store.dto.ProductDTO;
import com.online.store.model.Product;
import com.online.store.repository.ProductRepository;
import com.online.store.transformer.ProductTransformer;
import com.online.store.utils.PetType;
import com.online.store.utils.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<ProductDTO> getProductByCategory(String category, String petType, Integer page, Integer size) {
        Pageable pageWithElements = PageRequest.of(page, size);
        ProductCategory categoryEnum = ProductCategory.valueOf(category);
        Page<Product> products;
        if (!petType.isEmpty()) {
            products = productRepository.findAllByPetTypeAndCategory(PetType.valueOf(petType), categoryEnum, pageWithElements);
        } else {
            products = productRepository.findByCategory(categoryEnum, pageWithElements);
        }
        return productTransformer.entitiesToDtos(products.getContent());
    }

    public void decreaseAvailableAmount(Long productId, int amount) {
        Product product = productRepository.getById(productId);
        product.setAmountAvailable(product.getAmountAvailable() - amount);
        productRepository.save(product);
    }

    public long countProductsByCategory(String category) {
        ProductCategory categoryEnum = ProductCategory.valueOf(category);
        return productRepository.countAllByCategory(categoryEnum);
    }
}
