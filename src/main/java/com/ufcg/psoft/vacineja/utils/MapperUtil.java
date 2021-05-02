package com.ufcg.psoft.vacineja.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public final class MapperUtil {
    protected ModelMapper modelMapper;

    public MapperUtil() {
        this.modelMapper = new ModelMapper();
    }

    public <S, D> D toDTO(S entity, Class<D> dtoClass) {
        return this.modelMapper.map(entity, dtoClass);
    }
    
    public <S, D> S toEntity(D dto, Class<S> entityClass) {
        return this.modelMapper.map(dto, entityClass);
    }
}
