package resources.interfaces.entities;

import model.Usuari;
import resources.interfaces.DAOEntity;

import java.util.List;

public interface UsuariDAO extends DAOEntity<Usuari> {
    List<Usuari> getAll() throws Exception;
}
