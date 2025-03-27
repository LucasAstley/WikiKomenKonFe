package main.java.fr.ynov.wikikonmenkonfe.domain;

import main.java.fr.ynov.wikikonmenkonfe.factory.ArticleFactory;
import main.java.fr.ynov.wikikonmenkonfe.factory.UserFactory;

import java.util.ArrayList;
import java.util.List;

public class Wiki {
    public String name;
    public List<User> usersList;
    public List<Article> articlesList;
    private final UserFactory userFactory;
    private final ArticleFactory articleFactory;

    public Wiki(String name) {
        this.name = name;
        this.usersList = new ArrayList<>();
        this.articlesList = new ArrayList<>();
        this.userFactory = new UserFactory();
        this.articleFactory = new ArticleFactory();
    }

    public void addUser(User user) {
        usersList.add(user);
    }

    public void addArticle(Article article) {
        articlesList.add(article);
    }

    public List<Article> searchArticle(String search) {
        if (search == null || search.isEmpty()) {
            return articlesList;
        }
        List<Article> results = new ArrayList<>();
        for (Article article : articlesList) {
            if (article.getTitle().toLowerCase().contains(search.toLowerCase()) ||
                    article.getContent().contains(search.toLowerCase())) {
                results.add(article);
            }
        }
        return results;
    }

    public User createUser(String name, UserType type) {
        User user = switch (type) {
            case READER -> userFactory.createReader(name);
            case WRITER -> userFactory.createWriter(name);
            case MODERATOR -> userFactory.createModerator(name);
            case ADMIN -> userFactory.createAdmin(name);
        };
        addUser(user);
        return user;
    }

    public enum UserType {
        READER, WRITER, MODERATOR, ADMIN
    }

    public String getName() {
        return this.name;
    }
}