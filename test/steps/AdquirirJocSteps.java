package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import model.EstatJoc;
import model.Joc;
import resources.WorldState;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class AdquirirJocSteps {

    private final WorldState state;

    // Dependència del WorldState per accedir al controlador
    public AdquirirJocSteps(WorldState state) {
        this.state = state;
    }

    @Given("un usuari s'ha registrat amb l'e-mail {string} i dades vàlides")
    public void unUsuariSHaRegistratAmbLEMailIDadesValides(String email) {
        // Fem servir dades vàlides per defecte per registrar l'usuari
        state.controlador.registrarUsuari(email, "Password123$", "usuariTest", "01-01-2000");
    }

    @Given("un joc disponible anomenat {string} existeix al catàleg")
    public void unJocDisponibleAnomenatExisteixAlCataleg(String titolJoc) throws Exception {
        Joc joc = Joc.crearJocDisponible(
                titolJoc,
                List.of("Acció"),
                List.of("TestDev"),
                List.of("TestPub"),
                LocalDate.now().minusYears(1),
                LocalDate.now().minusYears(2)
        );
        // L'afegim manualment al catàleg (no al DAO, només per a aquest test)
        state.controlador.getCatalegJocs().addJoc(joc);
    }

    @Given("un joc anunciat anomenat {string} existeix al catàleg")
    public void unJocAnunciatAnomenatExisteixAlCataleg(String titolJoc) throws Exception {
        Joc joc = Joc.crearJocAnunciat(
                titolJoc,
                List.of("Aventura"),
                List.of("TestDev"),
                List.of("TestPub"),
                LocalDate.now().minusMonths(1)
        );
        state.controlador.getCatalegJocs().addJoc(joc);
    }


    @When("l'usuari amb e-mail {string} adquireix el joc {string}")
    public void lUsuariAmbEMailAdquireixElJoc(String email, String titolJoc) {
        // AQUEST MÈTODE ENCARA NO EXISTEIX. ENS DONARÀ L'ERROR "RED"
        state.resultat = state.controlador.adquirirJoc(email, titolJoc);
    }
}