package model;

import java.time.LocalDate;

public class PerfilUsuari {
    private final String email;
    private final String contrasenya;
    private final String nomUsuari;
    private final LocalDate dataNaixement;
    private final LocalDate dataRegistre;

    public PerfilUsuari(String email, String contrasenya, String nomUsuari, LocalDate dataNaixement, LocalDate dataRegistre) {
        this.email = email;
        this.contrasenya = contrasenya;
        this.nomUsuari = nomUsuari;
        this.dataNaixement = dataNaixement;
        this.dataRegistre = dataRegistre;
    }

    public String getEmail() {
        return email;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public String getNomUsuari() {
        return nomUsuari;
    }

    public LocalDate getDataNaixement() {
        return dataNaixement;
    }

    public LocalDate getDataRegistre() {
        return dataRegistre;
    }
}