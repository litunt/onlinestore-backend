package com.online.store.transformer;

import com.online.store.dto.item.OrderItemDTO;
import com.online.store.model.OrderItem;
import com.online.store.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderItemTransformer {

    @Autowired
    private ProductTransformer productTransformer;

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderItemDTO> entitiesToDtos(List<OrderItem> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public List<OrderItem> dtosToEntities(List<OrderItemDTO> dtos) {
        return dtos.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

    public OrderItemDTO entityToDto(OrderItem entity) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setItemId(entity.getId());
        dto.setQuantity(entity.getQuantity());
        dto.setContainerId(entity.getOrder().getId());
        dto.setProduct(productTransformer.entityToDto(entity.getProduct()));
        return dto;
    }

    public OrderItem dtoToEntity(OrderItemDTO dto) {
        OrderItem entity = new OrderItem();
        entity.setId(dto.getItemId());
        entity.setOrder(orderRepository.getById(dto.getContainerId()));
        entity.setProduct(productTransformer.dtoToEntity(dto.getProduct()));
        entity.setQuantity(dto.getQuantity());
        return entity;
    }
}
