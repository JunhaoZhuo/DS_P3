package steps;

import controlador.Controlador;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import resources.WorldState;

public class VisualitzarLlistaJocsAdquiritsSteps {
    private final WorldState state;

    public VisualitzarLlistaJocsAdquiritsSteps(WorldState state) {
        this.state = state;
    }

    @When("l'usuari {string} sol·licita veure la llista de jocs adquirits ordenats per nom \\(alfabèticament)")
    public void lUsuariSolLicitaVeureLaLlistaDeJocsAdquiritsOrdenatsPerNomAlfabèticament(String email) {
        state.resultat = state.controlador.visualitzarLlistaJocsAdquiritsPerUsuari(email);
    }
}
