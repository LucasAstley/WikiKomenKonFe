package main.java.fr.ynov.wikikonmenkonfe.domain;

public class Writer extends User {

    public Writer(String name) {
        super(name);
    }

    @Override
    public void viewArticle(Article article) {
        article.read();
    }
}
