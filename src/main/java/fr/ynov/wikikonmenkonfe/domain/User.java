package main.java.fr.ynov.wikikonmenkonfe.domain;

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
