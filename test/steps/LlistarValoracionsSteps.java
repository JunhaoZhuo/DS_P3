package steps;
import io.cucumber.java.en.When;
import resources.WorldState;

public class LlistarValoracionsSteps {
    private final WorldState state;

    public LlistarValoracionsSteps(WorldState state) {
        this.state = state;
    }

    @When("l'usuari {string} sol·licita veure la llista de les seves valoracions")
    public void lUsuariSolLicitaVeureLaLlistaDeLesSevesValoracions(String email) {
        state.resultat = state.controlador.llistarValoracionsUsuari(email);
    }
}