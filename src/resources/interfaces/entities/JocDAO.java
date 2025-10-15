package resources.interfaces.entities;

import model.Joc;
import resources.interfaces.DAOEntity;

import java.util.List;

public interface JocDAO extends DAOEntity<Joc> {
    List<Joc> getAll() throws Exception;
}
