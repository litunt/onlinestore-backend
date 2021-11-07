package com.online.store.transformer;

import com.online.store.dto.ProductDTO;
import com.online.store.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductTransformer {

    public List<ProductDTO> entitiesToDtos(List<Product> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public List<Product> dtosToEntities(List<ProductDTO> dtos) {
        return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

    public ProductDTO entityToDto(Product entity) {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(entity.getId());
        dto.setName(entity.getName());
        dto.setPetType(entity.getPetType());
        dto.setCategory(entity.getCategory());
        dto.setPrice(entity.getPrice());
        dto.setAmountAvailable(entity.getAmountAvailable());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public Product dtoToEntity(ProductDTO dto) {
        Product entity = new Product();
        entity.setId(dto.getProductId());
        entity.setName(dto.getName());
        entity.setPetType(dto.getPetType());
        entity.setCategory(dto.getCategory());
        entity.setPrice(dto.getPrice());
        entity.setAmountAvailable(dto.getAmountAvailable());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
