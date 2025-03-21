package main.java.fr.ynov.wikikonmenkonfe.factory;

import main.java.fr.ynov.wikikonmenkonfe.domain.Article;

public class ArticleFactory {

    public ArticleFactory() {
    }

    public Article createArticle(String title, String content) {
        return new Article(title, content);
    }
}
