package resources.interfaces.relations;

import resources.interfaces.DAO;
import utils.tuples.Sextet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RelacioSessioJocAssolimentJocEspecAssolimentJocDAO extends DAO<Sextet<String, String, LocalDate, LocalDateTime, String, LocalDateTime>> {
    List<Sextet<String, String, LocalDate, LocalDateTime, String, LocalDateTime>> getAll();
}
