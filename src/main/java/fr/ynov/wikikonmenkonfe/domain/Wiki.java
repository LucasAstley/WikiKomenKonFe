package main.java.fr.ynov.wikikonmenkonfe.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import main.java.fr.ynov.wikikonmenkonfe.factory.ArticleFactory;
import main.java.fr.ynov.wikikonmenkonfe.factory.UserFactory;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Wiki {
    public String name;
    public List<User> usersList;
    private final String articlesResourcePath = "/articles.json";
    private final String articlesFileName = "articles.json";
    private final UserFactory userFactory;
    private final ArticleFactory articleFactory;
    private final Gson gson;

    /**
     * Constructor for the Wiki class
     *
     * @param name the name of the wiki
     * @throws IOException if an error occurs while reading the articles file
     */
    public Wiki(String name) {
        this.name = name;
        this.usersList = new ArrayList<>();
        this.userFactory = new UserFactory();
        this.articleFactory = new ArticleFactory();

        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(User.class, new UserTypeAdapter())
                .create();

        try (InputStream is = getClass().getResourceAsStream(articlesResourcePath)) {
            if (is == null) {
                saveArticles(new ArrayList<>());
            }
        } catch (IOException e) {
            System.err.println("Error initializing articles file: " + e.getMessage());
        }
    }

    /**
     * Adds a user to the wiki
     *
     * @param user the user to add
     */
    public void addUser(User user) {
        usersList.add(user);
    }

    /**
     * Add an article to the wiki
     *
     * @param article the article to add
     * @throws IOException if an error occurs while reading or writing the file
     */
    public void addArticle(Article article) {
        try {
            List<Article> articles = loadArticles();
            articles.add(article);
            saveArticles(articles);
        } catch (IOException e) {
            System.err.println("Error adding article: " + e.getMessage());
        }
    }

    /**
     * Removes an article from the wiki
     *
     * @param article the article to remove
     * @throws IOException if an error occurs while reading or writing the file
     */
    public void removeArticle(Article article) {
        try {
            List<Article> articles = loadArticles();
            articles.removeIf(a -> a.getTitle().equals(article.getTitle()));
            saveArticles(articles);
        } catch (IOException e) {
            System.err.println("Error removing article: " + e.getMessage());
        }
    }

    /**
     * Updates an existing article in the JSON local file
     *
     * @param article the article to update
     * @throws IOException if an error occurs while reading or writing the file
     */
    public void updateArticle(Article article) {
        try {
            List<Article> articles = loadArticles();
            for (int i = 0; i < articles.size(); i++) {
                if (articles.get(i).getTitle().equals(article.getTitle())) {
                    articles.set(i, article);
                    saveArticles(articles);
                    return;
                }
            }
        } catch (IOException e) {
            System.err.println("Error updating article: " + e.getMessage());
        }
    }

    /**
     * Loads articles from the JSON local file (or from the resource if the file does not exist)
     *
     * @return List of articles
     * @throws IOException if an error occurs while reading the file
     */
    private List<Article> loadArticles() throws IOException {
        File articlesFile = new File(articlesFileName);

        if (articlesFile.exists()) {
            try (java.io.Reader reader = new FileReader(articlesFile)) {
                Type articleListType = new TypeToken<ArrayList<Article>>() {
                }.getType();
                List<Article> articles = gson.fromJson(reader, articleListType);
                return articles != null ? articles : new ArrayList<>();
            }
        }

        try (InputStream is = getClass().getResourceAsStream(articlesResourcePath)) {
            if (is == null) {
                return new ArrayList<>();
            }

            try (java.io.Reader reader = new InputStreamReader(is)) {
                Type articleListType = new TypeToken<ArrayList<Article>>() {
                }.getType();
                List<Article> articles = gson.fromJson(reader, articleListType);
                return articles != null ? articles : new ArrayList<>();
            }
        }
    }

    /**
     * Saves articles to the JSON local file
     *
     * @param articles List of articles to save
     * @throws IOException if an error occurs while writing the file
     */
    private void saveArticles(List<Article> articles) throws IOException {
        File articlesFile = new File(articlesFileName);

        try (FileWriter writer = new FileWriter(articlesFile)) {
            gson.toJson(articles, writer);
        } catch (IOException e) {
            System.err.println("Error saving articles: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Searches for articles containing the search string in their title or content
     *
     * @param search the search string
     * @return a list of articles matching the search criteria
     * @throws IOException if an error occurs while reading the file
     */
    public List<Article> searchArticle(String search) {
        try {
            List<Article> articles = loadArticles();
            if (search == null || search.isEmpty()) {
                return articles;
            }

            List<Article> results = new ArrayList<>();
            for (Article article : articles) {
                if (article.getTitle().toLowerCase().contains(search.toLowerCase()) ||
                        article.getContent().toLowerCase().contains(search.toLowerCase())) {
                    results.add(article);
                }
            }
            return results;
        } catch (IOException e) {
            System.err.println("Error searching articles: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Creates a new user of the specified type
     *
     * @param name the name of the user
     * @param type the type of user to create based on the UserType enum
     * @return the created user
     */
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

    /**
     * Gets the list of articles from the JSON local file
     *
     * @return List of articles
     * @throws IOException if an error occurs while reading the file
     */
    public List<Article> getArticlesList() {
        try {
            return loadArticles();
        } catch (IOException e) {
            System.err.println("Error loading articles: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public enum UserType {
        READER, WRITER, MODERATOR, ADMIN
    }

    public String getName() {
        return this.name;
    }
}