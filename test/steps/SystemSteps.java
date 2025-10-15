package steps;

import controlador.Controlador;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import resources.WorldState;

import static org.junit.Assert.assertEquals;

public class SystemSteps {
    private final WorldState state;

    public SystemSteps(WorldState state) {
        this.state = state;
    }

    @Given("s'han carregat els usuaris de la base de dades")
    public void sHanCarregatElsUsuarisDeLaBaseDeDades() {
        state.controlador.loadUsuariDataFromResources();
    }

    @Given("s'ha carregat el catàleg de la base de dades")
    public void sHaCarregatElCatàlegDeLaBaseDeDades() {
        state.controlador.loadJocDataFromResources();
    }

    @Given("els usuaris i les seves adquisicions es troben carregats de la base de dades")
    public void elsUsuarisILesSevesAdquisicionsEsTrobenCarregatsDeLaBaseDeDades() {
        state.controlador.loadDataFromResources();
    }

    @Then("el sistema mostra el missatge {string}")
    public void elSistemaMostraElMissatge(String expected) {
        assertEquals(expected, state.resultat);
    }

    @Then("el sistema mostra el missatge següent:")
    public void elSistemaMostraElMissatgeSeguent(String docString) {
        assertEquals(docString, state.resultat);
    }
}
