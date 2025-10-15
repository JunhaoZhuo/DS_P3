package resources.interfaces.relations;

import resources.interfaces.DAO;
import utils.tuples.Sextet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RelacioUsuariAdquisicioSessioJocDAO extends DAO<Sextet<String, String, LocalDate, LocalDateTime, LocalDateTime, Integer>> {
    List<Sextet<String, String, LocalDate, LocalDateTime, LocalDateTime, Integer>> getAll();
}
