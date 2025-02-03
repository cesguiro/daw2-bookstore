package es.cesguiro.data;

import es.cesguiro.model.book.query.AuthorCollectionResponse;
import es.cesguiro.model.book.query.AuthorReponse;
import es.cesguiro.usecase.book.query.model.AuthorCollectionQuery;
import es.cesguiro.usecase.book.query.model.AuthorQuery;

import java.util.List;
import java.util.Map;

public class AuthorData {

    private static final String BASE_LINK = "http://localhost:8080/api/authors";

    private static List<AuthorCollectionQuery> authorCollectionQueries = List.of(
            new AuthorCollectionQuery("author1", "author1"),
            new AuthorCollectionQuery("author2", "author2"),
            new AuthorCollectionQuery("author3", "author3"),
            new AuthorCollectionQuery("author4", "author4")
    );

    private static List<AuthorQuery> authorQueries = List.of(
            new AuthorQuery("author1", "author1"),
            new AuthorQuery("author2", "author2"),
            new AuthorQuery("author3", "author3"),
            new AuthorQuery("author4", "author4")
    );

    private static List<AuthorCollectionResponse> authorCollectionResponses = List.of(
            new AuthorCollectionResponse(
                    "author1",
                    Map.of("_self", BASE_LINK + "/author1",
                            "books", BASE_LINK + "/author1/books"
                    )
            ),
            new AuthorCollectionResponse(
                    "author2",
                    Map.of("_self", BASE_LINK + "/author2",
                            "books", BASE_LINK + "/author2/books"
                    )
            ),
            new AuthorCollectionResponse(
                    "author3",
                    Map.of("_self", BASE_LINK + "/author3",
                            "books", BASE_LINK + "/author3/books"
                    )
            ),
            new AuthorCollectionResponse(
                    "author4",
                    Map.of("_self", BASE_LINK + "/author4",
                            "books", BASE_LINK + "/author4/books"
                    )
            )
    );

    private static List<AuthorReponse> authorReponses = List.of(
            new AuthorReponse(
                    "author1",
                    Map.of("_self", BASE_LINK + "/author1",
                            "books", BASE_LINK + "/author1/books"
                    )
            ),
            new AuthorReponse(
                    "author2",
                    Map.of("_self", BASE_LINK + "/author2",
                            "books", BASE_LINK + "/author2/books"
                    )
            ),
            new AuthorReponse(
                    "author3",
                    Map.of("_self", BASE_LINK + "/author3",
                            "books", BASE_LINK + "/author3/books"
                    )
            ),
            new AuthorReponse(
                    "author4",
                    Map.of("_self", BASE_LINK + "/author4",
                            "books", BASE_LINK + "/author4/books"
                    )
            )
    );

    public static List<AuthorCollectionQuery> getAuthorCollectionQueries() {
        return authorCollectionQueries;
    }

    public static AuthorCollectionQuery getAuthorCollectionQuery(int index) {
        return authorCollectionQueries.get(index);
    }

    public static List<AuthorQuery> getAuthorQueries() {
        return authorQueries;
    }

    public static AuthorQuery getAuthorQuery(int index) {
        return authorQueries.get(index);
    }

    public static List<AuthorCollectionResponse> getAuthorCollectionResponses() {
        return authorCollectionResponses;
    }

    public static AuthorCollectionResponse getAuthorCollectionResponse(int index) {
        return authorCollectionResponses.get(index);
    }

    public static List<AuthorReponse> getAuthorReponses() {
        return authorReponses;
    }

    public static AuthorReponse getAuthorReponse(int index) {
        return authorReponses.get(index);
    }

}
