package main.java.fr.ynov.wikikonmenkonfe.main;

import main.java.fr.ynov.wikikonmenkonfe.domain.Article;
import main.java.fr.ynov.wikikonmenkonfe.domain.User;
import main.java.fr.ynov.wikikonmenkonfe.factory.UserFactory;
import main.java.fr.ynov.wikikonmenkonfe.domain.Wiki;
import main.java.fr.ynov.wikikonmenkonfe.gui.WikiGUI;
import main.java.fr.ynov.wikikonmenkonfe.domain.Category;

import javax.swing.*;

public class ApplicationRunner {

    public static void main(String[] args) {
        Wiki wiki = new Wiki("WikiKonMenKonfé");

        User writer = new UserFactory().createWriter("John Doe");
        wiki.addUser(writer);

        Article article1 = new Article("Awesome article", "This is a test article", writer, Category.TECHNOLOGY);
        wiki.addArticle(article1);

        Article article2 = new Article("Another awesome article", "This is another test article", writer, Category.SCIENCE);
        wiki.addArticle(article2);

        SwingUtilities.invokeLater(() -> {
            WikiGUI gui = new WikiGUI(wiki);
            gui.showGUI();
        });
    }
}