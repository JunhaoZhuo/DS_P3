package steps;

import controlador.Controlador;
import io.cucumber.java.Before;
import io.cucumber.java.en.When;
import resources.WorldState;

import static org.junit.Assert.assertEquals;

public class LoguejarUsuariSteps {
    private final WorldState state;

    public LoguejarUsuariSteps(WorldState state) {
        this.state = state;
    }

    @When("l'usuari inicia sessió amb l'e-mail {string} i la contrasenya {string}")
    public void lUsuariIniciaSessioAmbLeMailILaContrasenya(String email, String contrasenya) {
        state.resultat = state.controlador.loguejarUsuari(email, contrasenya);
    }
}
