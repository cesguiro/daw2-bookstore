package es.cesguiro.data;

import es.cesguiro.model.book.query.PublisherResponse;
import es.cesguiro.usecase.book.query.model.PublisherQuery;

import java.util.List;
import java.util.Map;

public class PublisherData {

    private static final String BASE_LINK = "http://localhost:8080/api/publishers";

    private static final List<PublisherQuery> publisherQueries = List.of(
            new PublisherQuery("publisher1", "publisher1"),
            new PublisherQuery("publisher2", "publisher2"),
            new PublisherQuery("publisher3", "publisher3"),
            new PublisherQuery("publisher4", "publisher4")
    );

    private static final List<PublisherResponse> publisherResponses = List.of(
            new PublisherResponse(
                    "publisher1",
                    Map.of("books", BASE_LINK + "/publisher1/books")
            ),
            new PublisherResponse(
                    "publisher2",
                    Map.of("books", BASE_LINK + "/publisher2/books")
            ),
            new PublisherResponse(
                    "publisher3",
                    Map.of("books", BASE_LINK + "/publisher3/books")
            ),
            new PublisherResponse(
                    "publisher4",
                    Map.of("books", BASE_LINK + "/publisher4/books")
            )
    );

    public static List<PublisherQuery> getPublisherQueries() {
        return publisherQueries;
    }

    public static PublisherQuery getPublisherQuery(int index) {
        return publisherQueries.get(index);
    }

    public static List<PublisherResponse> getPublisherResponses() {
        return publisherResponses;
    }

    public static PublisherResponse getPublisherResponse(int index) {
        return publisherResponses.get(index);
    }

}
