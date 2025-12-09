package resources;

import controlador.interfaces.IDataService;
import model.*;
import model.excepcions.JocNotFoundException;
import resources.interfaces.entities.EspecAssolimentDAO;
import resources.interfaces.relations.RelacioSessioJocAssolimentJocEspecAssolimentJocDAO;
import resources.interfaces.relations.RelacioUsuariAdquisicioJocDAO;
import resources.interfaces.entities.JocDAO;
import resources.interfaces.entities.UsuariDAO;
import resources.interfaces.relations.RelacioUsuariAdquisicioSessioJocDAO;
import resources.interfaces.daofactory.AbstractDAOFactory;
import utils.tuples.Quintet;
import utils.tuples.Sextet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//noous imports per Revisio
import resources.interfaces.relations.RelacioUsuariJocValoracioDAO;
import utils.tuples.Trio;

public class DataService implements IDataService  {
    private final UsuariDAO usuariDAO;
    private final JocDAO jocDAO;
    private final RelacioUsuariAdquisicioJocDAO relacioUsuariAdquisicioJocDAO;
    private final RelacioUsuariAdquisicioSessioJocDAO relacioUsuariAdquisicioSessioJocDAO;
    private final EspecAssolimentDAO especAssolimentDAO;
    private final RelacioSessioJocAssolimentJocEspecAssolimentJocDAO relacioSessionsJocAssolimentsJocEspecAssolimentsJoc;


    // nou mètode per Revisio
    private final RelacioUsuariJocValoracioDAO relacioUsuariJocRevisioDAO;
//    private CarteraUsuaris carteraUsuaris;
//    private CatalegJocs catalegJocs;
    // NOU DAO
    private final resources.interfaces.relations.RelacioJocEspecAssolimentDAO relacioJocEspecAssolimentDAO;


    public DataService(AbstractDAOFactory factory) {
        usuariDAO = factory.createUsuariDAO();
        jocDAO = factory.createJocDAO();
        relacioUsuariAdquisicioJocDAO       = factory.createRelacioUsuariAdquisicioJocDAO();
        relacioUsuariAdquisicioSessioJocDAO = factory.createRelacioUsuariSessioJocAdquisicioDAO();
        especAssolimentDAO = factory.createEspecAssolimentDAO();
        relacioSessionsJocAssolimentsJocEspecAssolimentsJoc = factory.createRelacioSessioJocAssolimentJocEspecAssolimentJocDAO();

        // inicialitzacio nou atribut, crerea l'objecte DAO de Revisio
        relacioUsuariJocRevisioDAO = factory.createRelacioUsuariJocRevisioDAO();
        // NOU DAO
        relacioJocEspecAssolimentDAO = factory.createRelacioJocEspecAssolimentDAO();

    }

