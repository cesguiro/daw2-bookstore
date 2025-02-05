package es.cesguiro.controller.data;

import es.cesguiro.model.book.query.CategoryResponse;

import java.util.List;
import java.util.Map;

public class CategoryData {

    private static final String BASE_LINK = "http://localhost:8080/api/categories";

    private static final List<CategoryResponse> categoryResponsesEs = List.of(
            new CategoryResponse("categoryEs1", Map.of("books", BASE_LINK + "/category1/books")),
            new CategoryResponse("categoryEs2", Map.of("books", BASE_LINK + "/category2/books")),
            new CategoryResponse("categoryEs3", Map.of("books", BASE_LINK + "/category3/books")),
            new CategoryResponse("categoryEs4", Map.of("books", BASE_LINK + "/category4/books"))
    );
    private static final List<CategoryResponse> categoryResponsesEn = List.of(
            new CategoryResponse("categoryEn1", Map.of("books", BASE_LINK + "/category1/books")),
            new CategoryResponse("categoryEn2", Map.of("books", BASE_LINK + "/category2/books")),
            new CategoryResponse("categoryEn3", Map.of("books", BASE_LINK + "/category3/books")),
            new CategoryResponse("categoryEn4", Map.of("books", BASE_LINK + "/category4/books"))
    );

    public static List<CategoryResponse> getCategoryResponsesEs() {
        return categoryResponsesEs;
    }

    public static CategoryResponse getCategoryResponseEs(int index) {
        return categoryResponsesEs.get(index);
    }

    public static List<CategoryResponse> getCategoryResponsesEn() {
        return categoryResponsesEn;
    }

    public static CategoryResponse getCategoryResponseEn(int index) {
        return categoryResponsesEn.get(index);
    }

}
