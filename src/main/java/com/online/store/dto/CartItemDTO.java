package com.online.store.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {

    @NotNull
    private long cartItemId;

    @NotNull
    private ProductDTO product;

    private int quantity;

}
