package main.java.fr.ynov.wikikonmenkonfe.domain;

public class Article {
    private final String title;
    private String content;
    private Category category;
    private User author;
    private int views;
    private int likes;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
        this.views = 0;
        this.likes = 0;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public int getViews() {
        return views;
    }

    public int getLikes() {
        return likes;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void like() {
        likes++;
    }

    public Article read() {
        views++;
        return this;
    }

    public void write(String title, String content) {
        this.content = content;
    }
}