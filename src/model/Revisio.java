package model;

public class Revisio extends Valoracio {
    private final int jugabilitat;
    private final int grafics;
    private final int historia;
    private final int musica;

    public Revisio(int jugabilitat, int grafics, int historia, int musica) {
        this.jugabilitat = jugabilitat;
        this.grafics = grafics;
        this.historia = historia;
        this.musica = musica;
    }

    // Podem afegir un mètode per calcular la mitjana si fos necessari
    public double getValoracioMitjana() {
        return (jugabilitat + grafics + historia + musica) / 4.0;
    }
    @Override
    public String getTipus() {
        return "Revisió";
    }

    @Override
    public String getResum() {
        return "Nota mitjana: " + getValoracioMitjana();
    }
}