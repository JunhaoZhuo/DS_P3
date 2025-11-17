package steps;

import io.cucumber.java.en.When;
import resources.WorldState;

public class JugarSessioJocSteps {

    private final WorldState state;

    public JugarSessioJocSteps(WorldState state) {
        this.state = state;
    }

    @When("l'usuari {string} inicia i finalitza una sessió del joc {string}")
    public void lUsuariIniciaIFinalitzaUnaSessioDelJoc(String email, String titolJoc) {
        // AQUEST MÈTODE ENCARA NO EXISTEIX. ENS DONARÀ L'ERROR "RED"
        state.resultat = state.controlador.jugarSessio(email, titolJoc);
    }
}