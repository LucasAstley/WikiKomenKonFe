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

    /**
     * Constructor for the GUI
     *
     * @param wiki the wiki to display
     */
    public WikiGUI(Wiki wiki) {
        this.wiki = wiki;
        mainFrame = new MainFrame(this, wiki);
    }

    public void showGUI() {
        mainFrame.setVisible(true);
    }

    /**
     * Navigates to the specified panel
     *
     * @param panelName the name of the panel you want to navigate to
     */
    public void navigateTo(String panelName) {
        if (USER_PANEL.equals(panelName)) {
            try {
                if (currentUser == null) {
                    throw new AuthenticationRequiredException("You must be logged in to write articles");
                }
                UserPanel userPanel = getUserPanel();
                userPanel.setUser(currentUser);
                mainFrame.navigateTo(panelName);
            } catch (AuthenticationRequiredException e) {
                JOptionPane.showMessageDialog(mainFrame,
                        e.getMessage(),
                        "Authentication Required",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
        } else {
            mainFrame.navigateTo(panelName);
        }

        if (WELCOME_PANEL.equals(panelName)) {
            getWelcomePanel().clearSelection();
        }
    }

    public Wiki getWiki() {
        return wiki;
    }

    /**
     * Show the user login dialog
     */
    public void showLoginDialog() {
        LoginFrame loginFrame = new LoginFrame(mainFrame, wiki);
        loginFrame.setVisible(true);

        User loggedInUser = loginFrame.getLoggedInUser();
        if (loggedInUser != null) {
            this.currentUser = loggedInUser;
            JOptionPane.showMessageDialog(mainFrame, "Welcome, " + loggedInUser.getName() + "!");
        }
    }

    /**
     * Show the article write dialog
     */
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

    /**
     * Show the edit dialog for the specified article
     *
     * @param articleToEdit the article to edit
     */
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
                    e.getMessage(),
                    "Permission Denied",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Perform a search for the specified query
     *
     * @param query the string content to search for
     */
    public void performSearch(String query) {
        List<Article> results = wiki.searchArticle(query);
        WelcomePanel welcomePanel = getWelcomePanel();
        welcomePanel.updateSearchResults(results);
    }

    /**
     * Update the list of articles in the welcome panel
     */
    public void updateArticlesList() {
        List<Article> allArticles = wiki.getArticlesList();
        WelcomePanel welcomePanel = getWelcomePanel();
        welcomePanel.updateSearchResults(allArticles);
    }

    /**
     * Display the specified article in the article panel
     *
     * @param article the article to display
     */
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
