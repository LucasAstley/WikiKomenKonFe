package main.java.fr.ynov.wikikonmenkonfe.gui.buttons;

import javax.swing.*;
import java.awt.*;

/**
 * CustomButtons class provides methods to create styled buttons for the application
 * It includes primary and secondary button styles with specific colors and borders
 */
public class CustomButtons extends JButton {
    public static final Color PRIMARY = new Color(0x93B874);
    public static final Color SECONDARY = new Color(0xE6EEE0);

    /**
     * Create a primary button with a specific text
     *
     * @param text the text to display on the button
     * @return a JButton styled as a primary button
     */
    public static JButton createPrimaryButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(PRIMARY);
        button.setForeground(java.awt.Color.BLACK);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new java.awt.Color(110, 138, 87), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        button.setFocusPainted(false);
        return button;
    }


    /**
     * Create a secondary button with a specific text
     *
     * @param text the text to display on the button
     * @return a JButton styled as a secondary button
     */
    public static JButton createSecondaryButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(SECONDARY);
        button.setForeground(java.awt.Color.BLACK);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new java.awt.Color(200, 210, 190), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        button.setFocusPainted(false);
        return button;
    }
}
