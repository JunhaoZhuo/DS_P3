package resources.interfaces.daofactory;


import resources.interfaces.entities.EspecAssolimentDAO;
import resources.interfaces.relations.RelacioSessioJocAssolimentJocEspecAssolimentJocDAO;
import resources.interfaces.relations.RelacioUsuariAdquisicioJocDAO;
import resources.interfaces.entities.JocDAO;
import resources.interfaces.entities.UsuariDAO;
import resources.interfaces.relations.RelacioUsuariAdquisicioSessioJocDAO;

public interface AbstractDAOFactory {

    /*
     * Mètodes per crear DAOs per a diferents entitats
     */

    UsuariDAO createUsuariDAO();
    JocDAO createJocDAO();
    RelacioUsuariAdquisicioJocDAO createRelacioUsuariAdquisicioJocDAO();
    RelacioUsuariAdquisicioSessioJocDAO createRelacioUsuariSessioJocAdquisicioDAO();
    EspecAssolimentDAO createEspecAssolimentDAO();
    RelacioSessioJocAssolimentJocEspecAssolimentJocDAO createRelacioSessioJocAssolimentJocEspecAssolimentJocDAO();
}
