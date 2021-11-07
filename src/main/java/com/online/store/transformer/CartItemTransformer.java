package com.online.store.transformer;

import com.online.store.dto.item.CartItemDTO;
import com.online.store.model.CartItem;
import com.online.store.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartItemTransformer {

    @Autowired
    private ProductTransformer productTransformer;

    @Autowired
    private CartRepository cartRepository;

    public List<CartItemDTO> entitiesToDtos(List<CartItem> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public List<CartItem> dtosToEntities(List<CartItemDTO> dtos) {
        return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

    public CartItemDTO entityToDto(CartItem entity) {
        CartItemDTO dto = new CartItemDTO();
        dto.setItemId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setProduct(productTransformer.entityToDto(entity.getProduct()));
        dto.setContainerId(entity.getCart().getId());
        return dto;
    }

    public CartItem dtoToEntity(CartItemDTO dto) {
        CartItem entity = new CartItem();
        entity.setId(dto.getItemId());
        entity.setQuantity(dto.getQuantity());
        entity.setProduct(productTransformer.dtoToEntity(dto.getProduct()));
        entity.setCart(cartRepository.getById(dto.getContainerId()));
        return entity;
    }
}
