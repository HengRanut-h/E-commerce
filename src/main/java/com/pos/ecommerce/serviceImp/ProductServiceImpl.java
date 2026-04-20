package com.pos.ecommerce.serviceImp;


import com.pos.ecommerce.model.entity.ProductEntity;
import com.pos.ecommerce.model.request.ProductRequest;
import com.pos.ecommerce.model.response.ProductDto;
import com.pos.ecommerce.repository.ProductRepository;
import com.pos.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    // 🔹 Convert Entity → DTO
    private ProductDto mapToDto(ProductEntity e) {
        return ProductDto.builder()
                .id(e.getId())
                .name(e.getName())
                .description(e.getDescription())
                .price(e.getPrice())
                .stock(e.getStock())
                .active(e.getActive())
                .build();
    }

    @Override
    public Page<ProductDto> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(this::mapToDto);
    }

    @Override
    public ProductDto findById(Long id) {
        ProductEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return mapToDto(entity);
    }

    @Override
    public ProductDto create(ProductRequest request) {

        ProductEntity entity = ProductEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .active(true)
                .createdAt(LocalDateTime.now())
                .build();

        return mapToDto(repository.save(entity));
    }

    @Override
    public ProductDto update(Long id, ProductRequest request) {

        ProductEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setPrice(request.getPrice());
        entity.setStock(request.getStock());
        entity.setUpdatedAt(LocalDateTime.now());

        return mapToDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}