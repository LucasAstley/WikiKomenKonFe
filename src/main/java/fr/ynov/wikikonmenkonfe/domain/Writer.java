package main.java.fr.ynov.wikikonmenkonfe.domain;

import main.java.fr.ynov.wikikonmenkonfe.factory.ArticleFactory;

import java.util.ArrayList;
import java.util.List;

public class Writer extends User {
    private List<Article> writtenArticles;

    public Writer(String name) {
        super(name);
        this.writtenArticles = new ArrayList<>();
    }

    @Override
    public void viewArticle(Article article) {
        article.read();
    }

    public Article writeArticle(String title, String content, ArticleFactory factory) {
        Article article = factory.createArticle(title, content);
        writtenArticles.add(article);
        return article;
    }

    public void editArticle(Article article, String newContent) {
        article.setContent(newContent);
    }

    public List<Article> getWrittenArticles() {
        return new ArrayList<>(writtenArticles);
    }
}