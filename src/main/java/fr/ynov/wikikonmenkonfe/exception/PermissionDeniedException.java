package main.java.fr.ynov.wikikonmenkonfe.exception;

/**
 * Exception thrown when a user does not have the necessary permissions to perform an action
 */
public class PermissionDeniedException extends Exception {

    public PermissionDeniedException() {
        super("Permission denied");
    }

    public PermissionDeniedException(String message) {
        super(message);
    }
}