package com.online.store;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.online.store.controller.CartController;
import com.online.store.dto.CartDTO;
import com.online.store.dto.ProductDTO;
import com.online.store.dto.item.CartItemDTO;
import com.online.store.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;


    @Test
    public void saveCartTest() throws Exception {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCreated(LocalDateTime.now());
        cartDTO.setUserId(1L);
        cartDTO.setCartItems(generateCartItems());

        ObjectWriter objWriter = objectMapper.writer();

        MvcResult result = mvc.perform(post("/cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objWriter.writeValueAsString(cartDTO)))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();
        CartDTO returnedCart = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });

        assertNotNull(returnedCart.getCartId());
        returnedCart.getCartItems().forEach(item -> {
            assertNotNull(item.getContainerId());
            assertEquals(returnedCart.getCartId(), item.getContainerId());
            assertNotNull(item.getItemId());
        });
    }

    @Test
    public void getCartByUserIdTest() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                        .get("/cart")
                        .param("userId", "1"))
                .andExpect(status()
                        .isOk())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();
        CartDTO returnedCart = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });

        assertNotNull(returnedCart);
        assertEquals(1L, returnedCart.getUserId());
    }

    private List<CartItemDTO> generateCartItems() {
        List<ProductDTO> products = productService.getProductByCategory("TOYS", "CAT", 0, 2, "price", "asc");
        List<CartItemDTO> items = new ArrayList<>();
        for (ProductDTO product : products) {
            CartItemDTO item = new CartItemDTO();
            item.setProduct(product);
            item.setQuantity(1);
            items.add(item);
        }
        return items;
    }
}
