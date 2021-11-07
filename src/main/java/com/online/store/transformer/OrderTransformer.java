package com.online.store.transformer;

import com.online.store.dto.OrderDTO;
import com.online.store.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderTransformer {

    @Autowired
    private OrderItemTransformer orderItemTransformer;

    public List<OrderDTO> entitiesToDtos(List<Order> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public List<Order> dtosToEntities(List<OrderDTO> dtos) {
        return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

    public OrderDTO entityToDto(Order entity) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(entity.getId());
        dto.setCreated(entity.getCreated());
        dto.setUserId(entity.getUserId());
        dto.setStatus(entity.getStatus());
        dto.setOrderItems(orderItemTransformer.entitiesToDtos(entity.getOrderItems()));
        return dto;
    }

    public Order dtoToEntity(OrderDTO dto) {
        Order entity = new Order();
        entity.setId(dto.getOrderId());
        entity.setCreated(dto.getCreated());
        entity.setUserId(dto.getUserId());
        entity.setOrderItems(orderItemTransformer.dtosToEntities(dto.getOrderItems()));
        entity.setStatus(dto.getStatus());
        return entity;
    }
}
