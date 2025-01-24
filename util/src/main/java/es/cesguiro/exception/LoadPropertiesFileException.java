package es.cesguiro.exception;

public class LoadPropertiesFileException extends RuntimeException {

    private static final String DESCRIPTION = "Failed to load properties file: ";

    public LoadPropertiesFileException(String message, Throwable cause) {
        super(DESCRIPTION + message, cause);
    }
}
