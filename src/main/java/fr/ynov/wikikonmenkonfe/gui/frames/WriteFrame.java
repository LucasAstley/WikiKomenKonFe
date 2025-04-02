package main.java.fr.ynov.wikikonmenkonfe.gui.frames;

import main.java.fr.ynov.wikikonmenkonfe.domain.Admin;
import main.java.fr.ynov.wikikonmenkonfe.domain.Article;
import main.java.fr.ynov.wikikonmenkonfe.domain.Category;
import main.java.fr.ynov.wikikonmenkonfe.domain.User;
import main.java.fr.ynov.wikikonmenkonfe.domain.Writer;
import main.java.fr.ynov.wikikonmenkonfe.factory.ArticleFactory;
import main.java.fr.ynov.wikikonmenkonfe.gui.WikiGUI;
import main.java.fr.ynov.wikikonmenkonfe.gui.buttons.CustomButtons;

import javax.swing.*;
import java.awt.*;

public class WriteFrame extends JFrame {
    private final JTextField titleField;
    private final JTextArea contentArea;
    private final JComboBox<Object> categoryCombo;
    private final Article existingArticle;
    private final WikiGUI wikiGUI;

    /**
     * Constructor for creating a new article or editing an existing one
     *
     * @param mainFrame       the main frame of the application
     * @param wikiGUI         the WikiGUI instance
     * @param existingArticle the existing article to edit, or null if creating a new one
     */
    public WriteFrame(MainFrame mainFrame, WikiGUI wikiGUI, Article existingArticle) {
        this.wikiGUI = wikiGUI;
        this.existingArticle = existingArticle;
        boolean isEditMode = existingArticle != null;

        setTitle(isEditMode ? "Edit article" : "Write new article");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        formPanel.add(titleLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        formPanel.add(titleField, gbc);

        JLabel categoryLabel = new JLabel("Category:");
        Object[] categoryOptions = new Object[Category.values().length + 1];
        categoryOptions[0] = "Select category...";
        System.arraycopy(Category.values(), 0, categoryOptions, 1, Category.values().length);

        categoryCombo = new JComboBox<>(categoryOptions);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        formPanel.add(categoryLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        formPanel.add(categoryCombo, gbc);

        JLabel contentLabel = new JLabel("Content:");
        contentArea = new JTextArea(15, 20);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(contentArea);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        formPanel.add(contentLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        formPanel.add(scrollPane, gbc);

        if (isEditMode) {
            titleField.setText(existingArticle.getTitle());
            contentArea.setText(existingArticle.getContent());

            Category existingCategory = existingArticle.getCategory();
            if (existingCategory != null) {
                for (int i = 1; i < categoryOptions.length; i++) {
                    if (categoryOptions[i].equals(existingCategory)) {
                        categoryCombo.setSelectedIndex(i);
                        break;
                    }
                }
            }
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton cancelButton = CustomButtons.createSecondaryButton("Cancel");
        JButton saveButton = CustomButtons.createPrimaryButton(isEditMode ? "Update" : "Save");

        cancelButton.addActionListener(e -> dispose());
        saveButton.addActionListener(e -> saveArticle());

        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setLocationRelativeTo(null);
    }

    /**
     * Method to save the article in the wiki
     */
    private void saveArticle() {
        String title = titleField.getText().trim();
        String content = contentArea.getText().trim();
        Object selectedCategory = categoryCombo.getSelectedItem();

        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a title", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter content", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (selectedCategory == null || "Select category...".equals(selectedCategory.toString())) {
            JOptionPane.showMessageDialog(this, "Please select a category", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Category category = (Category) selectedCategory;
        User currentUser = wikiGUI.getCurrentUser();

        if (existingArticle != null) {
            if (currentUser instanceof Admin) {
                existingArticle.setTitle(title);
                existingArticle.setContent(content);
                existingArticle.setCategory(category);
                wikiGUI.getWiki().updateArticle(existingArticle);
                JOptionPane.showMessageDialog(this, "Article updated successfully!");
                wikiGUI.displayArticle(existingArticle);
                dispose();
            }
        } else {
            if (currentUser instanceof Admin || currentUser instanceof Writer) {
                wikiGUI.getWiki().addArticle(ArticleFactory.createArticle(title, content, currentUser, category));
                JOptionPane.showMessageDialog(this, "Article saved successfully!");
                wikiGUI.updateArticlesList();
                dispose();
            }
        }
    }
}