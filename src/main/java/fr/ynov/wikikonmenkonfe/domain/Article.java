package main.java.fr.ynov.wikikonmenkonfe.domain;

public class Article {
    private String title;
    private String content;
    private Category category;
    private User author;
    private int views;

    /**
     * Constructor for the Article class
     *
     * @param title   the title of the article
     * @param content the content of the article
     */
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
        this.views = 0;
    }

    /**
     * Constructor for the Article class with author
     *
     * @param title   the title of the article
     * @param content the content of the article
     * @param author  the author of the article
     */
    public Article(String title, String content, User author) {
        this(title, content);
        this.author = author;
    }

    /**
     * Constructor for the Article class with author and category
     *
     * @param title    the title of the article
     * @param content  the content of the article
     * @param author   the author of the article
     * @param category the category of the article
     */
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

    /**
     * Read the article and increment the view counter
     */
    public void read() {
        this.views++;
    }
}