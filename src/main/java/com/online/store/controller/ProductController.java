package com.online.store.controller;

import com.online.store.dto.ProductDTO;
import com.online.store.exception.ApiException;
import com.online.store.service.ProductService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public List<ProductDTO> getProductsByCategory(@RequestParam @NotNull String category,
                                                                  @RequestParam String petType,
                                                                  @RequestParam(defaultValue = "0") Integer page,
                                                                  @RequestParam(defaultValue = "4") Integer size) throws ApiException {
        try {
            return productService.getProductByCategory(category, petType, page, size);
        } catch (Exception ex) {
            throw new ApiException("Could not retrieve products!");
        }
    }

    @GetMapping("/total")
    public Long getProductsTotalNumberByCategory(@RequestParam @NotNull String category) {
        try {
            return productService.countProductsByCategory(category);
        } catch (Exception ex) {
            throw new ApiException("Could not get total number of products!");
        }
    }
}
