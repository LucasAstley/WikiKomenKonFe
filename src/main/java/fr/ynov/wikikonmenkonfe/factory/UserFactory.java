package main.java.fr.ynov.wikikonmenkonfe.factory;

import main.java.fr.ynov.wikikonmenkonfe.domain.User;
import main.java.fr.ynov.wikikonmenkonfe.domain.Reader;
import main.java.fr.ynov.wikikonmenkonfe.domain.Writer;
import main.java.fr.ynov.wikikonmenkonfe.domain.Moderator;
import main.java.fr.ynov.wikikonmenkonfe.domain.Admin;

public class UserFactory {

    public UserFactory() {
    }

    public User createReader(String name) {
        return new Reader(name);
    }

    public User createWriter(String name) {
        return new Writer(name);
    }

    public User createModerator(String name) {
        return new Moderator(name);
    }

    public User createAdmin(String name) {
        return new Admin(name);
    }
}
