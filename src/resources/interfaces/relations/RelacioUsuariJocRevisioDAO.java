package resources.interfaces.relations;

import model.Revisio;
import resources.interfaces.DAO;
import utils.tuples.Trio;

import java.util.List;

// Aquesta interfície defineix que retornarem un Trio: Email (String), Títol Joc (String), i l'objecte Revisio
public interface RelacioUsuariJocRevisioDAO extends DAO<Trio<String, String, Revisio>> {
    List<Trio<String, String, Revisio>> getAll();
}