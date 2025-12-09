package model;

import model.excepcions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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


    public void registrarUsuari(String email, String contrasenya, String nomUsuari, String dataNaixement) throws Exception {
        // 1. Deleguem la validació de format a l'Autenticador
        Autenticador autenticador = new Autenticador();
        LocalDate dataNaixementAux = autenticador.validarDadesRegistre(email, contrasenya, nomUsuari, dataNaixement);

        // Comprovacions de negoci (existència) es queden a CarteraUsuaris
        comprovarEmailEstaDisponible(email);
        comprovarNomUsuariEstaDisponible(nomUsuari);


        Usuari nouUsuari = new Usuari(
                email,
                contrasenya,
                nomUsuari,
                dataNaixementAux,
                LocalDate.now()
        );

        this.afegirUsuari(nouUsuari);
    }

    // Mètodes privats moguts des del Controlador (Expert en Informació) / alta cohesió)
    /*
     * Metodes privats de comprovació i processament.
     */

    private void comprovarEmailEstaDisponible(String email) throws Exception {
        Usuari usuari = findByEmail(email);
        if (usuari != null) {
            throw new EmailAlreadyRegisteredException();
        }
    }

    private void comprovarNomUsuariEstaDisponible(String nomUsuari) throws Exception {
        Usuari usuari = findByNomUsuari(nomUsuari);
        if (usuari != null) {
            throw new UsernameAlreadyRegisteredException();
        }
    }

    // Metode que ja estava en el codi base: afegir usuari
    public void afegirUsuari(Usuari nouUsuari) {
        usuaris.add(nouUsuari);
    }


}
