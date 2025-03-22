package main.java.fr.ynov.wikikonmenkonfe.gui.frames;

import main.java.fr.ynov.wikikonmenkonfe.domain.Wiki;
import main.java.fr.ynov.wikikonmenkonfe.gui.WikiGUI;
import main.java.fr.ynov.wikikonmenkonfe.gui.panels.ArticlePanel;
import main.java.fr.ynov.wikikonmenkonfe.gui.panels.UserPanel;
import main.java.fr.ynov.wikikonmenkonfe.gui.panels.WelcomePanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel contentPanel;

    public MainFrame(WikiGUI wikiGUI, Wiki wiki) {
        setTitle("WikiKomenKonFé");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        WelcomePanel welcomePanel = new WelcomePanel(wikiGUI);
        ArticlePanel articlePanel = new ArticlePanel(wikiGUI);
        UserPanel userPanel = new UserPanel(wikiGUI);

        contentPanel.add(welcomePanel, WikiGUI.WELCOME_PANEL);
        contentPanel.add(articlePanel, WikiGUI.ARTICLE_PANEL);
        contentPanel.add(userPanel, WikiGUI.USER_PANEL);

        cardLayout.show(contentPanel, WikiGUI.WELCOME_PANEL);

        add(contentPanel);
        pack();
        setLocationRelativeTo(null);
    }

    public void navigateTo(String panelName) {
        cardLayout.show(contentPanel, panelName);
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}