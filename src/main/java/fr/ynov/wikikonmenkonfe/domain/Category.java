package main.java.fr.ynov.wikikonmenkonfe.domain;

public enum Category {

    POLITICS("Politics"),
    SPORT("Sport"),
    SCIENCE("Science"),
    CULTURE("Culture"),
    ECONOMY("Economy"),
    TECHNOLOGY("Technology"),
    HEALTH("Health"),
    EDUCATION("Education"),
    ENVIRONMENT("Environment"),
    SOCIETY("Society"),
    HISTORY("History"),
    LITERATURE("Literature");

    private String category;

    private Category(String categoryName) {
        this.category = categoryName;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String category) {
        this.category = category;
    }
}
