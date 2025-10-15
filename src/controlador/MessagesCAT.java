package controlador;

// TO DO moure a controller + crear classes per exceptions
public enum MessagesCAT {

    // Exception messages
    EmptyEmailException("L'e-mail no pot ser buit"),
    BadlyFormattedEmailException("L'e-mail no té un format vàlid"),
    EmptyPasswordException("La contrasenya no pot ser buida"),
    PasswordHasLessThan8CharactersException("La contrasenya he de tenir 8 caràcters o més"),
    PasswordNeedsAtLeastOneLetterException("La contrasenya ha de tenir almenys una lletra"),
    PasswordNeedsAtLeastOneNumberException("La contrasenya he de tenir almenys un nombre"),
    PasswordNeedsAtLeastOneSymbolException("La contrasenya he de tenir almenys un símbol"),
    EmptyUsernameException("El nom d'usuari no pot ser buit"),
    EmptyBirthdateException("La data de naixement no pot ser buida"),
    InvalidBirthdateFormatException("La data de naixement ha de tenir el format dd-MM-yyyy"),
    EmailAlreadyRegisteredException("Ja existeix un usuari amb aquest e-mail"),
    UsernameAlreadyRegisteredException("Ja existeix un usuari amb aquest nom d'usuari"),
    EmailNotRegisteredException("No existeix cap usuari amb aquest e-mail"),
    IncorrectPasswordException("La contrasenya no és correcta"),
    EmptyCatalegException("No hi ha jocs disponibles al catàleg en aquest moment"),
    JocNotFoundException("No s'ha trobat cap joc amb aquest títol"),
    UsuariSenseAdquisicionsException("L'usuari no ha adquirit cap joc"),
    SuccessfulUserRegistration("Usuari registrat correctament"),
    SuccessfulLogin("Sessió iniciada correctament"),
    ;


    private final String message;

    MessagesCAT(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static String translate(Exception e) {
        // Depending on the type of the Exception we will return the corresponding message
        String exceptionName = e.getClass().getSimpleName();  // Obté el nom de la classe de l'excepció
        return valueOf(exceptionName).getMessage();
    }
}
