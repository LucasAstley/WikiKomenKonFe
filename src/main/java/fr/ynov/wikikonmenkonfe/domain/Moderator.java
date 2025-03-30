package main.java.fr.ynov.wikikonmenkonfe.domain;

public class Moderator extends User {
    public Moderator(String name) {
        super(name);
    }

    public void deleteArticle(Article article, Wiki wiki) {
        wiki.removeArticle(article);
    }
}