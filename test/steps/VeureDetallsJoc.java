package steps;

import controlador.Controlador;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.When;
import resources.WorldState;

public class VeureDetallsJoc {
    private final WorldState state;

    public VeureDetallsJoc(WorldState state) {
        this.state = state;
    }

    @When("l'usuari tria veure els detalls del joc {string}")
    public void lUsuariTriaVeureElsDetallsDelJoc(String titol) {
        state.resultat = state.controlador.veureDetallsJoc(titol);
    }
}
