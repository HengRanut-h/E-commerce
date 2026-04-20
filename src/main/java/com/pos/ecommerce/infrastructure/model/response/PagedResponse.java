package com.pos.ecommerce.infrastructure.model.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagedResponse<T> {

    private List<T> content;

    private Pagination pagination;

    @Data @Builder
    @NoArgsConstructor @AllArgsConstructor
    public static class Pagination {
        private int  page;           // current page (0-based)
        private int  size;           // page size
        @JsonProperty("total_elements")
        private long totalElements;
        @JsonProperty("total_pages")
        private int  totalPages;
        @JsonProperty("has_next")
        private boolean hasNext;
        @JsonProperty("has_previous")
        private boolean hasPrevious;
        @JsonProperty("is_first")
        private boolean first;
        @JsonProperty("is_last")
        private boolean last;
    }

    // Convenience builder from Spring Data Page
    public static <T> PagedResponse<T> of(Page<T> page) {
        return PagedResponse.<T>builder()
                .content(page.getContent())
                .pagination(Pagination.builder()
                        .page(page.getNumber())
                        .size(page.getSize())
                        .totalElements(page.getTotalElements())
                        .totalPages(page.getTotalPages())
                        .hasNext(page.hasNext())
                        .hasPrevious(page.hasPrevious())
                        .first(page.isFirst())
                        .last(page.isLast())
                        .build())
                .build();
    }

    // Convenience builder wrapping inside ApiResponse
    public static <T> ApiResponse<PagedResponse<T>> toApiResponse(Page<T> page) {
        return ApiResponse.success(PagedResponse.of(page));
    }
}