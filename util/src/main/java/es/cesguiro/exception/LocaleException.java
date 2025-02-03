package es.cesguiro.exception;

public class LocaleException extends RuntimeException {

    private static final String DESCRIPTION = "Locale error";

    public LocaleException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
