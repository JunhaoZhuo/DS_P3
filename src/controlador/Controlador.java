package controlador;

import controlador.interfaces.IDataService;
import model.*;
import model.excepcions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import vista.PreparadorVista;


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

            comprovarEmailEstaDisponible(email);
            comprovarNomUsuariEstaDisponible(nomUsuari);

            Usuari nouUsuari = new Usuari(
                    email,
                    contrasenya,
                    nomUsuari,
                    dataNaixement,
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

            if (!usuari.comprovarContrasenya(contrasenya)) {
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
            return PreparadorVista.prepararLlistaJocsCataleg(jocs);
        } catch (Exception e) {
            return MessagesCAT.translate(e);
        }
    }

    public String visualitzarLlistaJocsAdquiritsPerUsuari(String email) {
        try {
            List<Adquisicio> adquisicions = carteraUsuaris.getAdquisicionsDeUsuariOrdenadesPerNom(email);
            // Delegació de la preparació (aplicant GRASP)
            return PreparadorVista.prepararLlistaAdquisicions(adquisicions);
        } catch (Exception e) {
            return MessagesCAT.translate(e);
        }
    }

    public String veureDetallsJoc(String titol) {
        try {
            Joc joc = catalegJocs.findByTitol(titol);
            return PreparadorVista.prepararDetallsJoc(joc);
        } catch (Exception e) {
            return MessagesCAT.translate(e);
        }
    }

    /*
     * Metodes privats de comprovació i processament.
     */


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