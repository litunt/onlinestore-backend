package com.online.store;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.online.store.controller.OrderController;
import com.online.store.dto.OrderDTO;
import com.online.store.dto.StoreUserDTO;
import com.online.store.dto.UserCredentialsDTO;
import com.online.store.service.AuthenticationService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthenticationService authService;

    @Before
    public void performLogin() {
        UserCredentialsDTO userCredentials = new UserCredentialsDTO();
        userCredentials.setPassword("K1dPKyMfIQQj3ZZsyLTJ2w==");
        userCredentials.setUsername("litunt");

        StoreUserDTO userDTO = authService.login(userCredentials);
        Authentication auth = new UsernamePasswordAuthenticationToken(userCredentials.getUsername(), userCredentials.getPassword());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    public void getOrdersByUserIdTest() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                        .get("/orders")
                        .param("userId", "1"))
                .andExpect(status()
                        .isOk())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();
        List<OrderDTO> returnedOrders = objectMapper.readValue(contentAsString, new TypeReference<>() {
        });

        assertEquals(3, returnedOrders.size());
        returnedOrders.forEach(order -> assertEquals(1, order.getUserId()));
    }
}
