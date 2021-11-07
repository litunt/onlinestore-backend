package com.online.store.transformer;

import com.online.store.dto.StoreUserDTO;
import com.online.store.model.StoreUser;
import org.springframework.stereotype.Component;

@Component
public class StoreUserTransformer {

    public StoreUserDTO entityToDto(StoreUser entity) {
        StoreUserDTO dto = new StoreUserDTO();
        dto.setUserId(entity.getId());
        dto.setName(entity.getName());
        dto.setActive(entity.getIsActive());
        dto.setRegDate(entity.getRegDate());
        return dto;
    }

    public StoreUser dtoToEntity(StoreUserDTO dto) {
        StoreUser entity = new StoreUser();
        entity.setId(dto.getUserId());
        entity.setName(dto.getName());
        entity.setRegDate(dto.getRegDate());
        entity.setIsActive(dto.isActive());
        return entity;
    }
}
