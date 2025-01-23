package es.cesguiro.exception;

public class KeyNotFoundException extends RuntimeException {

    private static final String DESCRIPTION = "Key not found: ";

    public KeyNotFoundException(String key) {
        super(DESCRIPTION + key);
    }
}
