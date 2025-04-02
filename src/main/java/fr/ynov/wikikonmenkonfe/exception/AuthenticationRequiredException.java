package main.java.fr.ynov.wikikonmenkonfe.exception;

/**
 * Exception thrown when authentication is required to perform an action
 */
public class AuthenticationRequiredException extends Exception {

    public AuthenticationRequiredException() {
        super("Authentication required");
    }

    public AuthenticationRequiredException(String message) {
        super(message);
    }
}