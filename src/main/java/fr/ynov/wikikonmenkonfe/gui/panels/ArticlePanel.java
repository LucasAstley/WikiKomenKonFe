package main.java.fr.ynov.wikikonmenkonfe.gui.panels;

import main.java.fr.ynov.wikikonmenkonfe.domain.Article;
import main.java.fr.ynov.wikikonmenkonfe.domain.Category;
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
    private final JLabel likesLabel;
    private final JTextArea contentArea;
    private final JButton likeButton;
    private Article currentArticle;

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
        likesLabel = new JLabel("Likes: 0");
        statsPanel.add(viewsLabel);
        statsPanel.add(likesLabel);
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

        likeButton = new JButton("Like");
        likeButton.addActionListener(e -> {
            if (currentArticle != null) {
                currentArticle.like();
                likesLabel.setText("Likes: " + currentArticle.getLikes());
            }
        });
        buttonPanel.add(likeButton);

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
        likesLabel.setText("Likes: " + article.getLikes());
        contentArea.setText(article.getContent());
        article.read();
    }
}