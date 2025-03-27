package main.java.fr.ynov.wikikonmenkonfe.exception;

public class PermissionDeniedException extends Exception {
    public PermissionDeniedException() {
        super("Permission denied");
    }

    public PermissionDeniedException(String message) {
        super(message);
    }
}