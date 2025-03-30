package main.java.fr.ynov.wikikonmenkonfe.gui.buttons;

import javax.swing.*;
import java.awt.*;

public class CustomButtons extends JButton {
    public static final Color PRIMARY = new Color(0x93B874);
    public static final Color SECONDARY = new Color(0xE6EEE0);

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
