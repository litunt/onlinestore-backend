package com.online.store.dto;

import com.online.store.dto.item.CartItemDTO;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    @NotNull
    private long cartId;

    @NotNull
    private long userId;

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime created;

    private List<CartItemDTO> cartItems;

}
