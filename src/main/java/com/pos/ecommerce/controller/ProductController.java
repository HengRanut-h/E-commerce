package com.pos.ecommerce.controller;

import com.pos.ecommerce.infrastructure.model.response.ApiResponseWrapper;
import com.pos.ecommerce.infrastructure.model.response.PagedResponse;
import com.pos.ecommerce.infrastructure.util.ResponseUtils;
import com.pos.ecommerce.model.request.ProductRequest;
import com.pos.ecommerce.model.response.ProductResponse;
import com.pos.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @GetMapping("/test")
    public String test() {
        return "hello";
    }

    // ================= GET BY ID =================
    @Operation(summary = "Get product by id")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(schema = @Schema(implementation = ApiResponseWrapper.class)))
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseWrapper<ProductResponse>> findById(@PathVariable Long id) {
        return ResponseUtils.ok(productService.findById(id));
    }

    // ================= CREATE =================
    @Operation(summary = "Create product")
    @ApiResponse(responseCode = "201", description = "Created",
            content = @Content(schema = @Schema(implementation = ApiResponseWrapper.class)))
    @PostMapping
    public ResponseEntity<ApiResponseWrapper<ProductResponse>> create(
            @Valid @RequestBody ProductRequest request) {

        return ResponseUtils.created(productService.create(request));
    }

    // ================= UPDATE =================
    @Operation(summary = "Update product")
    @ApiResponse(responseCode = "200", description = "Updated",
            content = @Content(schema = @Schema(implementation = ApiResponseWrapper.class)))
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseWrapper<ProductResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {

        return ResponseUtils.updated(productService.update(id, request));
    }

    // ================= DELETE =================
    @Operation(summary = "Delete product")
    @ApiResponse(responseCode = "200", description = "Deleted")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseWrapper<Void>> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseUtils.deleted();
    }

    // ================= LIST =================
    @Operation(summary = "Get all products (paged)")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(schema = @Schema(implementation = PagedResponse.class)))
    @GetMapping
    public ResponseEntity<ApiResponseWrapper<PagedResponse<ProductResponse>>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Page<ProductResponse> result = productService.findAll(PageRequest.of(page, size));
        return ResponseUtils.paged(result);
    }
}