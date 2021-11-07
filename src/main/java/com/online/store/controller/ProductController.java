package com.online.store.controller;

import com.online.store.dto.ProductDTO;
import com.online.store.service.ProductService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public List<ProductDTO> getProductsByCategory(@RequestParam @NotNull String category, @RequestParam String petType) {
        return productService.getProductByCategory(category, petType);
    }

}
