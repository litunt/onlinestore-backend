package com.online.store.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private long productId;

    @NotNull
    private String name;

    private double price;

    private int amountAvailable;
}
