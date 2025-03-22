package main.java.fr.ynov.wikikonmenkonfe.gui.panels;

import main.java.fr.ynov.wikikonmenkonfe.domain.Article;
import main.java.fr.ynov.wikikonmenkonfe.gui.WikiGUI;
import main.java.fr.ynov.wikikonmenkonfe.gui.buttons.ActionButton;
import main.java.fr.ynov.wikikonmenkonfe.gui.buttons.NavigationButton;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WelcomePanel extends JPanel {
    private final WikiGUI mainFrame;
    private final JTextField searchField;
    private final JList<Article> resultsList;
    private final DefaultListModel<Article> resultsModel;

    public WelcomePanel(WikiGUI mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("WikiKomenKonFé");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel searchPanel = new JPanel(new BorderLayout(5, 0));
        searchField = new JTextField();
        JButton searchButton = ActionButton.createSearchButton(searchField, mainFrame);
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        resultsModel = new DefaultListModel<>();
        resultsList = new JList<>(resultsModel);
        resultsList.setCellRenderer(new ArticleListCellRenderer());
        resultsList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Article selectedArticle = resultsList.getSelectedValue();
                if (selectedArticle != null) {
                    mainFrame.displayArticle(selectedArticle);
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(resultsList);

        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        navPanel.add(ActionButton.createLoginButton(mainFrame));
        navPanel.add(NavigationButton.createProfileButton(mainFrame));

        JPanel centerPanel = new JPanel(new BorderLayout(0, 10));
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(titleLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(navPanel, BorderLayout.SOUTH);

        List<Article> allArticles = mainFrame.getWiki().articlesList;
        for (Article article : allArticles) {
            resultsModel.addElement(article);
        }
    }

    public void updateSearchResults(List<Article> results) {
        resultsModel.clear();
        for (Article article : results) {
            resultsModel.addElement(article);
        }
    }

    private static class ArticleListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Article article) {
                setText(article.getTitle());
            }
            return this;
        }
    }
}