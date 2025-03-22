package main.java.fr.ynov.wikikonmenkonfe.gui.buttons;

import main.java.fr.ynov.wikikonmenkonfe.gui.WikiGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionButton extends JButton {

    public ActionButton(String text, ActionListener action) {
        super(text);
        addActionListener(action);
    }

    public static ActionButton createSearchButton(JTextField searchField, WikiGUI mainFrame) {
        return new ActionButton("Search", e -> {
            String query = searchField.getText().trim();
            if (!query.isEmpty()) {
                mainFrame.performSearch(query);
            }
        });
    }

    public static ActionButton createLoginButton(WikiGUI mainFrame) {
        return new ActionButton("Login", e -> mainFrame.showLoginDialog());
    }
}