package steps;

import controlador.Controlador;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import resources.WorldState;

import java.util.List;
import java.util.Map;

public class VisualitzarLlistaJocsCatalegSteps {
    private final WorldState state;

    public VisualitzarLlistaJocsCatalegSteps(WorldState state) {
        this.state = state;
    }

    @Given("el catàleg no conté cap videojoc")
    public void elCatàlegNoContéCapVideojoc() {
    }

    @When("l'usuari sol·licita veure la llista de jocs del catàleg ordenats per nom \\(alfabèticament)")
    public void lUsuariSolLicitaVeureLaLlistaDeJocsDelCatàlegOrdenatsPerNomAlfabèticament() {
        state.resultat = state.controlador.visualitzarLlistaJocsCataleg();
    }
}
