package main.java.fr.ynov.wikikonmenkonfe.gui;

import main.java.fr.ynov.wikikonmenkonfe.domain.*;
import main.java.fr.ynov.wikikonmenkonfe.exception.AuthenticationRequiredException;
import main.java.fr.ynov.wikikonmenkonfe.exception.PermissionDeniedException;
import main.java.fr.ynov.wikikonmenkonfe.gui.frames.MainFrame;
import main.java.fr.ynov.wikikonmenkonfe.gui.frames.LoginFrame;
import main.java.fr.ynov.wikikonmenkonfe.gui.frames.WriteFrame;
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

        if (WELCOME_PANEL.equals(panelName)) {
            getWelcomePanel().clearSelection();
        } else if (USER_PANEL.equals(panelName) && currentUser != null) {
            UserPanel userPanel = getUserPanel();
            userPanel.setUser(currentUser);
        }
    }

    public Wiki getWiki() {
        return wiki;
    }

    public void showLoginDialog() {
        LoginFrame loginFrame = new LoginFrame(mainFrame, wiki);
        loginFrame.setVisible(true);

        User loggedInUser = loginFrame.getLoggedInUser();
        if (loggedInUser != null) {
            this.currentUser = loggedInUser;
            JOptionPane.showMessageDialog(mainFrame, "Welcome, " + loggedInUser.getName() + "!");
        }
    }

    public void showWriteDialog() {
        try {
            if (currentUser == null) {
                throw new AuthenticationRequiredException("You must be logged in to write articles");
            }

            if (!(currentUser instanceof Admin || currentUser instanceof Writer)) {
                throw new PermissionDeniedException("Only Writers and Administrators can write articles");
            }

            WriteFrame writeFrame = new WriteFrame(mainFrame, this, null);
            writeFrame.setVisible(true);

        } catch (AuthenticationRequiredException e) {
            JOptionPane.showMessageDialog(mainFrame,
                    e.getMessage(),
                    "Authentication Required",
                    JOptionPane.WARNING_MESSAGE);
        } catch (PermissionDeniedException e) {
            JOptionPane.showMessageDialog(mainFrame,
                    "PermissionDeniedException: " + e.getMessage(),
                    "Permission Denied",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void showEditDialog(Article articleToEdit) {
        try {
            if (currentUser == null) {
                throw new AuthenticationRequiredException("You must be logged in to edit articles");
            }

            if (!(currentUser instanceof Admin)) {
                throw new PermissionDeniedException("Only Administrators can edit articles");
            }

            WriteFrame writeFrame = new WriteFrame(mainFrame, this, articleToEdit);
            writeFrame.setVisible(true);

        } catch (AuthenticationRequiredException e) {
            JOptionPane.showMessageDialog(mainFrame,
                    e.getMessage(),
                    "Authentication Required",
                    JOptionPane.WARNING_MESSAGE);
        } catch (PermissionDeniedException e) {
            JOptionPane.showMessageDialog(mainFrame,
                    "PermissionDeniedException: " + e.getMessage(),
                    "Permission Denied",
                    JOptionPane.ERROR_MESSAGE);
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
    
    public void updateArticlesList() {
        List<Article> allArticles = wiki.articlesList;
        WelcomePanel welcomePanel = getWelcomePanel();
        welcomePanel.updateSearchResults(allArticles);
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
