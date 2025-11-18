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

    // Nova logica refactoritzada (creador + expert)
    public void registrarUsuari(String email, String contrasenya, String nomUsuari, String dataNaixement) throws Exception {
        comprovarCorreuEsValid(email);
        comprovarContrasenyaEsValida(contrasenya);
        comprovarNomUsuariEsValid(nomUsuari);
        LocalDate dataNaixementAux = comprovarIProcessarData(dataNaixement);

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
    private void comprovarCorreuEsValid(String email) throws Exception {
        if (email == null || email.isEmpty()) {
            throw new EmptyEmailException();
        } else if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+\\.[A-Za-z]{2,}$")) {
            throw new BadlyFormattedEmailException();
        }
    }

    private void comprovarContrasenyaEsValida(String contrasenya) throws Exception {
        if (contrasenya == null || contrasenya.isEmpty()) {
            throw new EmptyPasswordException();
        } else if (contrasenya.length() < 8) {
            throw new PasswordHasLessThan8CharactersException();
        } else if (!contrasenya.matches(".*[a-zA-Z].*")) {
            throw new PasswordNeedsAtLeastOneLetterException();
        } else if (!contrasenya.matches(".*\\d.*")) {
            throw new PasswordNeedsAtLeastOneNumberException();
        } else if (!contrasenya.matches(".*[^A-Za-z0-9].*")) { // Qualsevol caràcter que no sigui lletra o nombre
            throw new PasswordNeedsAtLeastOneSymbolException();
        }
    }

    private void comprovarNomUsuariEsValid(String nomUsuari) throws Exception {
        if (nomUsuari == null || nomUsuari.isEmpty()) {
            throw new EmptyUsernameException();
        }
    }

    private LocalDate comprovarIProcessarData(String dataNaixement) throws Exception {
        if (dataNaixement == null || dataNaixement.isEmpty()) {
            throw new EmptyBirthdateException();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
        try {
            return LocalDate.parse(dataNaixement, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidBirthdateFormatException();
        }
    }


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
