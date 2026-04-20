package com.pos.ecommerce.serviceImp;


import com.pos.ecommerce.exception.ResourceAlreadyExistsException;
import com.pos.ecommerce.exception.ResourceNotFoundException;
import com.pos.ecommerce.model.entity.ProductEntity;
import com.pos.ecommerce.model.mapper.ProductMapper;
import com.pos.ecommerce.model.request.ProductRequest;
import com.pos.ecommerce.model.response.ProductResponse;
import com.pos.ecommerce.repository.ProductRepository;
import com.pos.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    // 🔹 Convert Entity → DTO
    private ProductResponse mapToDto(ProductEntity e) {
        return new ProductResponse(
              e.getId(),
                e.getName(),
                e.getDescription(),
                e.getPrice(),
                e.getStock(),
                e.getActive(),
                e.getCreatedAt(),
                e.getUpdatedAt()

        );
    }

    @Override

    public Page<ProductResponse> findAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(mapper::toResponse);
    }

    @Override
    public ProductResponse findById(Long id) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return mapToDto(entity);
    }

    @Override
    public ProductResponse create(ProductRequest request) {
        if (productRepository.existsByName(request.name())) {
            throw new ResourceAlreadyExistsException("Product","Name",request.name());
        }
        ProductEntity entity = mapper.toEntity(request);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        return mapper.toResponse(productRepository.save(entity));
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        if (productRepository.existsByName(request.name())) {
            throw new ResourceAlreadyExistsException("Product","Name",request.name());
        }
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product", "id", id
                ));

        mapper.updateEntity(request, entity);

        entity.setUpdatedAt(LocalDateTime.now());

        return mapper.toResponse(productRepository.save(entity));
    }

@Override
public void delete(Long id) {
    productRepository.deleteById(id);
}
}