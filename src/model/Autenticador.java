package model;

import model.excepcions.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Autenticador {

    public LocalDate validarDadesRegistre(String email, String contrasenya, String nomUsuari, String dataNaixement) throws Exception {
        comprovarCorreuEsValid(email);
        comprovarContrasenyaEsValida(contrasenya);
        if (nomUsuari == null || nomUsuari.isEmpty()) throw new EmptyUsernameException();
        return comprovarIProcessarData(dataNaixement);
    }

    public void validarCredencialsLogin(String email, String contrasenya) throws Exception {
        if (email == null || email.isEmpty()) throw new EmptyEmailException();
        if (contrasenya == null || contrasenya.isEmpty()) throw new EmptyPasswordException();
    }

    public void verificarContrasenya(Usuari usuari, String contrasenya) throws IncorrectPasswordException {
        // Nota: Assumim que l'usuari no és null (això ho valida qui crida el mètode)
        if (!usuari.comprovarContrasenya(contrasenya)) {
            throw new IncorrectPasswordException();
        }
    }

    // --- Mètodes privats moguts des de CarteraUsuaris ---

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
        } else if (!contrasenya.matches(".*[^A-Za-z0-9].*")) {
            throw new PasswordNeedsAtLeastOneSymbolException();
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