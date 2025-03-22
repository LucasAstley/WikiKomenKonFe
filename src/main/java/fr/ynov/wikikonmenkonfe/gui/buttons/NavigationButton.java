package main.java.fr.ynov.wikikonmenkonfe.gui.buttons;

import main.java.fr.ynov.wikikonmenkonfe.gui.WikiGUI;

import javax.swing.*;

public class NavigationButton extends JButton {

    public NavigationButton(String text, String destination, WikiGUI mainFrame) {
        super(text);
        addActionListener(e -> mainFrame.navigateTo(destination));
    }

    public static NavigationButton createBackButton(WikiGUI mainFrame) {
        return new NavigationButton("Back to Search", WikiGUI.WELCOME_PANEL, mainFrame);
    }

    public static NavigationButton createProfileButton(WikiGUI mainFrame) {
        return new NavigationButton("My Profile", WikiGUI.USER_PANEL, mainFrame);
    }
}