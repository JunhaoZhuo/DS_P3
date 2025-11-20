package model;

import java.time.LocalDate;


public class Comentari extends Valoracio {
    private final String text;
    private final LocalDate data;

    public Comentari(String text, LocalDate data) {
        this.text = text;
        this.data = data;
    }

    // implementacions dels mètodes abstractes
    @Override
    public String getTipus() {
        return "Comentari";
    }

    @Override
    public String getResum() {
        return text + " (" + data.toString() + ")";
    }
}