    public void loadDataInto(CarteraUsuaris cu, CatalegJocs cj) {
        try {
            initCarteraUsuaris(cu);
            initCatalegJocs(cj);
            relacionarUsuarisAdquisicionsJocs(cu, cj); // Usuari -- AdquisicioJoc
            relacionarUsuarisAdquisicionsSessionsJoc(cu); // Usuari -- Adquisicio -- SessioJoc
            initEspecAssoliments(cj);
            relacionarSessionsJocAssolimentsJocEspecAssolimentsJoc(cu, cj); // SessioJoc -- AssolimentJoc -- EspecAssolimentJoc

            // NOVA CRIDA: Carregar revisions, cal implementar el metode
            relacionarUsuarisJocsRevisions(cu);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // implementacio per la crida en loadDataInto
    private void relacionarUsuarisJocsRevisions(CarteraUsuaris cu) throws Exception {
        List<Trio<String, String, Valoracio>> relacions = relacioUsuariJocRevisioDAO.getAll();

        for (Trio<String, String, Valoracio> r : relacions) {
            String email = r.getElement1();
            String titolJoc = r.getElement2();
            Valoracio valoracio = r.getElement3();

            // 1. Busquem l'usuari
            Usuari usuari = cu.findByEmail(email);
            if (usuari != null) {
                // 2. Busquem l'adquisició d'aquest joc
                Adquisicio adquisicio = usuari.findAdquisicioByTitol(titolJoc); // Mètode creat a la US8
                if (adquisicio != null) {
                    // 3. Assignem la revisió
                    adquisicio.setValoracio(valoracio);
                }
            }
        }
    }

    public void loadUsuarisInto(CarteraUsuaris cu) {
        try {
            initCarteraUsuaris(cu);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void loadJocsInto(CatalegJocs cj) {
        try {
            initCatalegJocs(cj);
            initEspecAssoliments(cj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Crea la cartera d'usuaris amb tots els usuaris carregats via UsuuariDAO
     */
    public void initCarteraUsuaris(CarteraUsuaris cu) throws Exception {
        // Obtenir tots els usuaris
        List<Usuari> usuaris = usuariDAO.getAll();

        // Crear la cartera d'usuaris amb els usuaris carregats
        cu.setUsuaris(usuaris);
    }

    /*
     * Assigna els assoliments específics als jocs del catàleg via JocDAO
     */
    public void initCatalegJocs(CatalegJocs cj) throws Exception {
        // Obtenir tots els jocs
        List<Joc> jocs = jocDAO.getAll();

        // Crear el catàleg de jocs amb els jocs carregats
        cj.setJocs(jocs);
    }

    /*
     * Assigna els assoliments específics als jocs del catàleg via EspecAssolimentDAO
     */
    private void initEspecAssoliments(CatalegJocs cj) {
        try {
            // 1. Obtenim totes les definicions d'assoliments i les mapem per títol per accés ràpid
            List<EspecAssolimentJoc> totsAssoliments = especAssolimentDAO.getAll();
            java.util.Map<String, EspecAssolimentJoc> mapaAssoliments = new java.util.HashMap<>();
            for (EspecAssolimentJoc ea : totsAssoliments) {
                mapaAssoliments.put(ea.getTitol(), ea);
            }

            // 2. Obtenim les relacions (Quin joc té quin assoliment)
            List<utils.tuples.Parell<String, String>> relacions = relacioJocEspecAssolimentDAO.getAll();

            // 3. Processem les relacions
            for (utils.tuples.Parell<String, String> relacio : relacions) {
                String titolJoc = relacio.getElement1();
                String titolAssoliment = relacio.getElement2();

                Joc joc = cj.findByTitol(titolJoc);
                EspecAssolimentJoc ea = mapaAssoliments.get(titolAssoliment);

                if (joc != null && ea != null) {
                    joc.afegirEspecAssolimentJoc(ea);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error inicialitzant assoliments: " + e.getMessage());
        }
    }
    /*
     * Relaciona els usuaris amb les adquisicions i les adquisicions amb els jocs via RelacioUsuariAdquisicioJocDAO
     */
    private void relacionarUsuarisAdquisicionsJocs(CarteraUsuaris cu, CatalegJocs cj) throws Exception {
        // Obtenir totes les relacions
        List<Quintet<String, String, LocalDate, Double, String>> relacionsUsuariAdquisicioJoc;
        try {
            relacionsUsuariAdquisicioJoc = relacioUsuariAdquisicioJocDAO.getAll();
            for (Quintet<String, String, LocalDate, Double, String> r : relacionsUsuariAdquisicioJoc) {
                // Extreure els elements del quintet
                String emailUsuari = r.getElement1();
                String titolJoc = r.getElement2();
                LocalDate dataAdquisicio = r.getElement3();
                Double quantitatPreu = r.getElement4();
                String monedaPreu = r.getElement5();

                // Buscar l'usuari i el joc corresponents
                Usuari usuariTrobat = cu.findByEmail(emailUsuari);
                Joc jocTrobat = cj.findByTitol(titolJoc);
                if (usuariTrobat != null && jocTrobat != null) {
                    Adquisicio adquisicio = Adquisicio.crearAdquisicio(
                            jocTrobat,
                            dataAdquisicio,
                            quantitatPreu,
                            monedaPreu
                    );
                    usuariTrobat.addAdquisicio(adquisicio);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Relaciona els usuaris amb les sessions de joc i les sessions de joc amb les adquisicions via
     * RelacioUsuariSessioJocAdquisicioDAO.
     */
    private void relacionarUsuarisAdquisicionsSessionsJoc(CarteraUsuaris cu) throws Exception {
        List<Sextet<String, String, LocalDate, LocalDateTime, LocalDateTime, Integer>> relacionsUsuariAdquisicioSessioJoc;
        try {
            relacionsUsuariAdquisicioSessioJoc = relacioUsuariAdquisicioSessioJocDAO.getAll();
            for (Sextet<String, String, LocalDate, LocalDateTime, LocalDateTime, Integer> r : relacionsUsuariAdquisicioSessioJoc) {
                // Extreure els elements del sextet
                String emailUsuari = r.getElement1();
                String titolJoc = r.getElement2();
                LocalDate dataAdquisicio = r.getElement3();
                LocalDateTime dataIniciSessio = r.getElement4();
                LocalDateTime dataFiSessio = r.getElement5();
                Integer estatSessio = r.getElement6();

                // Buscar l'usuari i el joc corresponents
                Usuari usuariTrobat = cu.findByEmail(emailUsuari);
                if (usuariTrobat != null) {
                    Adquisicio adquisicioTrobat = usuariTrobat.findByTitolJocIDataAdquisicio(titolJoc, dataAdquisicio);
                    if (adquisicioTrobat != null) {
                        SessioJoc sessioJoc = new SessioJoc(dataIniciSessio, dataFiSessio, EstatSessioJoc.values()[estatSessio]);
                        adquisicioTrobat.afegirSessioJoc(sessioJoc);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void relacionarSessionsJocAssolimentsJocEspecAssolimentsJoc(CarteraUsuaris cu, CatalegJocs cj) throws Exception {
        List<Sextet<String, String, LocalDate, LocalDateTime, String, LocalDateTime>> relacions = relacioSessionsJocAssolimentsJocEspecAssolimentsJoc.getAll();
        for (Sextet<String, String, LocalDate, LocalDateTime, String, LocalDateTime> r : relacions) {
            // Extreure els elements del sextet
            String emailUsuari = r.getElement1();
            String titolJoc = r.getElement2();
            LocalDate dataAdquisicio = r.getElement3();
            LocalDateTime dataIniciSessioJoc = r.getElement4();
            String titolAssoliment = r.getElement5();
            LocalDateTime dataHoraAssoliment = r.getElement6();

            Usuari usuari = cu.findByEmail(emailUsuari);
            if (usuari != null) {
                Adquisicio adquisicio = usuari.findByTitolJocIDataAdquisicio(titolJoc, dataAdquisicio);
                if (adquisicio != null) {
                    SessioJoc sessioJoc = adquisicio.findSessioJocByDataInici(dataIniciSessioJoc);
                    if (sessioJoc != null) {
                        EspecAssolimentJoc especAssoliment = cj.findEspecAssolimentByTitol(titolAssoliment);
                        if (especAssoliment != null) {
                            AssolimentJoc assolimentJoc = new AssolimentJoc(especAssoliment, dataHoraAssoliment);
                            sessioJoc.afegirAssolimentJoc(assolimentJoc);
                        }
                    }
                }
            }
        }
    }
}
