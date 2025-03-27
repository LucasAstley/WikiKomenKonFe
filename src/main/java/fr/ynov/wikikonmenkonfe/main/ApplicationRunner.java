package main.java.fr.ynov.wikikonmenkonfe.main;

import main.java.fr.ynov.wikikonmenkonfe.domain.Article;
import main.java.fr.ynov.wikikonmenkonfe.domain.User;
import main.java.fr.ynov.wikikonmenkonfe.factory.UserFactory;
import main.java.fr.ynov.wikikonmenkonfe.factory.ArticleFactory;
import main.java.fr.ynov.wikikonmenkonfe.domain.Wiki;
import main.java.fr.ynov.wikikonmenkonfe.gui.WikiGUI;
import main.java.fr.ynov.wikikonmenkonfe.domain.Category;

/**
 * Main class of the application
 */
public class ApplicationRunner {


    public static void main(String[] args) {
        Wiki wiki = new Wiki("WikiKomenKonFé");

        User writer = new UserFactory().createWriter("John Doe");
        wiki.addUser(writer);

        ArticleFactory.addArticleToWiki(wiki, ArticleFactory.createArticle("KomenKonFé pour hacker le FBI", "1. Ouvrir un terminal et taper 'sudo hack FBI' \n2. Se rendre compte que ça marche pas \n3. Se remettre en question", writer, Category.ECONOMY));

        ArticleFactory.addArticleToWiki(wiki, ArticleFactory.createArticle("KomenKonFé pour devenir riche en 24h", "1. Miner du Bitcoin sur ton PC de 2009 \n2. Regarder ton CPU prendre feu \n3. Lancer un crowdfunding pour racheter un PC", writer, Category.ECONOMY));

        ArticleFactory.addArticleToWiki(wiki, ArticleFactory.createArticle("KomenKonFé pour courir plus vite", "1. Installer un SSD dans tes chaussures \n2. Compiler un programme de sprint en Java \n3. Attendre 3 secondes que la JVM démarre avant de partir", writer, Category.SPORT));

        ArticleFactory.addArticleToWiki(wiki, ArticleFactory.createArticle("KomenKonFé pour devenir un génie", "1. Dire 'c'est une question d’algorithmique' à chaque débat \n2. Boire du café jusqu’à voir le code source de la Matrice \n3. Répondre '42' à toutes les questions", writer, Category.EDUCATION));

        ArticleFactory.addArticleToWiki(wiki, ArticleFactory.createArticle("KomenKonFé pour sauver la planète", "1. Ne plus imprimer ses mails (c’est un crime) \n2. Compiler en C plutôt qu’en Java pour économiser du CPU \n3. Convaincre Elon Musk d’aller sur Mars et d’y rester", writer, Category.ENVIRONMENT));

        ArticleFactory.addArticleToWiki(wiki, ArticleFactory.createArticle("KomenKonFé pour éviter les bugs en Java", "1. Ne jamais coder fatigué \n2. Ne jamais coder reposé non plus, sinon on se rend compte du code qu’on a écrit hier \n3. Utiliser Python à la place (traître)", writer, Category.TECHNOLOGY));

        ArticleFactory.addArticleToWiki(wiki, ArticleFactory.createArticle("KomenKonFé pour impressionner son prof de Java", "1. Dire 'J’utilise les design patterns' même si c’est faux \n2. Remplacer toutes les variables par des noms en latin \n3. Rajouter des 'synchronized' partout pour avoir l’air intelligent", writer, Category.EDUCATION));

        ArticleFactory.addArticleToWiki(wiki, ArticleFactory.createArticle("KomenKonFé pour coder sans bug", "1. Rêver \n2. Se réveiller \n3. Accepter que c’est impossible", writer, Category.TECHNOLOGY));

        ArticleFactory.addArticleToWiki(wiki, ArticleFactory.createArticle("KomenKonFé pour coder un site web en Java", "1. Se poser la question 'Pourquoi ?' \n2. Réaliser qu’on aurait dû utiliser JavaScript \n3. Pleurer en configurant Spring Boot", writer, Category.TECHNOLOGY));

        WikiGUI gui = new WikiGUI(wiki);
        gui.showGUI();
    }
}