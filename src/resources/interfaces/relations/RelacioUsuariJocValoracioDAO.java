package resources.interfaces.relations;

import model.Revisio;
import model.Valoracio;
import resources.interfaces.DAO;
import utils.tuples.Trio;

import java.util.List;

// Aquesta interfície defineix que retornarem un Trio: Email (String), Títol Joc (String), i l'objecte Revisio
public interface RelacioUsuariJocValoracioDAO extends DAO<Trio<String, String, Valoracio>> {
    List<Trio<String, String, Valoracio>> getAll();
}