package main.java.fr.ynov.wikikonmenkonfe.domain;

public class Admin extends User {
    public Admin(String name) {
        super(name);
    }

    @Override
    public void viewArticle(Article article) {
        article.read();
    }

    public void writeArticle(String title, String content, Wiki wiki) {
        Article article = new Article(title, content);
        wiki.addArticle(article);
    }

    public void editArticle(Article article, String newContent) {
        article.setContent(newContent);
    }

    public void deleteArticle(Article article, Wiki wiki) {
        wiki.removeArticle(article);
    }

    public void addUser(User user, Wiki wiki) {
        wiki.addUser(user);
    }

    public void removeUser(User user, Wiki wiki) {
        wiki.usersList.remove(user);
    }
}