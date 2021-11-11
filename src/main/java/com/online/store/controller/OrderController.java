package com.online.store.controller;

import com.online.store.dto.CartDTO;
import com.online.store.dto.OrderDTO;
import com.online.store.exception.ApiException;
import com.online.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderDTO> getOrdersByUserId(@RequestParam Long userId) {
        try {
            return orderService.getOrdersByUserId(userId);
        } catch (Exception ex) {
            throw new ApiException("Could not get orders for user with ID = " + userId.toString());
        }
    }

    @PostMapping
    public OrderDTO createOrder(@RequestBody CartDTO cartDTO) {
        try {
            return orderService.createOrder(cartDTO);
        } catch (Exception ex) {
            throw new ApiException("Could not create order!");
        }
    }

    @PutMapping("/{orderId}")
    public OrderDTO updateOrderStatus(@PathVariable Long orderId, @RequestBody OrderDTO orderDTO) {
        try {
            return orderService.updateOrderStatus(orderId, orderDTO);
        } catch (Exception ex) {
            throw new ApiException("Could not update status for order with ID = " + orderId.toString());
        }
    }
}
