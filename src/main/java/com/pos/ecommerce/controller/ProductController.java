package com.pos.ecommerce.controller;

import com.pos.ecommerce.infrastructure.model.response.ApiResponse;
import com.pos.ecommerce.infrastructure.model.response.PagedResponse;
import com.pos.ecommerce.infrastructure.util.ResponseUtils;
import com.pos.ecommerce.model.request.ProductRequest;
import com.pos.ecommerce.model.response.ProductDto;
import com.pos.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<PagedResponse<ProductDto>>> list(
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "20") int size) {
        Page<ProductDto> result = productService.findAll(PageRequest.of(page, size));
        return ResponseUtils.paged(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> findById(@PathVariable Long id) {
        return ResponseUtils.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductDto>> create(
            @Valid @RequestBody ProductRequest request) {
        return ResponseUtils.created(productService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {
        return ResponseUtils.updated(productService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseUtils.deleted();
    }
}