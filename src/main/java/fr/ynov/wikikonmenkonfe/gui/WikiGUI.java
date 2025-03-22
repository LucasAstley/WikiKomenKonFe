package main.java.fr.ynov.wikikonmenkonfe.gui;

import main.java.fr.ynov.wikikonmenkonfe.domain.Article;
import main.java.fr.ynov.wikikonmenkonfe.domain.User;
import main.java.fr.ynov.wikikonmenkonfe.domain.Wiki;
import main.java.fr.ynov.wikikonmenkonfe.gui.frames.MainFrame;
import main.java.fr.ynov.wikikonmenkonfe.gui.frames.UserFrame;
import main.java.fr.ynov.wikikonmenkonfe.gui.panels.ArticlePanel;
import main.java.fr.ynov.wikikonmenkonfe.gui.panels.UserPanel;
import main.java.fr.ynov.wikikonmenkonfe.gui.panels.WelcomePanel;

import javax.swing.*;
import java.util.List;

public class WikiGUI {
    private final MainFrame mainFrame;
    private final Wiki wiki;
    private User currentUser;

    public static final String WELCOME_PANEL = "Welcome";
    public static final String ARTICLE_PANEL = "Article";
    public static final String USER_PANEL = "User";

    public WikiGUI(Wiki wiki) {
        this.wiki = wiki;
        mainFrame = new MainFrame(this, wiki);
    }

    public void showGUI() {
        mainFrame.setVisible(true);
    }

    public void navigateTo(String panelName) {
        mainFrame.navigateTo(panelName);

        if (USER_PANEL.equals(panelName) && currentUser != null) {
            UserPanel userPanel = getUserPanel();
            userPanel.setUser(currentUser);
        }
    }

    public Wiki getWiki() {
        return wiki;
    }

    public void showLoginDialog() {
        UserFrame userFrame = new UserFrame(mainFrame, wiki);
        userFrame.setVisible(true);

        User loggedInUser = userFrame.getLoggedInUser();
        if (loggedInUser != null) {
            this.currentUser = loggedInUser;
            JOptionPane.showMessageDialog(mainFrame, "Welcome, " + loggedInUser.getName() + "!");
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void performSearch(String query) {
        List<Article> results = wiki.searchArticle(query);
        WelcomePanel welcomePanel = getWelcomePanel();
        welcomePanel.updateSearchResults(results);
    }

    public void displayArticle(Article article) {
        if (article != null) {
            ArticlePanel articlePanel = getArticlePanel();
            articlePanel.setArticle(article);
            navigateTo(ARTICLE_PANEL);
        }
    }

    private WelcomePanel getWelcomePanel() {
        return (WelcomePanel) ((JPanel) mainFrame.getContentPanel().getComponent(0));
    }

    private ArticlePanel getArticlePanel() {
        return (ArticlePanel) ((JPanel) mainFrame.getContentPanel().getComponent(1));
    }

    private UserPanel getUserPanel() {
        return (UserPanel) ((JPanel) mainFrame.getContentPanel().getComponent(2));
    }
}