package main.java.fr.ynov.wikikonmenkonfe.exception;

public class AuthenticationRequiredException extends Exception {
    public AuthenticationRequiredException() {
        super("Authentication required");
    }

    public AuthenticationRequiredException(String message) {
        super(message);
    }
}