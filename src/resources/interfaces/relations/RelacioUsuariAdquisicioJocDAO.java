package resources.interfaces.relations;

import resources.interfaces.DAO;
import utils.tuples.Quintet;

import java.time.LocalDate;
import java.util.List;

public interface RelacioUsuariAdquisicioJocDAO extends DAO<Quintet<String, String, LocalDate, Double, String>> {
    List<Quintet<String, String, LocalDate, Double, String>> getAll();
}
