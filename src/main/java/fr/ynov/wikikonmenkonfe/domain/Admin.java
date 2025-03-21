package main.java.fr.ynov.wikikonmenkonfe.domain;

public class Admin extends User {

    public Admin(String name) {
        super(name);
    }

    @Override
    public void viewArticle(Article article) {
        article.read();
    }
}
