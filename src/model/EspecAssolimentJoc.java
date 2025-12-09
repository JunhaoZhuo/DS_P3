package model;

public class EspecAssolimentJoc {
    private final String titol;
    // Eliminem titolJoc
    private final Double percentatgeUsuarisCompletat;

    public EspecAssolimentJoc(String titol, Double percentatgeUsuarisCompletat) {
        this.titol = titol;
        this.percentatgeUsuarisCompletat = percentatgeUsuarisCompletat;
    }

    public String getTitol() {
        return titol;
    }

    public Double getPercentatgeUsuarisCompletat() {
        return percentatgeUsuarisCompletat;
    }
}
