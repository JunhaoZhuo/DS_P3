package application;

import controlador.Controlador;
import controlador.interfaces.IDataService;
import resources.implementations.mock.daofactory.MockDAOFactory;
import resources.DataService;

public class AppContainer {
    public MockDAOFactory factory = new MockDAOFactory();
    public IDataService dataService = new DataService(factory);
    public Controlador controlador = new Controlador(dataService);
}
