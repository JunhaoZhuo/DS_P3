package steps;

import io.cucumber.java.en.When;
import resources.WorldState;

public class DemanarRecomanacionsSteps {

    private final WorldState state;

    public DemanarRecomanacionsSteps(WorldState state) {
        this.state = state;
    }

    @When("l'usuari {string} demana recomanacions")
    public void lUsuariDemanaRecomanacions(String email) {
        // AQUEST MÈTODE ENCARA NO EXISTEIX. ENS DONARÀ L'ERROR "RED"
        state.resultat = state.controlador.demanarRecomanacions(email);
    }
}