package com.pos.ecommerce.model.mapper;

import com.pos.ecommerce.model.entity.ProductEntity;
import com.pos.ecommerce.model.request.ProductRequest;
import com.pos.ecommerce.model.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    ProductEntity toEntity(ProductRequest request);

    void updateEntity(ProductRequest request, @MappingTarget ProductEntity entity);

    ProductResponse toResponse(ProductEntity entity);
}
