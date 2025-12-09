package model;

import java.time.LocalDate;


public class Comentari extends Valoracio {
    private final String text;
    private final LocalDate data;
    // nou atribut per US12
    private final String autor;

    public Comentari(String text, LocalDate data, String autor) {
        this.text = text;
        this.data = data;
        // inicialitzem el nou atribut
        this.autor = autor;
    }

    // implementacions dels mètodes abstractes
    @Override
    public String getTipus() {
        return "Comentari";
    }

    @Override
    public String getResum() {
        return text + " (" + data.toString() + ") - per " + autor;
    }
}