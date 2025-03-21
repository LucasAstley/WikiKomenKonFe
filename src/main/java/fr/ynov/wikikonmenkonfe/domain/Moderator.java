package main.java.fr.ynov.wikikonmenkonfe.domain;

public class Moderator extends User {

    public Moderator(String name) {
        super(name);
    }

    @Override
    public void viewArticle(Article article) {
        article.read();
    }
}
