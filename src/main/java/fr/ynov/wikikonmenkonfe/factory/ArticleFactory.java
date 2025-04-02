package main.java.fr.ynov.wikikonmenkonfe.factory;

import main.java.fr.ynov.wikikonmenkonfe.domain.Article;
import main.java.fr.ynov.wikikonmenkonfe.domain.User;
import main.java.fr.ynov.wikikonmenkonfe.domain.Category;
import main.java.fr.ynov.wikikonmenkonfe.domain.Wiki;

/**
 * Factory class for creating Article objects
 */
public class ArticleFactory {

    public ArticleFactory() {
    }

    /**
     * Creates an Article object with the given title and content.
     *
     * @param title   The title of the article
     * @param content The content of the article
     * @return A new Article object
     */
    public static Article createArticle(String title, String content) {
        return new Article(title, content);
    }

    /**
     * Creates an Article object with the given title, content, and author.
     *
     * @param title   The title of the article
     * @param content The content of the article
     * @param author  The author of the article
     * @return A new Article object
     */
    public static Article createArticle(String title, String content, User author) {
        return new Article(title, content, author);
    }

    /**
     * Creates an Article object with the given title, content, author, and category.
     *
     * @param title    The title of the article
     * @param content  The content of the article
     * @param author   The author of the article
     * @param category The category of the article
     * @return A new Article object
     */
    public static Article createArticle(String title, String content, User author, Category category) {
        return new Article(title, content, author, category);
    }
}