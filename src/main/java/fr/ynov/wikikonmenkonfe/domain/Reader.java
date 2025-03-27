package main.java.fr.ynov.wikikonmenkonfe.domain;

public class Reader extends User {

    public Reader(String name) {
        super(name);
    }

    @Override
    public void viewArticle(Article article) {
        article.read();
    }
}
