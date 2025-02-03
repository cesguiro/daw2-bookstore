package es.cesguiro.pagination;

import es.cesguiro.exception.PagedCollectionException;

import java.util.List;

public record Page<T>(
        List<T> data,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages
) {

    /**
     * Creates a new instance of PagedCollection.
     *
     * @param data         the data to be paginated
     * @param pageNumber   the page number
     * @param pageSize     the page size
     * @param totalElements the total number of elements
     *
     * @throws PagedCollectionException if the data is null, the page number is negative, or the page size is less than or equal to zero
     */
    public Page(List<T> data, int pageNumber, int pageSize, long totalElements) {
        this(
                validateDataSize(data, pageSize),
                validatePageNumber(pageNumber),
                validatePageSize(pageSize),
                totalElements,
                (int) Math.ceil((double) totalElements / pageSize)
        );
    }

    private static <T> List<T> validateDataSize(List<T> data, int pageSize) {
        if (data == null) {
            throw new PagedCollectionException("Data cannot be null");
        }
        if (data.size() > pageSize) {
            throw new PagedCollectionException("Data size cannot be greater than page size");
        }
        return data;
    }

    private static int validatePageNumber(int pageNumber) {
        if (pageNumber < 0) {
            throw new PagedCollectionException("Page number cannot be negative");
        }
        return pageNumber;
    }

    private static int validatePageSize(int pageSize) {
        if (pageSize <= 0) {
            throw new PagedCollectionException("Page size must be greater than zero");
        }
        return pageSize;
    }
}
