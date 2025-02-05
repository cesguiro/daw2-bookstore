package es.cesguiro.model;

import java.util.List;

public record PageResponse<T>(
        List<T> data,
        int page,
        int pageSize,
        long totalItems,
        int totalPages,
        String previous,
        String next
) {
}
