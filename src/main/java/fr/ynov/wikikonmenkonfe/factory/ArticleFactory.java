package main.java.fr.ynov.wikikonmenkonfe.factory;

import main.java.fr.ynov.wikikonmenkonfe.domain.Article;
import main.java.fr.ynov.wikikonmenkonfe.domain.User;
import main.java.fr.ynov.wikikonmenkonfe.domain.Category;
import main.java.fr.ynov.wikikonmenkonfe.domain.Wiki;

public class ArticleFactory {
    public ArticleFactory() {
    }

    public static Article createArticle(String title, String content) {
        return new Article(title, content);
    }

    public static Article createArticle(String title, String content, User author) {
        return new Article(title, content, author);
    }

    public static Article createArticle(String title, String content, User author, Category category) {
        return new Article(title, content, author, category);
    }

    public static void addArticleToWiki(Wiki wiki, Article article) {
        wiki.addArticle(article);
    }
}