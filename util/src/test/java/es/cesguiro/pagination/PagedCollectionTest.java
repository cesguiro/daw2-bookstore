package es.cesguiro.pagination;

import es.cesguiro.exception.PagedCollectionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PagedCollectionTest {

    @Test
    @DisplayName("Test total pages with 0 elements should return 0")
    void testTotalPagesWithZeroElements() {
        PagedCollection<String> pagedCollection = new PagedCollection<>(Collections.emptyList(), 0, 10, 0);
        assertEquals(0, pagedCollection.totalPages(), "Total pages should be 0");
    }

    @Test
    @DisplayName("Test total pages with 1 element should return 1")
    void testTotalPagesWithOneElement() {
        PagedCollection<String> pagedCollection = new PagedCollection<>(List.of("element"), 0, 10, 1);
        assertEquals(1, pagedCollection.totalPages(), "Total pages should be 1");
    }

    @Test
    @DisplayName("Test total pages with 10 elements and page size 10 should return 1")
    void testTotalPagesWithTenElementsAndPageSizeTen() {
        PagedCollection<String> pagedCollection = new PagedCollection<>(List.of("element1", "element2", "element3", "element4", "element5", "element6", "element7", "element8", "element9", "element10"), 0, 10, 10);
        assertEquals(1, pagedCollection.totalPages(), "Total pages should be 1");
    }

    @Test
    @DisplayName("Test negative page should throw PagedCollectionException")
    void testNegativePage() {
        assertThrows(PagedCollectionException.class, () -> new PagedCollection<>(List.of("element"), -1, 10, 100));
    }

    @Test
    @DisplayName("Test negative page size should throw PagedCollectionException")
    void testNegativePageSize() {
        assertThrows(PagedCollectionException.class, () -> new PagedCollection<>(List.of("element"), 0, -1, 100));
    }

    @Test
    @DisplayName("Test empty data with non-zero total elements")
    void testEmptyDataWithNonZeroTotalElements() {
        PagedCollection<String> pagedCollection = new PagedCollection<>(Collections.emptyList(), 0, 10, 20);
        assertEquals(2, pagedCollection.totalPages(), "Total pages should be calculated based on totalElements");
    }

    @Test
    @DisplayName("Test total elements 0 with page size 1 should return 0 total pages")
    void testZeroTotalElementsWithPageSizeOne() {
        PagedCollection<String> pagedCollection = new PagedCollection<>(Collections.emptyList(), 0, 1, 0);
        assertEquals(0, pagedCollection.totalPages(), "Total pages should be 0");
    }

    @Test
    @DisplayName("Test total elements 1 with page size 1 should return 1 total page")
    void testOneTotalElementWithPageSizeOne() {
        PagedCollection<String> pagedCollection = new PagedCollection<>(List.of("element"), 0, 1, 1);
        assertEquals(1, pagedCollection.totalPages(), "Total pages should be 1");
    }

    @Test
    @DisplayName("Test large total elements with page size")
    void testLargeTotalElements() {
        PagedCollection<String> pagedCollection = new PagedCollection<>(Collections.emptyList(), 0, 10, 1_000_000);
        assertEquals(100_000, pagedCollection.totalPages(), "Total pages should be correctly calculated for large inputs");
    }

    @Test
    @DisplayName("Test data size exceeds page size should throw PagedCollectionException")
    void testDataSizeExceedsPageSize() {
        assertThrows(PagedCollectionException.class,
                () -> new PagedCollection<>(List.of("element1", "element2", "element3"), 0, 2, 3));
    }

    @Test
    @DisplayName("Test data size equals page size should not throw exception")
    void testDataSizeEqualsPageSize() {
        PagedCollection<String> pagedCollection = new PagedCollection<>(List.of("element1", "element2"), 0, 2, 2);
        assertEquals(1, pagedCollection.totalPages(), "Total pages should be 1");
    }

    @Test
    @DisplayName("Test data size less than page size should not throw exception")
    void testDataSizeLessThanPageSize() {
        PagedCollection<String> pagedCollection = new PagedCollection<>(List.of("element1"), 0, 2, 1);
        assertEquals(1, pagedCollection.totalPages(), "Total pages should be 1");
    }

    @Test
    @DisplayName("Test null data should throw PagedCollectionException")
    void testNullData() {
        assertThrows(PagedCollectionException.class, () -> new PagedCollection<>(null, 0, 10, 10));
    }

}