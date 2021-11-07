package com.online.store.dto;

import com.online.store.utils.PetType;
import com.online.store.utils.ProductCategory;
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

    @NotNull
    private PetType petType;

    @NotNull
    private ProductCategory category;

    @NotNull
    private String description;

    private double price;

    private int amountAvailable;
}
