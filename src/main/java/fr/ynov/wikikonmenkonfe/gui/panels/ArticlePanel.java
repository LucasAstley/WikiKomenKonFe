package main.java.fr.ynov.wikikonmenkonfe.gui.panels;

import main.java.fr.ynov.wikikonmenkonfe.domain.Admin;
import main.java.fr.ynov.wikikonmenkonfe.domain.Article;
import main.java.fr.ynov.wikikonmenkonfe.domain.Category;
import main.java.fr.ynov.wikikonmenkonfe.domain.Moderator;
import main.java.fr.ynov.wikikonmenkonfe.domain.User;
import main.java.fr.ynov.wikikonmenkonfe.gui.WikiGUI;
import main.java.fr.ynov.wikikonmenkonfe.gui.buttons.NavigationButton;

import javax.swing.*;
import java.awt.*;

public class ArticlePanel extends JPanel {
    private final WikiGUI mainFrame;
    private final JLabel titleLabel;
    private final JLabel authorLabel;
    private final JLabel categoryLabel;
    private final JLabel viewsLabel;
    private final JTextArea contentArea;
    private Article currentArticle;
    private JButton deleteButton;
    private JButton editButton;

    /**
     * The article panel that displays the content of an article
     *
     */
    public ArticlePanel(WikiGUI mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        titleLabel = new JLabel("Title");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        authorLabel = new JLabel("Author:");
        authorLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        authorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        categoryLabel = new JLabel("Category:");
        categoryLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        categoryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        viewsLabel = new JLabel("Views: 0");
        statsPanel.add(viewsLabel);
        statsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(authorLabel);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(categoryLabel);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(statsPanel);

        contentArea = new JTextArea();
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setEditable(false);
        contentArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(contentArea);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(NavigationButton.createBackButton(mainFrame));

        deleteButton = new JButton("Remove Article");
        deleteButton.setVisible(false);
        deleteButton.addActionListener(e -> deleteArticle());
        
        editButton = new JButton("Edit Article");
        editButton.setVisible(false);
        editButton.addActionListener(e -> editArticle());
        
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);

        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setArticle(Article article) {
        this.currentArticle = article;
        titleLabel.setText(article.getTitle());

        User author = article.getAuthor();
        if (author != null) {
            authorLabel.setText("Author: " + author.getName());
        } else {
            authorLabel.setText("Author: Unknown");
        }

        Category category = article.getCategory();
        if (category != null) {
            categoryLabel.setText("Category: " + category.getCategory());
        } else {
            categoryLabel.setText("Category: Uncategorized");
        }

        viewsLabel.setText("Views: " + article.getViews());
        contentArea.setText(article.getContent());
        article.read();
        
        updateButtonVisibility();
    }
    
    private void updateButtonVisibility() {
        User currentUser = mainFrame.getCurrentUser();

        boolean canDelete = currentUser instanceof Moderator || currentUser instanceof Admin;
        boolean canEdit = currentUser instanceof Admin;
        
        deleteButton.setVisible(canDelete);
        editButton.setVisible(canEdit);
    }
    
    private void deleteArticle() {
        User currentUser = mainFrame.getCurrentUser();
        if (currentUser == null) return;
        
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this article?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);
                
        if (confirm == JOptionPane.YES_OPTION) {
            if (currentUser instanceof Admin) {
                ((Admin) currentUser).deleteArticle(currentArticle, mainFrame.getWiki());
                JOptionPane.showMessageDialog(this, "Article deleted successfully");
                mainFrame.updateArticlesList();
                mainFrame.navigateTo(WikiGUI.WELCOME_PANEL);
            } else if (currentUser instanceof Moderator) {
                ((Moderator) currentUser).deleteArticle(currentArticle, mainFrame.getWiki());
                JOptionPane.showMessageDialog(this, "Article deleted successfully");
                mainFrame.updateArticlesList();
                mainFrame.navigateTo(WikiGUI.WELCOME_PANEL);
            }
        }
    }
    
    private void editArticle() {
        User currentUser = mainFrame.getCurrentUser();
        if (!(currentUser instanceof Admin)) return;

        mainFrame.showEditDialog(currentArticle);
    }
}

