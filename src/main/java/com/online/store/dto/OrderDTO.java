package com.online.store.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private long orderId;

    private long userId;

    @NotNull
    private String status;

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime created;

    private List<OrderItemDTO> orderItems;
}
