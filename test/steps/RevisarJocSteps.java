package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import resources.WorldState;

import java.util.HashMap;
import java.util.Map;

public class RevisarJocSteps {

    private final WorldState state;

    public RevisarJocSteps(WorldState state) {
        this.state = state;
    }

    // Aquest 'Given' reutilitza els steps de la US7
    @Given("l'usuari {string} ha adquirit el joc {string}")
    public void lUsuariHaAdquiritElJoc(String email, String titolJoc) throws Exception {
        // 1. Crear usuari
        state.controlador.registrarUsuari(email, "Password123$", "usuariTest-" + email, "01-01-2000");

        // 2. Crear joc
        AdquirirJocSteps adquirirSteps = new AdquirirJocSteps(state);
        adquirirSteps.unJocDisponibleAnomenatExisteixAlCataleg(titolJoc);

        // 3. Adquirir joc
        state.controlador.adquirirJoc(email, titolJoc);
    }

    @When("l'usuari {string} revisa el joc {string} amb les següents puntuacions:")
    public void lUsuariRevisaElJocAmbLesSeguentsPuntuacions(String email, String titolJoc, DataTable dataTable) {
        // Convertim la taula de Cucumber a un Map
        Map<String, Integer> puntuacions = new HashMap<>();
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            puntuacions.put(row.get("Categoria"), Integer.parseInt(row.get("Puntuacio")));
        }

        // AQUEST MÈTODE ENCARA NO EXISTEIX. ENS DONARÀ L'ERROR "RED"
        state.resultat = state.controlador.revisarJoc(email, titolJoc, puntuacions);
    }

    @Given("l'usuari {string} ja ha revisat el joc {string}")
    public void lUsuariJaHaRevisatElJoc(String email, String titolJoc) {
        // Creem una revisió inicial
        Map<String, Integer> puntuacionsAntigues = new HashMap<>();
        puntuacionsAntigues.put("Jugabilitat", 5);
        puntuacionsAntigues.put("Grafics", 5);
        puntuacionsAntigues.put("Historia", 5);
        puntuacionsAntigues.put("Musica", 5);

        state.controlador.revisarJoc(email, titolJoc, puntuacionsAntigues);
    }
}