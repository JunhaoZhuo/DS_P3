package model;

import model.excepcions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CarteraUsuaris {
    private final List<Usuari> usuaris;

    public CarteraUsuaris() {
        this.usuaris = new ArrayList<>();
    }

    public void setUsuaris(List<Usuari> usuaris) {
        this.usuaris.clear();
        this.usuaris.addAll(usuaris);
    }

    public Usuari findByEmail(String email) throws Exception {
        for (Usuari usuari : usuaris) {
            if (usuari.getEmail().equals(email)) {
                return usuari;
            }
        }
        return null;
    }

    public Usuari findByNomUsuari(String nomUsuari) {
        for (Usuari usuari : usuaris) {
            if (usuari.getNomUsuari().equals(nomUsuari)) {
                return usuari;
            }
        }
        return null;
    }

    public List<Adquisicio> getAdquisicionsDeUsuariOrdenadesPerNom(String email) throws Exception {
        Usuari usuari = findByEmail(email);
        if (usuari == null) {
            throw new EmailNotRegisteredException();
        }

        List<Adquisicio> adquisicions = usuari.getAdquisicions();
        if (adquisicions.isEmpty()) {
            throw new UsuariSenseAdquisicionsException();
        }

        return usuari.getAdquisicions()
                .stream()
                .sorted((a1, a2) -> a1.getJoc().getTitol().compareToIgnoreCase(a2.getJoc().getTitol()))
                .toList();
    }

    public void afegirUsuari(Usuari nouUsuari) {
        usuaris.add(nouUsuari);
    }
}
