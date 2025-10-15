package steps;

import controlador.Controlador;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import resources.WorldState;

import static org.junit.Assert.assertEquals;

public class RegistrarUsuariSteps {
    private final WorldState state;

    public RegistrarUsuariSteps(WorldState state) {
        this.state = state;
    }

    @Given("un nou usuari amb un e-mail no existent")
    public void unNouUsuariAmbUnEMailNoExistent() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("l'usuari es registra amb l'e-mail {string}, la contrasenya {string}, el nom d'usuari {string} i la data de naixement {string}")
    public void lUsuariEsRegistraAmbLEmailLaContrasenyaElNomDUsuariILaDataDeNaixement(
            String email,
            String contrasenya,
            String nomUsuari,
            String dataNaixement
    ) {
        state.resultat = state.controlador.registrarUsuari(
                email,
                contrasenya,
                nomUsuari,
                dataNaixement
        );
    }

    @Given("un usuari s'ha registrat amb l'e-mail {string}, la contrasenya {string}, el nom d'usuari {string} i la data de naixement {string}")
    public void unUsuariSHaRegistratAmbLEmailLaContrasenyaElNomDUsuariILaDataDeNaixement(
            String email,
            String contrasenya,
            String nomUsuari,
            String dataNaixement
    ) {
        state.resultat = state.controlador.registrarUsuari(
                email,
                contrasenya,
                nomUsuari,
                dataNaixement
        );
    }
}
