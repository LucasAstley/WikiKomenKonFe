package main.java.fr.ynov.wikikonmenkonfe.main;

import main.java.fr.ynov.wikikonmenkonfe.domain.Wiki;
import main.java.fr.ynov.wikikonmenkonfe.gui.WikiGUI;

/**
 * Main class of the application
 */
public class ApplicationRunner {


    public static void main(String[] args) {
        Wiki wiki = new Wiki("WikiKomenKonFé");

        WikiGUI gui = new WikiGUI(wiki);
        gui.showGUI();
    }
}