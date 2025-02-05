package es.cesguiro.controller.data;

import es.cesguiro.model.book.query.PublisherResponse;

import java.util.List;
import java.util.Map;

public class PublisherData {

    private static final String BASE_LINK = "http://localhost:8080/api/publishers";

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

    public static List<PublisherResponse> getPublisherResponses() {
        return publisherResponses;
    }

    public static PublisherResponse getPublisherResponse(int index) {
        return publisherResponses.get(index);
    }

}
