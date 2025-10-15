package resources;

import application.AppContainer;
import controlador.Controlador;

public class WorldState {
    public AppContainer appContainer = new AppContainer();
    public Controlador controlador = appContainer.controlador;

    public String resultat;
}
