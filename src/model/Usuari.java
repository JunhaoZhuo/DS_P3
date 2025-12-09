package model;

import model.excepcions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Usuari {
    // ara fem servir composició amb PerfilUsuari
    private final PerfilUsuari perfil;

    private List<Adquisicio> adquisicions;

    public Usuari(
            String email,
            String contrasenya,
            String nomUsuari,
            LocalDate dataNaixement,
            LocalDate dataRegistre
    ) {
        // Creem el perfil de l'usuari
        this.perfil = new PerfilUsuari(email, contrasenya, nomUsuari, dataNaixement, dataRegistre);

        this.adquisicions = new ArrayList<>();
    }

    public String getEmail() {
        return perfil.getEmail();
    }

    public String getContrasenya() {
        return perfil.getContrasenya();
    }
    public String getNomUsuari() {
        return perfil.getNomUsuari();
    }

   // Mètode per comprovar la contrasenya desde perfil
    public boolean comprovarContrasenya(String contrasenya) {
        return perfil.getContrasenya().equals(contrasenya);
    }

    // mètodes antics de usuari per gestionar adquisicions
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
