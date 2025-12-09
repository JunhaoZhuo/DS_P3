package resources.interfaces.relations;

import resources.interfaces.DAO;
import utils.tuples.Parell;
import java.util.List;

public interface RelacioJocEspecAssolimentDAO extends DAO<Parell<String, String>> {
    // Retorna Parell<TitolJoc, TitolAssoliment>
    List<Parell<String, String>> getAll();
}