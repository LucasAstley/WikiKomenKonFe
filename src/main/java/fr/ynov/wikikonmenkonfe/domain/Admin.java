package main.java.fr.ynov.wikikonmenkonfe.domain;

public class Admin extends User {
    public Admin(String name) {
        super(name);
    }

    public void deleteArticle(Article article, Wiki wiki) {
        wiki.removeArticle(article);
    }
}