package resources.implementations.mock.relations;

import model.Comentari;
import model.Revisio;
import model.Valoracio; // Importem la classe pare
import resources.interfaces.relations.RelacioUsuariJocValoracioDAO; // Interfície renombrada
import utils.tuples.Trio;

import java.time.LocalDate;

public class RelacioUsuariJocValoracioMockDAO extends RelacioMockDAO<Trio<String, String, Valoracio>> implements RelacioUsuariJocValoracioDAO {

    public RelacioUsuariJocValoracioMockDAO() {
        // Cas 1: Una Revisió (com teníem abans)
        addValoracio(
                "marta.soler@example.com",
                "Baldur's Gate 3",
                new Revisio(10, 9, 10, 9)
        );

        // Cas 2: Una altra Revisió
        addValoracio(
                "ajaleo@gmail.com",
                "Elden Ring: Shadow of the Erdtree",
                new Revisio(9, 10, 8, 10)
        );

        // Cas 3: Un Comentari (NOU!)
        addValoracio(
                "ajaleo@gmail.com",
                "Paragon",
                new Comentari("Joc molt divertit però tancat massa aviat",
                        LocalDate.of(2018, 4, 20),
                        "ajaleo@gmail.com")
        );
    }

    // Aquest mètode ara accepta qualsevol filla de Valoracio (polimorfisme)
    private void addValoracio(String email, String titolJoc, Valoracio valoracio) {
        try {
            relacions.add(new Trio<>(email, titolJoc, valoracio));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}