package main.java.fr.ynov.wikikonmenkonfe.gui.panels;

import main.java.fr.ynov.wikikonmenkonfe.domain.Admin;
import main.java.fr.ynov.wikikonmenkonfe.domain.Moderator;
import main.java.fr.ynov.wikikonmenkonfe.domain.User;
import main.java.fr.ynov.wikikonmenkonfe.domain.Writer;
import main.java.fr.ynov.wikikonmenkonfe.gui.WikiGUI;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends JPanel {
    private final WikiGUI mainFrame;
    private final JLabel nameLabel;
    private final JLabel roleLabel;
    private User currentUser;

    public UserPanel(WikiGUI mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));

        nameLabel = new JLabel("Name: Not logged in");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        roleLabel = new JLabel("Role: None");
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        roleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        userInfoPanel.add(Box.createVerticalGlue());
        userInfoPanel.add(nameLabel);
        userInfoPanel.add(Box.createVerticalStrut(10));
        userInfoPanel.add(roleLabel);
        userInfoPanel.add(Box.createVerticalGlue());

        JButton backButton = new JButton("Back to Search");
        backButton.addActionListener(e -> mainFrame.navigateTo(WikiGUI.WELCOME_PANEL));

        add(userInfoPanel, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }

    public void setUser(User user) {
        this.currentUser = user;
        nameLabel.setText("Name: " + user.getName());

        String role;
        switch (user) {
            case Admin admin -> role = "Admin";
            case Moderator moderator -> role = "Moderator";
            case Writer writer -> role = "Writer";
            default -> {
                role = "Reader";
            }
        }

        roleLabel.setText("Role: " + role);
    }
}