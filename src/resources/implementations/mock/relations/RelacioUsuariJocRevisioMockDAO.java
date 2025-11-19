package resources.implementations.mock.relations;

import model.Revisio;
import resources.interfaces.relations.RelacioUsuariJocRevisioDAO;
import utils.tuples.Trio;

public class RelacioUsuariJocRevisioMockDAO extends RelacioMockDAO<Trio<String, String, Revisio>> implements RelacioUsuariJocRevisioDAO {

    public RelacioUsuariJocRevisioMockDAO() {
        // Afegim una revisió de prova (Marta ha revisat Baldur's Gate 3)
        addRevisio(
                "marta.soler@example.com",
                "Baldur's Gate 3",
                10, // Jugabilitat
                9,  // Gràfics
                10, // Història
                9   // Música
        );

        // Pots afegir-ne més si vols
        addRevisio(
                "ajaleo@gmail.com",
                "Elden Ring: Shadow of the Erdtree",
                9, 10, 8, 10
        );
    }

    private void addRevisio(String email, String titolJoc, int jug, int graf, int hist, int mus) {
        try {
            // Creem l'objecte Revisio (del Model) directament aquí
            Revisio revisio = new Revisio(jug, graf, hist, mus);

            // L'afegim a la llista com un Trio
            relacions.add(new Trio<>(email, titolJoc, revisio));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}