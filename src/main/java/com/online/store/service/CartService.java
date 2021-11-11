package com.online.store.service;

import com.online.store.dto.CartDTO;
import com.online.store.dto.item.CartItemDTO;
import com.online.store.model.Cart;
import com.online.store.model.CartItem;
import com.online.store.repository.CartItemRepository;
import com.online.store.repository.CartRepository;
import com.online.store.transformer.CartItemTransformer;
import com.online.store.transformer.CartTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartTransformer cartTransformer;

    @Autowired
    private CartItemTransformer cartItemTransformer;

    public CartDTO getCartByUserId(Long userId) {
        Cart cart = cartRepository.findCartByUserId(userId);
        if (cart != null) {
            return cartTransformer.entityToDto(cart);
        }
        return null;
    }

    public CartDTO saveCart(CartDTO cartDTO) {
        List<CartItemDTO> itemDTOs = cartDTO.getCartItems();
        cartDTO.setCartItems(new ArrayList<>());

        Cart cart = cartTransformer.dtoToEntity(cartDTO);
        cart = cartRepository.save(cart);
        CartDTO dto = cartTransformer.entityToDto(cart);

        itemDTOs.forEach(item -> item.setContainerId(dto.getCartId()));
        List<CartItem> items = cartItemTransformer.dtosToEntities(itemDTOs);
        removeItemsWithZeroQuantity(items);
        if (items.isEmpty()) {
            clearCartData(cartDTO);
            return null;
        }
        items = cartItemRepository.saveAll(items);

        dto.setCartItems(cartItemTransformer.entitiesToDtos(items));
        return dto;
    }

    public void clearCartData(CartDTO cartDTO) {
        if (cartDTO.getCartId() != null) {
            cartRepository.deleteById(cartDTO.getCartId());
        }
    }

    private void removeItemsWithZeroQuantity(List<CartItem> items) {
        List<CartItem> itemsToRemove = new ArrayList<>();
        for (CartItem item : items) {
            if (item.getQuantity() <= 0) {
                itemsToRemove.add(item);
            }
        }
        cartItemRepository.deleteAll(itemsToRemove);
    }

}
