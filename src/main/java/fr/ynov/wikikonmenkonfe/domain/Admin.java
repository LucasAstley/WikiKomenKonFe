package main.java.fr.ynov.wikikonmenkonfe.domain;

public class Admin extends User {

    /**
     * Constructor for the Admin class
     *
     * @param name the name of the user
     */
    public Admin(String name) {
        super(name);
    }

    /**
     * Method to delete an article from a wiki
     * @param article the article to delete
     * @param wiki the wiki to delete the article from
     */
    public void deleteArticle(Article article, Wiki wiki) {
        wiki.removeArticle(article);
    }
}