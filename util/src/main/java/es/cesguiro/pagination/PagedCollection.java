package es.cesguiro.pagination;

import java.util.List;

public record PagedCollection<T>(
        List<T> data,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {
    public PagedCollection(List<T> data, int pageNumber, int pageSize, long totalElements) {
        this(
                data,
                pageNumber,
                pageSize,
                totalElements,
                (int) Math.ceil((double) totalElements / pageSize)
        );
    }

}
