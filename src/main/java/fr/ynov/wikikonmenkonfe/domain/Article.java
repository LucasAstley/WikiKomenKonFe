package main.java.fr.ynov.wikikonmenkonfe.domain;

public class Article {

    private final String title;
    private final String content;
    private int views;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
        this.views = 0;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getViews() {
        return views;
    }

    public Article read() {
        views++;
        return this;
    }
}
