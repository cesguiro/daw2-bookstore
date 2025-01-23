package es.cesguiro.exception;

public class LanguageException extends RuntimeException {

    private static final String DESCRIPTION = "Language error";

    public LanguageException(String message) {
        super(DESCRIPTION + ". " + message);
    }
}
