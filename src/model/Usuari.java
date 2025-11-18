package model;

import model.excepcions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Usuari {
    private final String email;
    private final String contrasenya;
    private final String nomUsuari;
    private final LocalDate dataNaixement;
    private final LocalDate dataRegistre;

    private List<Adquisicio> adquisicions;

    public Usuari(
            String email,
            String contrasenya,
            String nomUsuari,
            LocalDate dataNaixement,
            LocalDate dataRegistre
    ) {
        this.email = email;
        this.contrasenya = contrasenya;
        this.nomUsuari = nomUsuari;
        this.dataNaixement = dataNaixement;
        this.dataRegistre = dataRegistre;

        this.adquisicions = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public Object getContrasenya() {
        return contrasenya;
    }
    public String getNomUsuari() {
        return nomUsuari;
    }

    /*
    CANVIS US2
     */
    public boolean comprovarContrasenya(String contrasenya) {
        return this.contrasenya.equals(contrasenya);
    }


    public List<Adquisicio> getAdquisicions() {
        return adquisicions;
    }

    public void addAdquisicio(Adquisicio adquisicio) {
        if (adquisicions == null) {
            adquisicions = new ArrayList<>();
        }
        adquisicions.add(adquisicio);
    }

    public Adquisicio findByTitolJocIDataAdquisicio(String titolJoc, LocalDate dataAdquisicio) {
        if (adquisicions != null) {
            for (Adquisicio adquisicio : adquisicions) {
                if (adquisicio.getJoc().getTitol().equals(titolJoc)
                        && adquisicio.getData().equals(dataAdquisicio)) {
                    return adquisicio;
                }
            }
        }
        return null;
    }

    // Util pel US7, pel comprovar si un usuari ja té adquirit un joc
    public boolean teJoc(Joc joc) {
        if (adquisicions == null) return false;
        for (Adquisicio adq : adquisicions) {
            if (adq.getJoc().equals(joc)) {
                return true;
            }
        }
        return false;
    }

    // Util pel US8, trobar adquisició per títol de joc
    public Adquisicio findAdquisicioByTitol(String titolJoc) {
        if (adquisicions != null) {
            for (Adquisicio adquisicio : adquisicions) {
                if (adquisicio.getJoc().getTitol().equals(titolJoc)) {
                    return adquisicio;
                }
            }
        }
        return null;
    }
}
