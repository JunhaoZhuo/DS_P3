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
            String dataNaixement,
            LocalDate dataRegistre
    ) throws Exception {
        // Comprovacions i processaments
        comprovarCorreuEsValid(email);
        comprovarContrasenyaEsValida(contrasenya);
        comprovarNomUsuariEsValid(nomUsuari);
        LocalDate dataNaixementProcessada = comprovarIProcessarData(dataNaixement);

        // Assignacions
        this.email = email;
        this.contrasenya = contrasenya;
        this.nomUsuari = nomUsuari;
        this.dataNaixement = dataNaixementProcessada;
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
    /*
     * Metodes privats de comprovació i processament
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


}
