package main.java.fr.ynov.wikikonmenkonfe.factory;

import main.java.fr.ynov.wikikonmenkonfe.domain.Article;
import main.java.fr.ynov.wikikonmenkonfe.domain.User;
import main.java.fr.ynov.wikikonmenkonfe.domain.Category;

public class ArticleFactory {
    public ArticleFactory() {
    }

    public Article createArticle(String title, String content) {
        return new Article(title, content);
    }

    public Article createArticle(String title, String content, User author) {
        return new Article(title, content, author);
    }

    public Article createArticle(String title, String content, User author, Category category) {
        return new Article(title, content, author, category);
    }
}