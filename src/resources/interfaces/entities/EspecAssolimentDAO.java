package resources.interfaces.entities;

import model.EspecAssolimentJoc;
import resources.interfaces.DAOEntity;

import java.util.List;

public interface EspecAssolimentDAO extends DAOEntity<EspecAssolimentJoc> {
    List<EspecAssolimentJoc> getAll();

    boolean add(EspecAssolimentJoc especAssolimentJoc) throws Exception;

    boolean delete(EspecAssolimentJoc especAssolimentJoc) throws Exception;
}
