package main.java.fr.ynov.wikikonmenkonfe.gui.frames;

import main.java.fr.ynov.wikikonmenkonfe.domain.User;
import main.java.fr.ynov.wikikonmenkonfe.domain.Wiki;
import main.java.fr.ynov.wikikonmenkonfe.gui.buttons.CustomButtons;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JDialog {
    private JTextField usernameField;
    private JComboBox<String> roleComboBox;
    private User loggedInUser;
    private Wiki wiki;

    /**
     * The login popup to create a new user
     *
     * @param parent the parent frame
     * @param wiki   the wiki instance
     */
    public LoginFrame(Frame parent, Wiki wiki) {
        super(parent, "Login", true);
        this.wiki = wiki;
        setSize(300, 150);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Role:"));
        String[] roles = {"READER", "WRITER", "MODERATOR", "ADMIN"};
        roleComboBox = new JComboBox<>(roles);
        panel.add(roleComboBox);

        JButton loginButton = CustomButtons.createPrimaryButton("Login");
        loginButton.addActionListener(e -> login());
        JButton cancelButton = CustomButtons.createSecondaryButton("Cancel");
        cancelButton.addActionListener(e -> dispose());

        panel.add(loginButton);
        panel.add(cancelButton);

        add(panel);
    }

    /**
     * Displays the login dialog and waits for user input
     */
    private void login() {
        String username = usernameField.getText().trim();
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a username");
            return;
        }

        String roleString = (String) roleComboBox.getSelectedItem();
        Wiki.UserType role = Wiki.UserType.valueOf(roleString);

        loggedInUser = wiki.createUser(username, role);
        dispose();
    }

    /**
     * Returns the actual logged-in user
     *
     * @return the actual logged-in user
     */
    public User getLoggedInUser() {
        return loggedInUser;
    }
}