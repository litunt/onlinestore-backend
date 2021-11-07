package com.online.store.transformer;

import com.online.store.dto.CartDTO;
import com.online.store.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartTransformer {

    @Autowired
    private CartItemTransformer cartItemTransformer;

    public List<CartDTO> entitiesToDtos(List<Cart> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public List<Cart> dtosToEntities(List<CartDTO> dtos) {
        return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

    public CartDTO entityToDto(Cart entity) {
        CartDTO dto = new CartDTO();
        dto.setCartId(entity.getId());
        dto.setCartItems(cartItemTransformer.entitiesToDtos(entity.getCartItems()));
        dto.setUserId(entity.getUserId());
        dto.setCreated(entity.getCreated());
        return dto;
    }

    public Cart dtoToEntity(CartDTO dto) {
        Cart entity = new Cart();
        entity.setId(dto.getCartId());
        entity.setCreated(dto.getCreated());
        entity.setUserId(dto.getUserId());
        entity.setCartItems(cartItemTransformer.dtosToEntities(dto.getCartItems()));
        return entity;
    }
}
