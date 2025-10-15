package controlador;

import controlador.interfaces.IDataService;
import model.*;
import model.excepcions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Controlador {
    private final IDataService dataService;
    private CarteraUsuaris carteraUsuaris;
    private CatalegJocs catalegJocs;

    public Controlador(IDataService dataService) {
        this.dataService = dataService;
        this.carteraUsuaris = new CarteraUsuaris();
        this.catalegJocs = new CatalegJocs();
    }

    public void loadDataFromResources() {
        dataService.loadDataInto(carteraUsuaris, catalegJocs);
    }

    public void loadUsuariDataFromResources() {
        dataService.loadUsuarisInto(carteraUsuaris);
    }

    public void loadJocDataFromResources() {
        dataService.loadJocsInto(catalegJocs);
    }

    public String registrarUsuari(
            String email,
            String contrasenya,
            String nomUsuari,
            String dataNaixement
    ) {
        try {
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

            carteraUsuaris.afegirUsuari(nouUsuari);
            return MessagesCAT.SuccessfulUserRegistration.getMessage();
        } catch (Exception e) {
            return MessagesCAT.translate(e);
        }
    }

    public String loguejarUsuari(String email, String contrasenya) {
        try {
            if (email == null || email.isEmpty()) {
                throw new EmptyEmailException();
            }
            if (contrasenya == null || contrasenya.isEmpty()) {
                throw new EmptyPasswordException();
            }

            Usuari usuari = carteraUsuaris.findByEmail(email);
            if (usuari == null) {
                throw new EmailNotRegisteredException();
            }

            if (!usuari.getContrasenya().equals(contrasenya)) {
                throw new IncorrectPasswordException();
            }
            return MessagesCAT.SuccessfulLogin.getMessage();
        } catch (Exception e) {
            return MessagesCAT.translate(e);
        }
    }

    public String visualitzarLlistaJocsCataleg() {
        try {
            List<Joc> jocs = catalegJocs.getJocsOrdenatsPerNom();
            List<String> titols = jocs.stream()
                    .map(Joc::getTitol)
                    .collect(Collectors.toList());
            return "Llista de jocs del catàleg:\n" + String.join("\n", titols);
        } catch (Exception e) {
            return MessagesCAT.translate(e);
        }
    }

    public String visualitzarLlistaJocsAdquiritsPerUsuari(String email) {
        try {
            List<Adquisicio> adquisicions = carteraUsuaris.getAdquisicionsDeUsuariOrdenadesPerNom(email);
            List<String> titols = adquisicions.stream()
                    .map(Adquisicio::getTitolJoc)
                    .collect(Collectors.toList());
            return "Llista de jocs adquirits per l'usuari:\n" + String.join("\n", titols);
        } catch (Exception e) {
            return MessagesCAT.translate(e);
        }
    }

    public String veureDetallsJoc(String titol) {
        try {
            Joc joc = catalegJocs.findByTitol(titol);
            String titolFormatat = String.format("\"%s\"", joc.getTitol());
            String generesFormatat = String.join(", ", joc.getGeneres());
            String desenvolupadoresFormatat = String.join(", ", joc.getDesenvolupadores());
            String distribuidoresFormatat = String.join(", ", joc.getDistribuidores());
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String dataAnunci = joc.getDataAnunci() != null ? joc.getDataAnunci().format(pattern) : "N/A";
            String dataLlancament = joc.getDataLlancament() != null ? joc.getDataLlancament().format(pattern) : "N/A";
            String dataRetirada = joc.getDataRetirada() != null ? joc.getDataRetirada().format(pattern) : "N/A";

            StringBuilder details = new StringBuilder();
            details.append("Títol: ").append(titolFormatat).append("\n")
                    .append("Gènere(s): ").append(generesFormatat).append("\n")
                    .append("Desenvolupadora(es): ").append(desenvolupadoresFormatat).append("\n")
                    .append("Distribuïdora(es): ").append(distribuidoresFormatat).append("\n")
                    .append("Data d'anunci: ").append(dataAnunci).append("\n")
                    .append("Data de llançament: ").append(dataLlancament).append("\n")
                    .append("Data de retirada: ").append(dataRetirada).append("\n")
                    .append("Estat: ").append(joc.getEstat());
            return details.toString();
        } catch (Exception e) {
            return MessagesCAT.translate(e);
        }
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

    private void comprovarEmailEstaDisponible(String email) throws Exception {
        Usuari usuari = carteraUsuaris.findByEmail(email);
        if (usuari != null) {
            throw new EmailAlreadyRegisteredException();
        }
    }

    private void comprovarNomUsuariEstaDisponible(String nomUsuari) throws Exception {
        Usuari usuari = carteraUsuaris.findByNomUsuari(nomUsuari);
        if (usuari != null) {
            throw new UsernameAlreadyRegisteredException();
        }
    }
}