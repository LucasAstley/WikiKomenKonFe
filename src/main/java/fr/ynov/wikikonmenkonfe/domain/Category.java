package main.java.fr.ynov.wikikonmenkonfe.domain;

public enum Category {

    /**
     * The different categories of the wiki articles
     */
    CULTURE("Culture"),
    ECONOMY("Economy"),
    EDUCATION("Education"),
    ENVIRONMENT("Environment"),
    HEALTH("Health"),
    HISTORY("History"),
    LITERATURE("Literature"),
    POLITICS("Politics"),
    SCIENCE("Science"),
    SOCIETY("Society"),
    SPORT("Sport"),
    TECHNOLOGY("Technology");

    private String category;

    Category(String categoryName) {
        this.category = categoryName;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String category) {
        this.category = category;
    }
}
