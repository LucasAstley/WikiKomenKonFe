package main.java.fr.ynov.wikikonmenkonfe.gui.panels;

import main.java.fr.ynov.wikikonmenkonfe.domain.*;
import main.java.fr.ynov.wikikonmenkonfe.gui.WikiGUI;
import main.java.fr.ynov.wikikonmenkonfe.gui.buttons.CustomButtons;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends JPanel {
    private final WikiGUI mainFrame;
    private final JLabel nameLabel;
    private final JLabel roleLabel;
    private User currentUser;

    /**
     * Constructor for UserPanel
     *
     * @param mainFrame The main frame of the application
     */
    public UserPanel(WikiGUI mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));

        nameLabel = new JLabel();
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        roleLabel = new JLabel();
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        roleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        userInfoPanel.add(Box.createVerticalGlue());
        userInfoPanel.add(nameLabel);
        userInfoPanel.add(Box.createVerticalStrut(10));
        userInfoPanel.add(roleLabel);
        userInfoPanel.add(Box.createVerticalGlue());

        JButton backButton = CustomButtons.createPrimaryButton("Back to Search");
        backButton.addActionListener(e -> mainFrame.navigateTo(WikiGUI.WELCOME_PANEL));

        add(userInfoPanel, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }

    /**
     * Update the user information displayed in the panel
     *
     * @param user The user object containing the information to display
     */
    public void setUser(User user) {
        this.currentUser = user;
        nameLabel.setText("Name: " + user.getName());
        roleLabel.setText("Role: " + user.getRole());
    }
}