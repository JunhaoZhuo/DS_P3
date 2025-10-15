package model;

public class EspecAssolimentJoc {
    private final String titol;
    private final String titolJoc;

    public EspecAssolimentJoc(String titol, String titolJoc, Double percentatgeUsuarisCompletat) {
        this.titol = titol;
        this.titolJoc = titolJoc;
    }

    public String getTitol() {
        return titol;
    }

    public String getTitolJoc() {
        return titolJoc;
    }
}
