package pl.javastart.tasks.entity;

public enum Category {
    HOMEWORK("Zadanie domowe"),
    WORK("Praca"),
    COURSE("Kurs"),
    SPORT("Trening");

    private String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
