package es.cesguiro.exception;

public class DomainException extends RuntimeException {

    private static final String DESCRIPTION = "Domain exception: ";

    public DomainException(String message) {
        super(DESCRIPTION + message);
    }
}
