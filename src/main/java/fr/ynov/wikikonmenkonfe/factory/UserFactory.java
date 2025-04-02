package main.java.fr.ynov.wikikonmenkonfe.factory;

import main.java.fr.ynov.wikikonmenkonfe.domain.User;
import main.java.fr.ynov.wikikonmenkonfe.domain.Reader;
import main.java.fr.ynov.wikikonmenkonfe.domain.Writer;
import main.java.fr.ynov.wikikonmenkonfe.domain.Moderator;
import main.java.fr.ynov.wikikonmenkonfe.domain.Admin;

/**
 * Factory class for creating User objects
 */
public class UserFactory {

    public UserFactory() {
    }

    /**
     * Creates a User object based on the type of user
     *
     * @param name the name of the user
     * @return a User object
     */
    public User createReader(String name) {
        return new Reader(name);
    }

    /**
     * Creates a User object based on the type of user
     *
     * @param name the name of the user
     * @return a User object
     */
    public User createWriter(String name) {
        return new Writer(name);
    }

    /**
     * Creates a User object based on the type of user
     *
     * @param name the name of the user
     * @return a User object
     */
    public User createModerator(String name) {
        return new Moderator(name);
    }

    /**
     * Creates a User object based on the type of user
     *
     * @param name the name of the user
     * @return a User object (Admin type)
     */
    public User createAdmin(String name) {
        return new Admin(name);
    }
}
