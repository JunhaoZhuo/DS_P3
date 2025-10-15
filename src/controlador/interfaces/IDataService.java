package controlador.interfaces;

import model.CarteraUsuaris;
import model.CatalegJocs;

public interface IDataService {
    void loadDataInto(CarteraUsuaris carteraUsuaris, CatalegJocs catalegJocs);
    void loadUsuarisInto(CarteraUsuaris carteraUsuaris);
    void loadJocsInto(CatalegJocs catalegJocs);
}
