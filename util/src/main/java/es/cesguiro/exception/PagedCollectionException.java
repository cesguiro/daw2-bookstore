package es.cesguiro.exception;

public class PagedCollectionException extends RuntimeException {

        private static final String DESCRIPTION = "Failed to load paged collection: ";

        public PagedCollectionException(String message) {
            super(DESCRIPTION + message);
        }
}
