package es.cesguiro.exception;

public class AppFileNotFoundException extends RuntimeException {

    private static final String DESCRIPTION = "File not found: ";

    public AppFileNotFoundException(String message) {
        super(DESCRIPTION + message);
    }
}
