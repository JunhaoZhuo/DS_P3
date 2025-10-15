package resources.implementations.mock.daofactory;

import resources.interfaces.entities.EspecAssolimentDAO;
import resources.implementations.mock.entities.EspecAssolimentMockDAO;
import resources.interfaces.relations.RelacioSessioJocAssolimentJocEspecAssolimentJocDAO;
import resources.interfaces.relations.RelacioUsuariAdquisicioJocDAO;
import resources.interfaces.entities.JocDAO;
import resources.interfaces.entities.UsuariDAO;
import resources.interfaces.relations.RelacioUsuariAdquisicioSessioJocDAO;
import resources.implementations.mock.relations.RelacioSessioJocAssolimentJocEspecAssolimentJocMockDAO;
import resources.implementations.mock.relations.RelacioUsuariAdquisicioJocMockDAO;
import resources.implementations.mock.entities.JocMockDAO;
import resources.implementations.mock.entities.UsuariMockDAO;
import resources.implementations.mock.relations.RelacioUsuariAdquisicioSessioJocMockDAO;
import resources.interfaces.daofactory.AbstractDAOFactory;

public class MockDAOFactory implements AbstractDAOFactory {

    /*
     * Mètodes per crear DAOs per a diferents entitats
     */

    @Override
    public UsuariDAO createUsuariDAO() {
        return new UsuariMockDAO();
    }
    @Override
    public JocDAO createJocDAO() { return new JocMockDAO(); }
    @Override
    public RelacioUsuariAdquisicioJocDAO createRelacioUsuariAdquisicioJocDAO() { return new RelacioUsuariAdquisicioJocMockDAO(); }
    @Override
    public RelacioUsuariAdquisicioSessioJocDAO createRelacioUsuariSessioJocAdquisicioDAO() { return new RelacioUsuariAdquisicioSessioJocMockDAO(); }
    @Override
    public EspecAssolimentDAO createEspecAssolimentDAO() { return new EspecAssolimentMockDAO(); }
    @Override
    public RelacioSessioJocAssolimentJocEspecAssolimentJocDAO createRelacioSessioJocAssolimentJocEspecAssolimentJocDAO() {
        return new RelacioSessioJocAssolimentJocEspecAssolimentJocMockDAO();
    }
}
