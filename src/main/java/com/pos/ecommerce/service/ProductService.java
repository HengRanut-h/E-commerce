package com.pos.ecommerce.service;

import com.pos.ecommerce.model.response.ProductDto;
import com.pos.ecommerce.model.request.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductDto> findAll(Pageable pageable);

    ProductDto findById(Long id);

    ProductDto create(ProductRequest request);

    ProductDto update(Long id, ProductRequest request);

    void delete(Long id);
}