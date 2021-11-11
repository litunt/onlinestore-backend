package com.online.store.dto.item;

import com.online.store.dto.ProductDTO;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ItemDTO {

    Long itemId;

    Long containerId;

    ProductDTO product;

    int quantity;
}
