package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Joc;
import resources.WorldState;
import java.time.LocalDate;
import java.util.List;
import static org.junit.Assert.assertTrue;

public class ComentarJocSteps {
    private final WorldState state;

    public ComentarJocSteps(WorldState state) {
        this.state = state;
    }

    @Given("existeix un joc anomenat {string} amb estat {string}")
    public void existeixUnJocAnomenatAmbEstat(String titol, String estat) {
        Joc joc;
        // Creem el joc segons l'estat demanat (Factory methods de Joc)
        if (estat.equalsIgnoreCase("ANUNCIAT")) {
            joc = Joc.crearJocAnunciat(titol, List.of("RPG"), List.of("Dev"), List.of("Pub"), LocalDate.now());
        } else if (estat.equalsIgnoreCase("RETIRAT")) {
            joc = Joc.crearJocRetirat(titol, List.of("RPG"), List.of("Dev"), List.of("Pub"), LocalDate.now(), LocalDate.now(), LocalDate.now());
        } else {
            joc = Joc.crearJocDisponible(titol, List.of("RPG"), List.of("Dev"), List.of("Pub"), LocalDate.now(), LocalDate.now());
        }
        state.controlador.getCatalegJocs().addJoc(joc);
    }

    @When("l'usuari amb e-mail {string} comenta el joc {string} amb el text {string}")
    public void lUsuariComentaElJocAmbElText(String email, String titol, String text) {
        // Aquest mètode l'hem de crear al controlador
        state.resultat = state.controlador.comentarJoc(email, titol, text);
    }

    @Then("els detalls del joc {string} mostren el comentari {string} fet per {string}")
    public void elsDetallsDelJocMostrenElComentariFetPer(String titol, String text, String autor) {
        // Reutilitzem la funcionalitat de veure detalls (US6) per verificar la US12
        String detalls = state.controlador.veureDetallsJoc(titol);

        assertTrue("El comentari no es veu als detalls", detalls.contains(text));
        assertTrue("L'autor no es veu als detalls", detalls.contains(autor));
    }
}