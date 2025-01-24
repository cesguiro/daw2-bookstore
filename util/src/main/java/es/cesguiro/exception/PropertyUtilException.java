package es.cesguiro.exception;

public class PropertyUtilException extends RuntimeException {

    private static final String DESCRIPTION = "Property util error";

    public PropertyUtilException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
