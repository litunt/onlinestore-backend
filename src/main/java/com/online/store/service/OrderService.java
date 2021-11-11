package com.online.store.service;

import com.online.store.dto.CartDTO;
import com.online.store.dto.OrderDTO;
import com.online.store.dto.item.CartItemDTO;
import com.online.store.model.Order;
import com.online.store.model.OrderItem;
import com.online.store.repository.OrderItemRepository;
import com.online.store.repository.OrderRepository;
import com.online.store.transformer.OrderTransformer;
import com.online.store.transformer.ProductTransformer;
import com.online.store.utils.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderTransformer orderTransformer;

    @Autowired
    private ProductTransformer productTransformer;


    public List<OrderDTO> getOrdersByUserId(Long userId) {
        return orderTransformer.entitiesToDtos(orderRepository.findAllByUserIdOrderByCreatedDesc(userId));
    }

    public OrderDTO updateOrderStatus(Long orderId, OrderDTO orderDTO) {
        Order order = orderRepository.getById(orderId);
        order.setStatus(orderDTO.getStatus());
        order = orderRepository.save(order);
        return orderTransformer.entityToDto(order);
    }

    public OrderDTO createOrder(CartDTO cart) {
        Order order = new Order();
        order.setStatus(OrderStatus.CREATED);
        order.setUserId(cart.getUserId());
        order.setCreated(LocalDateTime.now());
        order = orderRepository.save(order);
        List<OrderItem> orderItems = generateOrderItemsFromCart(cart.getCartItems(), order);
        cartService.clearCartData(cart);
        orderItems = orderItemRepository.saveAll(orderItems);
        order.setOrderItems(orderItems);
        return orderTransformer.entityToDto(order);
    }

    private List<OrderItem> generateOrderItemsFromCart(List<CartItemDTO> cartItems, Order order) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItemDTO cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setProduct(productTransformer.dtoToEntity(cartItem.getProduct()));
            orderItem.setOrder(order);
            orderItems.add(orderItem);

            productService.decreaseAvailableAmount(cartItem.getProduct().getProductId(), cartItem.getQuantity());
        }
        return orderItems;
    }
}
