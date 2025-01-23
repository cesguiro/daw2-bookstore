package es.cesguiro.exception;

public class LoadPropertyFileException extends RuntimeException {

    private static final String DESCRIPTION = "Failed to load properties file: ";

    public LoadPropertyFileException(String message, Throwable cause) {
        super(DESCRIPTION + message, cause);
    }
}
