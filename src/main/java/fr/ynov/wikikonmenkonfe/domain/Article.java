package main.java.fr.ynov.wikikonmenkonfe.domain;

public class Article {
    private String title;
    private String content;
    private Category category;
    private User author;
    private int views;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
        this.views = 0;
    }

    public Article(String title, String content, User author) {
        this(title, content);
        this.author = author;
    }

    public Article(String title, String content, User author, Category category) {
        this(title, content, author);
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public int getViews() {
        return views;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void read() {
        views++;
    }

    public void write(String title, String content) {
        this.content = content;
    }
}