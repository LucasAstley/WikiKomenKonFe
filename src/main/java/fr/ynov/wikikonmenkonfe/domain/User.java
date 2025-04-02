package main.java.fr.ynov.wikikonmenkonfe.domain;

/**
 * Abstract class representing a user of the wiki
 * This class serves as a base for different types of users that will be created by inheritance
 */
public abstract class User {

    protected String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return this.getClass().getSimpleName();
    }
}
